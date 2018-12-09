package com.lszpro.service.impl;

import com.lszpro.model.bo.OrderBO;
import com.lszpro.model.rq.OrderRq;
import com.lszpro.service.OrderService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.rs.OrderRs;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2018-1-19
 * 单据
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static final ConstantsUri Order_list = new ConstantsUri("单据列表", "/order/orderList.php");
    private static final ConstantsUri Order_info = new ConstantsUri("单据详情", "/order/orderById.php");
    private static final ConstantsUri Order_save = new ConstantsUri("单据保存", "/order/orderSave.php");
    private static final ConstantsUri Order_del = new ConstantsUri("单据删除", "/order/orderDel.php");
    private static final ConstantsUri Order_DelByDanhao = new ConstantsUri("单据删除", "/order/orderDeldanhaoByDanhao.php");


    private static final ConstantsUri Order_end = new ConstantsUri("单据最后一单", "/order/orderEnd.php");

    private static final ConstantsUri Order_left = new ConstantsUri("单据前单", "/order/orderLeft.php");
    private static final ConstantsUri Order_right = new ConstantsUri("单据后单", "/order/orderRight.php");

    @Override
    public OrderRs orderDel(String id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        OrderRs rs = SoaConnectionFactory.get(Order_del, map, OrderRs.class);
        return rs;
    }

    @Override
    public OrderRs orderById(String id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        OrderRs rs = SoaConnectionFactory.get(Order_info, map, OrderRs.class);
        return rs;
    }

    @Override
    public OrderRs orderDelByDanhao(String danho) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("danhao", danho);
        OrderRs rs = SoaConnectionFactory.get(Order_DelByDanhao, map, OrderRs.class);
        return rs;
    }

    @Override
    public OrderRs orderSave(OrderBO OrderBO) {
        OrderRs returnObj = SoaConnectionFactory.post(Order_save, OrderBO, OrderRs.class);
        return returnObj;
    }

    @Override
    public OrderRs orderList(OrderRq rq) {
        OrderRs rs = SoaConnectionFactory.get(Order_list, rq, OrderRs.class);
        return rs;
    }

    @Override
    public OrderBO dayEndOrde(String orderSetId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderSetId", orderSetId);
        OrderRs rs = SoaConnectionFactory.get(Order_end, map, OrderRs.class);
        if (rs.isSuccess()) {
            return rs.getData();
        }
        return null;
    }

    @Override
    public OrderRs orderLeft(String orderSetId, String id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderSetId", orderSetId);
        map.put("id", id);
        OrderRs rs = SoaConnectionFactory.get(Order_left, map, OrderRs.class);

        return rs;
    }

    @Override
    public OrderRs orderRight(String orderSetId, String id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderSetId", orderSetId);
        map.put("id", id);
        OrderRs rs = SoaConnectionFactory.get(Order_right, map, OrderRs.class);

        return rs;
    }

}