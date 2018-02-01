package com.lszpro.service;

import com.lszpro.model.bo.ProductTypeBO;
import com.lszpro.model.rq.ProductTypeRq;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.ProductTypeRs;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
public interface ProductTypeService {
    ProductTypeRs productTypeDel(String id ) ;
    ProductTypeRs productTypeById(String id );
    ProductTypeRs productTypeSave( ProductTypeBO ProductTypeBO);
    ProductTypeRs productTypeList(ProductTypeRq rq);
}