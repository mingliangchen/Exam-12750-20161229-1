package com.hand.event;

import org.springframework.context.ApplicationEvent;

public class AfterInsertFilmEvent extends ApplicationEvent{
    private String  message;
    public AfterInsertFilmEvent(String message) {
        super(message);
        this.message=message;
    }

} 
