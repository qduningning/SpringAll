package com.znn.json.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znn.dao.UserDao;
import com.znn.vo.User;
/**
 * 返回@ResponseBody需要配置HttpMessageConverters
 *
 * @author RANDY.ZHANG 
 * 2014-5-7
 */
@Controller
public class UserJson {
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
	UserDao dao = (UserDao) applicationContext.getBean("userDao");
	
	@RequestMapping("/hi")
	public @ResponseBody String helloWorld(Model model) {
//		model.addAttribute("msg", "Hello HelloWorld");
		return "hello world";
//		return "redirect:hello.jsp";
	}
	
	@RequestMapping("/user/{id:\\d+}")
	public @ResponseBody User getUser(Model model,@PathVariable int id) {
		User user;
		user = dao.queryAUser(id);
		if (user == null) {
			model.addAttribute("tip", "<font color='red'>用户不存在！</font>");
		}else {
			model.addAttribute("tip", user.toString());
		}
		return user;
	}
	@RequestMapping(value = {"/user/*","/user"},method=RequestMethod.GET)//,produces="application/json" 406
	public @ResponseBody List<User> getAllUser(Model model) {
		List<User> list = dao.query();
		String tipString = "";
		for (int i = 0; i < list.size(); i++) {
			tipString += list.get(i).toString()+"<br />";
		}
		model.addAttribute("tip",tipString);
		return list;
	}
	@RequestMapping(value = "/user/aaa",method=RequestMethod.GET)
	public  @ResponseBody List<User> getTest(Model model,HttpServletResponse response, HttpServletRequest request) {
		List<User> list = dao.query();
		String tipString = "";
		for (int i = 0; i < list.size(); i++) {
			tipString += list.get(i).toString()+"<br />";
		}
		model.addAttribute("tip",tipString);
		return list;
	}
}
