package com.lszpro.service;

import com.lszpro.model.bo.CustomerTypeBO;
import com.lszpro.model.rq.CustomerTypeRq;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.CustomerTypeRs;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
public interface CustomerTypeService {
    CustomerTypeRs customerTypeDel(String id ) ;
    CustomerTypeRs customerTypeById(String id );
    CustomerTypeRs customerTypeSave( CustomerTypeBO CustomerTypeBO);
    CustomerTypeRs customerTypeList(CustomerTypeRq rq);
}