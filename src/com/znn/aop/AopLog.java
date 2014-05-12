package com.znn.aop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.znn.json.controller.UserJson;

@Aspect
public class AopLog {
	static final Logger logger = 	LogManager.getLogger(UserJson.class);
	@Before("execution(* com.znn.*.controller.*(..))")
	public void before() {
		System.out.println("before");
		logger.info("before");
	}
	@Before("execution(* com.znn...(..))")
	public void before1() {
		System.out.println("before");
		logger.info("before");
	}
	@Before("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void before2() {
		System.out.println("before");
		logger.info("before");
	}
	@After("execution(public * *(..))")
	public void After(){
		System.out.println("After");
		logger.info("After");
	}
	/* @AfterThrowing(pointcut = "execution (* *(..))",throwing = "ex") 
    public void afterThrowing(Exception ex) {
		 logger.info("afterThrowing:"+ex.getMessage());
	}*/
	/*@Around("* *(..)")
	public void around(){
		logger.info("around");	
	}*/
}
