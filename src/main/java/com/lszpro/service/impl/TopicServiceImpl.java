package com.lszpro.service.impl;

import com.lszpro.model.bo.TopicBO;
import com.lszpro.model.rq.TopicRq;
import com.lszpro.service.TopicService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.TopicRs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
@Service
public class TopicServiceImpl implements TopicService {
    private static final   ConstantsUri Topic_list = new ConstantsUri("热点话题列表","/topic/topicList.php");
    private static final ConstantsUri Topic_info=new ConstantsUri("热点话题详情","/topic/topicById.php");
    private static final ConstantsUri Topic_save=new ConstantsUri("热点话题保存","/topic/topicSave.php");
    private static final ConstantsUri Topic_del= new ConstantsUri("热点话题删除","/topic/topicDel.php");

    public TopicRs topicDel( String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        TopicRs rs = SoaConnectionFactory.get( Topic_del, map, TopicRs.class);
        return rs;
    }

    @Override
    public TopicRs topicById(String id ) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        TopicRs rs = SoaConnectionFactory.get( Topic_info, map, TopicRs.class);
        return rs;
    }

    @Override
    public TopicRs topicSave(TopicBO topicBO) {
        TopicRs returnObj = SoaConnectionFactory.post( Topic_save, topicBO, TopicRs.class);
        return returnObj;
    }

    @Override
    public TopicRs topicList(TopicRq rq) {
        TopicRs rs = SoaConnectionFactory.get( Topic_list, rq, TopicRs.class);
        return rs;
    }


}
