package com.hand.test;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hand.entity.Film;
import com.hand.event.ClosedEvent;
import com.hand.service.FilmService;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext
                (new String[] {"classpath:spring/spring-dao.xml", 
                        "classpath:spring/spring-service.xml"});
       
        FilmService filmService=(FilmService) context.getBean("filmService");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入电影名字(title)");
        String title =scanner.nextLine();
        System.out.println("请输入电影描述(description)");
        String description =scanner.nextLine();
        System.out.println("请输入语言id(language_id)");
        String languageId =scanner.nextLine();
        
        
        Film film=new Film(); 
        film.setTitle(title);
        film.setDescription(description);
        /*film.setRentalDuration(Byte.parseByte("6"));
        film.setRentalRate(4.99);*/
        if(languageId.matches("[0-9]+")){
            film.setLanguageId(Byte.parseByte(languageId));            
        }
        int i=filmService.insertFiml(film);
        
        System.out.println("插入的行数："+i);
       
        ContextStoppedEvent contextStoppedEvent = new ContextStoppedEvent(context);
        context.publishEvent(contextStoppedEvent);
        
    }

}
