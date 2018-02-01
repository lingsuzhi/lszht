package com.lszpro.controller.test;

import com.lszpro.common.LszUtil;
import com.lszpro.common.Utils;
import com.lszpro.entity.rq.TopicDiseasesRq;
import com.lszpro.entity.rs.TopicDiseasesRs;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Admin: lingsuzhi <554600654@qq.com.com> 
 * 时间: 2017-12-15
 */

@Controller
@RequestMapping(value = "/test/redis")
public class RedisController {//http://localhost:8586/ht/test/redis/get.do

	protected static Logger log = LoggerFactory.getLogger(RedisController.class);
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;

	@RequestMapping("/set.do")
	@ResponseBody
	public	ResponseEntity setRedis(HttpServletRequest request, Model model, HttpSession session) {
		stringRedisTemplate.opsForValue().set("redis111","testRedis");
		return ResponseEntity.ok(Utils.kv("data","设置完成"));

	}
	@RequestMapping("/get.do")
	@ResponseBody
	public	ResponseEntity getRedis(HttpServletRequest request, Model model, HttpSession session) {

		String s1 =  stringRedisTemplate.opsForValue().get("redis111" );
		return ResponseEntity.ok(Utils.kv("data",  s1));

	}
	@RequestMapping("/setObj.do")
	@ResponseBody
	public	ResponseEntity setRedisObj(HttpServletRequest request, Model model, HttpSession session) {
		ValueOperations<String, User1> operations=redisTemplate.opsForValue();
		User1 user = new User1(); //不能使用内部类
		user.setName("name1");
		user.setPwd("pwd1");
		operations.set("user1",user);
		return ResponseEntity.ok(Utils.kv("data","设置对象完成"));

	}
	@RequestMapping("/getObj.do")
	@ResponseBody
	public	ResponseEntity getRedisObj(HttpServletRequest request, Model model, HttpSession session) {
		ValueOperations<String, User1> operations=redisTemplate.opsForValue();
		User1 user =operations.get("user1");
		return ResponseEntity.ok(Utils.kv("data",  user.toString()));

	}

}