package com.lszpro.service.impl;

import com.lszpro.model.bo.ProductCompanyBO;
import com.lszpro.model.rq.ProductCompanyRq;
import com.lszpro.service.ProductCompanyService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.ProductCompanyRs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
@Service
public class ProductCompanyServiceImpl implements ProductCompanyService {
    private static final   ConstantsUri ProductCompany_list = new ConstantsUri("商品单位列表","/productCompany/productCompanyList.php");
    private static final ConstantsUri ProductCompany_info=new ConstantsUri("商品单位详情","/productCompany/productCompanyById.php");
    private static final ConstantsUri ProductCompany_save=new ConstantsUri("商品单位保存","/productCompany/productCompanySave.php");
    private static final ConstantsUri ProductCompany_del= new ConstantsUri("商品单位删除","/productCompany/productCompanyDel.php");
  @Override
    public ProductCompanyRs productCompanyDel( String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        ProductCompanyRs rs = SoaConnectionFactory.get( ProductCompany_del, map, ProductCompanyRs.class);
        return rs;
    }

    @Override
    public ProductCompanyRs productCompanyById(String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        ProductCompanyRs rs = SoaConnectionFactory.get( ProductCompany_info, map, ProductCompanyRs.class);
        return rs;
    }

    @Override
    public ProductCompanyRs productCompanySave(ProductCompanyBO ProductCompanyBO) {
        ProductCompanyRs returnObj = SoaConnectionFactory.post( ProductCompany_save, ProductCompanyBO, ProductCompanyRs.class);
        return returnObj;
    }

    @Override
    public ProductCompanyRs productCompanyList(ProductCompanyRq rq) {
        ProductCompanyRs rs = SoaConnectionFactory.get( ProductCompany_list, rq, ProductCompanyRs.class);
        return rs;
    }


}