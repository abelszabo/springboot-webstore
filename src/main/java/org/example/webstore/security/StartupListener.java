package org.example.webstore.security;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener {

    @PostConstruct
    void postConstruct() {
        System.out.println("StartupListener PostConstruct done.");
    }

    @EventListener(ApplicationStartedEvent.class)
    public void onStarted() {
        System.out.println("Application started!");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        System.out.println("Application is ready!");
    }
}