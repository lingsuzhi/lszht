package com.lszpro.web.order;

import com.alibaba.fastjson.JSON;
import com.lszpro.common.LszUtil;
import com.lszpro.common.PoiMod;
import com.lszpro.entity.bo.OrderChildBO;
import com.lszpro.exception.ServiceException;
import com.lszpro.model.bo.OrderBO;
import com.lszpro.model.bo.OrderSetBO;
import com.lszpro.model.rq.OrderRq;
import com.lszpro.service.OrderService;
import com.lszpro.service.OrderSetService;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.OrderRs;
import com.lszpro.soa.rs.OrderSetRs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2018-1-19
 * 单据
 */

@Controller
@RequestMapping(value = "/order")
public class OrderMainController {

    protected static Logger log = LoggerFactory.getLogger(OrderMainController.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderSetService orderSetService;

    @RequestMapping("/orderMain.php")
    public String orderview(@RequestParam(required = true) String id, HttpServletRequest request, @RequestParam(required = false) String orderId, Model model, HttpSession session) {
        OrderSetRs rs = orderSetService.orderSetById(id);
        if (!rs.isSuccess()) {
            throw new ServiceException("9999", "id错误");


        }
        OrderSetBO obj = rs.getData();
        if (obj != null) {
            model.addAttribute("orderSet", obj);

        }

        OrderBO orderBO = null;
        if (orderId != null) {
            OrderRs rs1 = orderService.orderById(orderId);
            if (rs1.isSuccess()) {
                orderBO = rs1.getData();
            }
        } else {
            if (obj != null) {
                orderBO = orderService.dayEndOrde(obj.getId());


            }


        }
        model.addAttribute("orderBO", orderBO);

        return "lsz/order/orderMain";
    }
    @RequestMapping(value = "/orderMainSave.php", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse orderSave(@RequestBody OrderBO orderBO, HttpServletRequest request) {

        return orderService.orderSave(orderBO);
    }
    @RequestMapping("/orderMainDelByDanhao.php")
    @ResponseBody
    public BaseResponse orderDel(@RequestParam(required = true) String danhao, HttpServletRequest request) {
        return orderService.orderDelByDanhao(danhao);

    }

    /**前单*/
    @RequestMapping("/orderLeft.php")
    @ResponseBody
    public BaseResponse orderLeft(@RequestParam(required = true) String id, @RequestParam(required = true) String orderSetId,HttpServletRequest request) {
        return orderService.orderLeft(orderSetId,id);
    }

    /**后单*/
    @RequestMapping("/orderRight.php")
    @ResponseBody
    public BaseResponse orderRight(@RequestParam(required = true) String id, @RequestParam(required = true) String orderSetId,HttpServletRequest request) {
        return orderService.orderRight(orderSetId,id);
    }
    @RequestMapping("/orderChildList.php")
    @ResponseBody
    public ResponseEntity orderChildList(@RequestParam(required = false) String orderId, HttpServletRequest request, Model model, HttpSession session) {

        if (StringUtils.isEmpty(orderId)) {
            return  null;
        }
        OrderBO orderBO = null;
        List<OrderChildBO> returnList = new ArrayList<OrderChildBO>();
            OrderRs rs1 = orderService.orderById(orderId);
            if (rs1.isSuccess()) {
                orderBO = rs1.getData();
                String datas = orderBO.getDatas();

                if(!StringUtils.isEmpty(datas)){
                    returnList = (List<OrderChildBO>)JSON.parse(datas);
//                    String[] strArr = datas.split("/r1/n");
//                    for (String strRow:strArr                         ) {
//                        OrderChildBO obj = new OrderChildBO();
//                        String[] colArr = strRow.split("/r2/n");
//                        if(colArr!=null){
//                           if(colArr.length>0) obj.setNumber(colArr[0]);
//                            if(colArr.length>1) obj.setName(colArr[1]);
//                            if(colArr.length>2)obj.setSpec(colArr[2]);
//                            if(colArr.length>3)obj.setCompany(colArr[3]);
//                            if(colArr.length>4)obj.setProce(colArr[4]);
//                            if(colArr.length>5)obj.setShuliang(colArr[5]);
//                            if(colArr.length>6) obj.setZongjine(colArr[6]);
//                            if(colArr.length>7)obj.setManufacturer(colArr[7]);
//                            if(colArr.length>8)obj.setBarcode(colArr[8]);
//                            returnList.add(obj);
//                        }
//
//                    }
                }

            }
        return LszUtil.returnList(returnList, returnList.size());
    }
    @RequestMapping("/downfile/*.xls")
    public StreamingResponseBody handle2(@RequestParam(required = true) String orderId) {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                HttpHeaders headers = new HttpHeaders();

                OrderRs rs1 = orderService.orderById(orderId);
                Map<String ,String > map = new LinkedHashMap<>();
                map.put("number","编号");
                map.put("name","名称");
                map.put("spec","规格");
                map.put("company","单位");
                map.put("proce","价格");
                map.put("shuliang","数量");
                map.put("zongjine","金额");
                map.put("'barcode'","条形码");

                if (rs1.isSuccess()) {
                    OrderBO orderBO = rs1.getData();
                    String datas = orderBO.getDatas();
                    if(!StringUtils.isEmpty(datas)) {
                        List<Object> returnList   = (List<Object>) JSON.parse(datas);
                        PoiMod.doXls(outputStream, returnList, map);
                    }
                }


            }

        };

    }
}