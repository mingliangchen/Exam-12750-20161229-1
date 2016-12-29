package com.hand.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component("afterInsertFilmEventListener")
public class AfterInsertFilmEventListener implements ApplicationListener {

    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof AfterInsertFilmEvent){
            System.out.println("After Insert Film Data");
        }
    }
 
}
 