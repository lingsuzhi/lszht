package com.lszpro.service;

import com.lszpro.model.bo.ProductBO;
import com.lszpro.model.rq.ProductRq;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.ProductRs;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
public interface ProductService {
    ProductRs productDel(String id ) ;
    ProductRs productById(String id );
    ProductRs productSave( ProductBO ProductBO);
    ProductRs productList(ProductRq rq);
}