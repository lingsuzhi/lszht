package com.lszpro.service.impl;

import com.lszpro.model.bo.CustomerBO;
import com.lszpro.model.rq.CustomerRq;
import com.lszpro.service.CustomerService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.CustomerRs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2018-1-18
 * 网点
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private static final   ConstantsUri Customer_list = new ConstantsUri("网点列表","/customer/customerList.php");
    private static final ConstantsUri Customer_info=new ConstantsUri("网点详情","/customer/customerById.php");
    private static final ConstantsUri Customer_save=new ConstantsUri("网点保存","/customer/customerSave.php");
    private static final ConstantsUri Customer_del= new ConstantsUri("网点删除","/customer/customerDel.php");
  @Override
    public CustomerRs customerDel( String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        CustomerRs rs = SoaConnectionFactory.get( Customer_del, map, CustomerRs.class);
        return rs;
    }

    @Override
    public CustomerRs customerById(String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        CustomerRs rs = SoaConnectionFactory.get( Customer_info, map, CustomerRs.class);
        return rs;
    }

    @Override
    public CustomerRs customerSave(CustomerBO CustomerBO) {
        CustomerRs returnObj = SoaConnectionFactory.post( Customer_save, CustomerBO, CustomerRs.class);
        return returnObj;
    }

    @Override
    public CustomerRs customerList(CustomerRq rq) {
        CustomerRs rs = SoaConnectionFactory.get( Customer_list, rq, CustomerRs.class);
        return rs;
    }


}