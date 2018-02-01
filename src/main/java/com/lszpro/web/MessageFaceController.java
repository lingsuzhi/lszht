package com.lszpro.web;

import com.lszpro.common.LszUtil;

import com.lszpro.common.Utils;
import com.lszpro.model.bo.MessageFaceBO;
import com.lszpro.model.rq.MessageFaceRq;
import com.lszpro.service.MessageFaceService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.MessageFaceRs;
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
@RequestMapping(value = "/messageFace")
public class MessageFaceController {

    protected static Logger log = LoggerFactory.getLogger(MessageFaceController.class);
    @Autowired
    private MessageFaceService messageFaceService;
    @RequestMapping("/messageFaceView.php")
    public String messageFaceview(HttpServletRequest request, Model model, HttpSession session) {
        return "lsz/MessageFace_list";
    }

    @RequestMapping("/messageFaceEdit.php")
    public String messageFaceEdit(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            MessageFaceRs rs = messageFaceService.messageFaceById(id);
            model.addAttribute("obj", rs.getData());
        }
        return "lsz/MessageFace_edit";
    }

    @RequestMapping("/messageFaceList.php")
    @ResponseBody
    public ResponseEntity messageFaceList(MessageFaceRq rq, HttpServletRequest request, Model model, HttpSession session) {

        MessageFaceRs rs = messageFaceService.messageFaceList(rq);
        if (rs.isSuccess()) {
            return LszUtil.returnList(rs.getDataList(), rs.getTotal());
        }
         return ResponseEntity.ok(rs);
    }

    @RequestMapping(value = "/messageFaceSave.php", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse messageFaceSave(@RequestBody MessageFaceBO messageFaceBO, HttpServletRequest request) {
        return messageFaceService.messageFaceSave(messageFaceBO);
    }

    @RequestMapping("/messageFaceById.php")
    @ResponseBody
    public BaseResponse messageFaceById(@RequestParam(required = true) String id, HttpServletRequest request) {
        return messageFaceService.messageFaceById(id);
    }

    @RequestMapping("/messageFaceDel.php")
    @ResponseBody
    public BaseResponse messageFaceDel(@RequestParam(required = true) String id, HttpServletRequest request) {
         return messageFaceService.messageFaceDel(id);

    }
}