package com.lszpro.service;

import com.lszpro.model.bo.TopicBO;
import com.lszpro.model.rq.TopicRq;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.TopicRs;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
public interface TopicService {
    TopicRs topicDel(String id ) ;
    TopicRs topicById(String id );
    TopicRs topicSave( TopicBO topicBO);
    TopicRs topicList(TopicRq rq);
}
