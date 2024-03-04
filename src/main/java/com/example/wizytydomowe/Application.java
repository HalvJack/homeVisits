package com.example.wizytydomowe;

import com.example.wizytydomowe.HereApi.TokenRequester;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        TokenRequester bean = run.getBean(TokenRequester.class);
        bean.requestAccessToken();
    }

}