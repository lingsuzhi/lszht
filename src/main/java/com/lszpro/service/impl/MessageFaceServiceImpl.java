package com.lszpro.service.impl;

import com.lszpro.model.bo.MessageFaceBO;
import com.lszpro.model.rq.MessageFaceRq;
import com.lszpro.service.MessageFaceService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.MessageFaceRs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
@Service
public class MessageFaceServiceImpl implements MessageFaceService {
    private static final   ConstantsUri MessageFace_list = new ConstantsUri("接口消息列表","/messageFace/messageFaceList.php");
    private static final ConstantsUri MessageFace_info=new ConstantsUri("接口消息详情","/messageFace/messageFaceById.php");
    private static final ConstantsUri MessageFace_save=new ConstantsUri("接口消息保存","/messageFace/messageFaceSave.php");
    private static final ConstantsUri MessageFace_del= new ConstantsUri("接口消息删除","/messageFace/messageFaceDel.php");
  @Override
    public MessageFaceRs messageFaceDel( String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        MessageFaceRs rs = SoaConnectionFactory.get( MessageFace_del, map, MessageFaceRs.class);
        return rs;
    }

    @Override
    public MessageFaceRs messageFaceById(String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        MessageFaceRs rs = SoaConnectionFactory.get( MessageFace_info, map, MessageFaceRs.class);
        return rs;
    }

    @Override
    public MessageFaceRs messageFaceSave(MessageFaceBO MessageFaceBO) {
        MessageFaceRs returnObj = SoaConnectionFactory.post( MessageFace_save, MessageFaceBO, MessageFaceRs.class);
        return returnObj;
    }

    @Override
    public MessageFaceRs messageFaceList(MessageFaceRq rq) {
        MessageFaceRs rs = SoaConnectionFactory.get( MessageFace_list, rq, MessageFaceRs.class);
        return rs;
    }


}