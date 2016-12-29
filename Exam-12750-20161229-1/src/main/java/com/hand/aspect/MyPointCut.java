package com.hand.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.hand.event.AfterInsertFilmEvent;
import com.hand.event.BeforeInsertFilmEvent;


@Aspect  
@Component
public class MyPointCut implements ApplicationContextAware{
    private ApplicationContext ctx=null;
    public ApplicationContext getCtx() {
        return ctx;
    } 
    public void setCtx(ApplicationContext ctx) {
        this.ctx = ctx;
    }
	@Pointcut("execution(* com.hand.service.*.*(..))")
	//@Pointcut(value="")
	public void mp(){
	    
	}
	@Before(value="mp()")
	public void before(){
	    String message="before";
		BeforeInsertFilmEvent event =new BeforeInsertFilmEvent(message);
		ctx.publishEvent(event);
	}
	
	@After(value="mp()")
	public void after(){
	    String message="after";
        AfterInsertFilmEvent event =new AfterInsertFilmEvent(message);
        ctx.publishEvent(event);
	}
	 //@Around(value="mp()")    
	    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {    
	        long begin = System.currentTimeMillis();  
	        Object o = pjp.proceed();    
	        long end = System.currentTimeMillis();  
	        System.out.println("方法运行时间："+(end-begin) );
	        return o;    
	    }
	//@AfterReturning(value="mp()")
	public void afterReturning(){
		System.out.println("调用了afterReturning 方法");
	}

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
        
    }
	

}
