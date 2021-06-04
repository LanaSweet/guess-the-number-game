package com.gromivchuk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        log.info("Guess the number game");

        //create context (container)
        //ConfigurableApplicationContext is an interface
        //new ClassPathXmlApplicationContext(CONFIG_LOCATION); this instantiates a container
        ConfigurableApplicationContext context = new
                AnnotationConfigApplicationContext(AppConfig.class);

        //get the bean from the container
        NumberGenerator numberGenerator = context.getBean( NumberGenerator.class);

        //call method next() to get a random number
        int number = numberGenerator.next();

        log.info("number = {}", number);
        Game game = context.getBean( Game.class);
//        game.reset();

        //close context
        context.close();
    }
}
