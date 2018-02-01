package com.lszpro.web;

import com.lszpro.common.LszUtil;

import com.lszpro.common.Utils;
import com.lszpro.model.bo.TopicBO;
import com.lszpro.model.rq.TopicRq;
import com.lszpro.service.TopicService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.TopicRs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2017-12-15
 */

@Controller
@RequestMapping(value = "/topic")
public class TopicController {

    protected static Logger log = LoggerFactory.getLogger(TopicController.class);
    @Autowired
    private TopicService topicService;
    @RequestMapping("/topicView.php")
    public String topicview(HttpServletRequest request, Model model, HttpSession session) {
        return "lsz/topic/list";
    }

    @RequestMapping("/topicEdit.php")
    public String topicEdit(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            TopicRs rs = topicService.topicById(id);
            model.addAttribute("obj", rs.getData());
        }
        return "lsz/topic/edit";
    }

    @RequestMapping("/topicList.php")
    @ResponseBody
    public ResponseEntity topicList(TopicRq rq, HttpServletRequest request, Model model, HttpSession session) {

        TopicRs rs = topicService.topicList(rq);
        if (rs.isSuccess()) {
            return LszUtil.returnList(rs.getDataList(), rs.getTotal());
        }
         return ResponseEntity.ok(rs);
    }

    @RequestMapping(value = "/topicSave.php", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse topicSave(@RequestBody TopicBO topicBO, HttpServletRequest request) {
        return topicService.topicSave(topicBO);
    }

    @RequestMapping("/topicById.php")
    @ResponseBody
    public BaseResponse topicById(@RequestParam(required = true) String id, HttpServletRequest request) {
        return topicService.topicById(id);
    }

    @RequestMapping("/topicDel.php")
    @ResponseBody
    public BaseResponse topicDel(@RequestParam(required = true) String id, HttpServletRequest request) {
         return topicService.topicDel(id);

    }
}