package com.hand.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
@Component("StartEvent")
public class StartEvent implements ApplicationListener<ContextRefreshedEvent>{

    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent()==null) {//root application context 没有parent，他就是老大.  
            
            System.out.println("Context Start");
        }
        
    }
 
}
