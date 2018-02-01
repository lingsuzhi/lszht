package com.lszpro.service.impl;

import com.lszpro.model.bo.OrderSetBO;
import com.lszpro.model.rq.OrderSetRq;
import com.lszpro.service.OrderSetService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.OrderSetRs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2018-1-19
 * 单据配置
 */
@Service
public class OrderSetServiceImpl implements OrderSetService {
    private static final   ConstantsUri OrderSet_list = new ConstantsUri("单据配置列表","/orderSet/orderSetList.php");
    private static final ConstantsUri OrderSet_info=new ConstantsUri("单据配置详情","/orderSet/orderSetById.php");
    private static final ConstantsUri OrderSet_save=new ConstantsUri("单据配置保存","/orderSet/orderSetSave.php");
    private static final ConstantsUri OrderSet_del= new ConstantsUri("单据配置删除","/orderSet/orderSetDel.php");
  @Override
    public OrderSetRs orderSetDel( String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        OrderSetRs rs = SoaConnectionFactory.get( OrderSet_del, map, OrderSetRs.class);
        return rs;
    }

    @Override
    public OrderSetRs orderSetById(String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        OrderSetRs rs = SoaConnectionFactory.get( OrderSet_info, map, OrderSetRs.class);
        return rs;
    }

    @Override
    public OrderSetRs orderSetSave(OrderSetBO OrderSetBO) {
        OrderSetRs returnObj = SoaConnectionFactory.post( OrderSet_save, OrderSetBO, OrderSetRs.class);
        return returnObj;
    }

    @Override
    public OrderSetRs orderSetList(OrderSetRq rq) {
        OrderSetRs rs = SoaConnectionFactory.get( OrderSet_list, rq, OrderSetRs.class);
        return rs;
    }


}