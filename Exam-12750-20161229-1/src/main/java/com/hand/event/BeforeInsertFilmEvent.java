package com.hand.event;

import org.springframework.context.ApplicationEvent;

public class BeforeInsertFilmEvent extends ApplicationEvent{
    private String message;
    public BeforeInsertFilmEvent(String message) {
        super(message);
        this.message=message;
                
    }

}
