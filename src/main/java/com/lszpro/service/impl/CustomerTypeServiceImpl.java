package com.lszpro.service.impl;

import com.lszpro.model.bo.CustomerTypeBO;
import com.lszpro.model.rq.CustomerTypeRq;
import com.lszpro.service.CustomerTypeService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.CustomerTypeRs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {
    private static final   ConstantsUri CustomerType_list = new ConstantsUri("网点类型列表","/customerType/customerTypeList.php");
    private static final ConstantsUri CustomerType_info=new ConstantsUri("网点类型详情","/customerType/customerTypeById.php");
    private static final ConstantsUri CustomerType_save=new ConstantsUri("网点类型保存","/customerType/customerTypeSave.php");
    private static final ConstantsUri CustomerType_del= new ConstantsUri("网点类型删除","/customerType/customerTypeDel.php");
  @Override
    public CustomerTypeRs customerTypeDel( String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        CustomerTypeRs rs = SoaConnectionFactory.get( CustomerType_del, map, CustomerTypeRs.class);
        return rs;
    }

    @Override
    public CustomerTypeRs customerTypeById(String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        CustomerTypeRs rs = SoaConnectionFactory.get( CustomerType_info, map, CustomerTypeRs.class);
        return rs;
    }

    @Override
    public CustomerTypeRs customerTypeSave(CustomerTypeBO CustomerTypeBO) {
        CustomerTypeRs returnObj = SoaConnectionFactory.post( CustomerType_save, CustomerTypeBO, CustomerTypeRs.class);
        return returnObj;
    }

    @Override
    public CustomerTypeRs customerTypeList(CustomerTypeRq rq) {
        CustomerTypeRs rs = SoaConnectionFactory.get( CustomerType_list, rq, CustomerTypeRs.class);
        return rs;
    }


}