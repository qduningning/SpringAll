package com.znn.rest.controller;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.znn.dao.UserDao;
import com.znn.vo.User;
/**
 * RestFul 返回视图
 *doc:http://docs.spring.io/spring-framework/docs/current/spring-framework-reference/html/mvc.html#mvc-introduction
 * @author RANDY.ZHANG 
 * 2014-5-7
 */
@Controller
public class UserController {
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
	UserDao dao = (UserDao) applicationContext.getBean("userDao");
	
	@RequestMapping("/hi")
	public String helloWorld(Model model) {
		model.addAttribute("msg", "Hello HelloWorld");
		return "hello";
//		return "redirect:hello.jsp";
	}
	@RequestMapping("/user/{id:\\d+}")
	public String getUser(Model model,@PathVariable("id") int userid) {
		User user;
		user = dao.queryAUser(userid);
		if (user == null) {
			model.addAttribute("tip", "<font color='red'>用户不存在！</font>");
		}else {
			model.addAttribute("tip", user.toString());
		}
		return "user";
	}
	@RequestMapping(value = "/user/*",method=RequestMethod.GET)
	public String getAllUser(Model model) {
		List<User> list = dao.query();
		String tipString = "";
		for (int i = 0; i < list.size(); i++) {
			tipString += list.get(i).toString()+"<br />";
		}
		model.addAttribute("tip",tipString);
		return "user";
	}
	@RequestMapping(value = "/user",method=RequestMethod.GET)
	public String getAUser(Model model,
			@RequestParam(value="id",required=false,defaultValue="1") int userid,
			@RequestHeader("user-agent") String agent) {
		User user;
		user = dao.queryAUser(userid);
		if (user == null) {
			model.addAttribute("tip", "<font color='red'>用户不存在！</font>");
		}else {
			model.addAttribute("tip", user.toString());
		}
		System.out.println(agent);
		return "user";
	}
}
