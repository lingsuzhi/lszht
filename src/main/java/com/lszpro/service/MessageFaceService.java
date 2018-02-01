package com.lszpro.service;

import com.lszpro.model.bo.MessageFaceBO;
import com.lszpro.model.rq.MessageFaceRq;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.MessageFaceRs;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
public interface MessageFaceService {
    MessageFaceRs messageFaceDel(String id ) ;
    MessageFaceRs messageFaceById(String id );
    MessageFaceRs messageFaceSave( MessageFaceBO MessageFaceBO);
    MessageFaceRs messageFaceList(MessageFaceRq rq);
}