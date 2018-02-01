package com.lszpro.service.impl;

import com.lszpro.model.bo.ProductTypeBO;
import com.lszpro.model.rq.ProductTypeRq;
import com.lszpro.service.ProductTypeService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.ProductTypeRs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    private static final   ConstantsUri ProductType_list = new ConstantsUri("商品类型列表","/productType/productTypeList.php");
    private static final ConstantsUri ProductType_info=new ConstantsUri("商品类型详情","/productType/productTypeById.php");
    private static final ConstantsUri ProductType_save=new ConstantsUri("商品类型保存","/productType/productTypeSave.php");
    private static final ConstantsUri ProductType_del= new ConstantsUri("商品类型删除","/productType/productTypeDel.php");
  @Override
    public ProductTypeRs productTypeDel( String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        ProductTypeRs rs = SoaConnectionFactory.get( ProductType_del, map, ProductTypeRs.class);
        return rs;
    }

    @Override
    public ProductTypeRs productTypeById(String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        ProductTypeRs rs = SoaConnectionFactory.get( ProductType_info, map, ProductTypeRs.class);
        return rs;
    }

    @Override
    public ProductTypeRs productTypeSave(ProductTypeBO ProductTypeBO) {
        ProductTypeRs returnObj = SoaConnectionFactory.post( ProductType_save, ProductTypeBO, ProductTypeRs.class);
        return returnObj;
    }

    @Override
    public ProductTypeRs productTypeList(ProductTypeRq rq) {
        ProductTypeRs rs = SoaConnectionFactory.get( ProductType_list, rq, ProductTypeRs.class);
        return rs;
    }


}