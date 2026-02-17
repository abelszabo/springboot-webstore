package org.example.webstore.security;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener2 implements ApplicationListener<ApplicationStartedEvent> {

    @PostConstruct
    void postConstruct() {
        System.out.println("StartupListener2 PostConstruct done.");
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("ApplicationStartedEvent fired!");
    }
}