package com.project.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	private Logger myLogger=Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.project.controller.*.*(..))")
	private void forControllerpackage(){
		
	}
	
	@Pointcut("execution(* com.project.dao.*.*(..))")
	private void forDaopackage(){
		
	}
	
	@Pointcut("execution(* com.project.service.*.*(..))")
	private void forServicepackage(){
		
	}
	@Pointcut("forControllerpackage() || forDaopackage() || forServicepackage()")
	private void forAppFlow(){
		
	}
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint){
		String method=theJoinPoint.getSignature().toShortString();
		
		myLogger.info("======>@Before: calling method: " + method );
		
		Object[] args=theJoinPoint.getArgs();
		for(Object temparg:args){
		myLogger.info("=====>argument: " + temparg);
		}
	}
	
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="result"
			)
	public void afterReturning(JoinPoint theJoinPoint,  Object result){
	
	String method=theJoinPoint.getSignature().toShortString();
		
		myLogger.info("======>@After: calling method: " + method );
		
		myLogger.info("=====>result: " + result);
		
	}
}
 