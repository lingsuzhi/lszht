package com.lszpro.service;

import com.lszpro.model.bo.CustomerBO;
import com.lszpro.model.rq.CustomerRq;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.CustomerRs;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2018-1-18
 * 网点
 */
public interface CustomerService {
    CustomerRs customerDel(String id ) ;
    CustomerRs customerById(String id );
    CustomerRs customerSave( CustomerBO CustomerBO);
    CustomerRs customerList(CustomerRq rq);
}