package com.lszpro.service;

import com.lszpro.model.bo.OrderSetBO;
import com.lszpro.model.rq.OrderSetRq;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.OrderSetRs;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2018-1-19
 * 单据配置
 */
public interface OrderSetService {
    OrderSetRs orderSetDel(String id ) ;
    OrderSetRs orderSetById(String id );
    OrderSetRs orderSetSave( OrderSetBO OrderSetBO);
    OrderSetRs orderSetList(OrderSetRq rq);
}