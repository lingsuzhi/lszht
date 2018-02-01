package com.lszpro.web;

import com.alibaba.fastjson.JSON;
import com.lszpro.common.Configs;
import com.lszpro.common.LszUtil;

import com.lszpro.common.Utils;
import com.lszpro.entity.rs.ProductTypeNode;
import com.lszpro.exception.ServiceException;
import com.lszpro.model.bo.ProductBO;
import com.lszpro.model.bo.ProductTypeBO;
import com.lszpro.model.rq.ProductRq;
import com.lszpro.service.ProductCompanyService;
import com.lszpro.service.ProductService;
import com.lszpro.service.ProductTypeService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.ProductCompanyRs;
import com.lszpro.soa.rs.ProductRs;
import com.lszpro.soa.rs.ProductTypeRs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2017-12-15
 */

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    protected static Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCompanyService productCompanyService;
    @Autowired
    private ProductTypeService productTypeService;
    @RequestMapping("/productView.php")
    public String productview(HttpServletRequest request, Model model, HttpSession session) {

        return "lsz/Product_list";
    }
    @RequestMapping("/ssproduct.php")
    public String productss(HttpServletRequest request, Model model, HttpSession session) {

        ProductTypeRs rs = productTypeService.productTypeList(null);
        if (rs.isSuccess()) {
            List<ProductTypeNode> rList =  getProductTypeParentNodes(rs.getDataList());

            model.addAttribute("tree", JSON.toJSONString(rList));

        }
        return "lsz/Product_ss";
    }
    @RequestMapping("/productEdit.php")
    public String productEdit(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            ProductRs rs = productService.productById(id);
            model.addAttribute("obj", rs.getData());
        }
        ProductCompanyRs pcRs =  productCompanyService.productCompanyList(null);
        if (pcRs.isSuccess()){
            model.addAttribute("ProductCompanyRs",  pcRs.getDataList());
        }

        return "lsz/Product_edit";
    }

    @RequestMapping("/productList.php")
    @ResponseBody
    public ResponseEntity productList(ProductRq rq, HttpServletRequest request, Model model, HttpSession session) {

        ProductRs rs = productService.productList(rq);
        if (rs.isSuccess()) {
            return LszUtil.returnList(rs.getDataList(), rs.getTotal());
        }
         return ResponseEntity.ok(rs);
    }

    @RequestMapping(value = "/productSave.php", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse productSave(@RequestBody ProductBO productBO, HttpServletRequest request) {
        return productService.productSave(productBO);
    }

    @RequestMapping("/productById.php")
    @ResponseBody
    public BaseResponse productById(@RequestParam(required = true) String id, HttpServletRequest request) {
        return productService.productById(id);
    }

    @RequestMapping("/productDel.php")
    @ResponseBody
    public BaseResponse productDel(@RequestParam(required = true) String id, HttpServletRequest request) {
         return productService.productDel(id);

    }

    /** 设置头像 */
    @RequestMapping(value = "/setimg2", method = RequestMethod.POST)
    public @ResponseBody BaseResponse importPicFile1(@RequestParam MultipartFile img,
                                                       @RequestParam(required = true) String id, HttpServletRequest request) {
//暂时不删除旧文件，以后可以批处理
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException("9999", "id错误");
        }
        // 原始名称
        String originalFilename = img.getOriginalFilename();
        // img.getSize() 大小
        // 上传图片
        if (img != null && originalFilename != null && originalFilename.length() > 0) {
            // 存储图片的物理路径
            String pic_path = Configs.FileDir;
            // 新的图片名称
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            // 新图片
            pic_path = pic_path + "productImg\\";

            File newFile = new File(pic_path + newFileName);
            // 判断目标文件所在的目录是否存在
            if (!newFile.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建父目录
                System.out.println("目标文件所在目录不存在，准备创建它！");
                if (!newFile.getParentFile().mkdirs()) {

                }
            }
            // 将内存中的数据写入磁盘
            try {
                img.transferTo(newFile);
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ProductBO obj = new ProductBO();
            obj.setId(id);
            obj.setImage("/imgs/productImg/" + newFileName);
            ProductRs returnObj = productService.productSave(obj);
            return returnObj;
        }
        throw new ServiceException("9999", "图片异常");
    }

    private List<ProductTypeNode> getProductTypeParentNodes(List<ProductTypeBO> list) {
        if (list == null) return null;
        List<ProductTypeNode> parentNodes = new ArrayList<>();
        for (ProductTypeBO ProductType : list) {
            if (StringUtils.isEmpty(ProductType.getParentId()) ) {
                ProductTypeNode parentNode = new ProductTypeNode();
                parentNode.setProductType(ProductType);
                setChildNode(parentNode, list);
                parentNodes.add(parentNode);
            }
        }
        if(!parentNodes.isEmpty()){
            sortProductTypeNode(parentNodes);
        }
        return parentNodes;
    }

    /* node表示本节点，list 代表所有菜单 */
    private void setChildNode(ProductTypeNode node, List<ProductTypeBO> list) {
        List<ProductTypeNode> childNode = new ArrayList<>();
        for (ProductTypeBO m : list) {
            if ((node.getProductType().getId()).equals(m.getParentId())) {
                ProductTypeNode n = new ProductTypeNode();
                n.setProductType(m);
                setChildNode(n, list);
                childNode.add(n);
            }
        }
        if(!childNode.isEmpty()){
            sortProductTypeNode(childNode);
        }
        node.setChildren(childNode);
    }

    private void sortProductTypeNode(List<ProductTypeNode> list){
        Collections.sort(list, new Comparator<ProductTypeNode>() {
            public int compare(ProductTypeNode n1, ProductTypeNode n2) {
                Integer sort1 = n1.getProductType().getSort();
                Integer sort2 = n2.getProductType().getSort();
                if (sort1 == null) sort1 = 0;
                if (sort2 == null) sort2 = 0;

                if (sort1 > sort2) {
                    return 1;
                } else if (sort1 == sort2) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
    }
}