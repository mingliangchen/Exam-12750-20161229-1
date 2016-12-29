package com.hand.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component("beforeInsertFilmEventListener")
public class BeforeInsertFilmEventListener implements ApplicationListener {

    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof BeforeInsertFilmEvent){
            System.out.println("Before Insert Film Data");
        }
        
    }

}
