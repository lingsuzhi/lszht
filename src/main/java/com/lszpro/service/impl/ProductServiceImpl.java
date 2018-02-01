package com.lszpro.service.impl;

import com.lszpro.model.bo.ProductBO;
import com.lszpro.model.rq.ProductRq;
import com.lszpro.service.ProductService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.ProductRs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
@Service
public class ProductServiceImpl implements ProductService {
    private static final   ConstantsUri Product_list = new ConstantsUri("商品列表","/product/productList.php");
    private static final ConstantsUri Product_info=new ConstantsUri("商品详情","/product/productById.php");
    private static final ConstantsUri Product_save=new ConstantsUri("商品保存","/product/productSave.php");
    private static final ConstantsUri Product_del= new ConstantsUri("商品删除","/product/productDel.php");
  @Override
    public ProductRs productDel( String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        ProductRs rs = SoaConnectionFactory.get( Product_del, map, ProductRs.class);
        return rs;
    }

    @Override
    public ProductRs productById(String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        ProductRs rs = SoaConnectionFactory.get( Product_info, map, ProductRs.class);
        return rs;
    }

    @Override
    public ProductRs productSave(ProductBO ProductBO) {
        ProductRs returnObj = SoaConnectionFactory.post( Product_save, ProductBO, ProductRs.class);
        return returnObj;
    }

    @Override
    public ProductRs productList(ProductRq rq) {
        ProductRs rs = SoaConnectionFactory.get( Product_list, rq, ProductRs.class);
        return rs;
    }


}