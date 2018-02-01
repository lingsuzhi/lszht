package com.lszpro.service;

import com.lszpro.model.bo.ProductCompanyBO;
import com.lszpro.model.rq.ProductCompanyRq;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.ProductCompanyRs;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
public interface ProductCompanyService {
    ProductCompanyRs productCompanyDel(String id ) ;
    ProductCompanyRs productCompanyById(String id );
    ProductCompanyRs productCompanySave( ProductCompanyBO ProductCompanyBO);
    ProductCompanyRs productCompanyList(ProductCompanyRq rq);
}