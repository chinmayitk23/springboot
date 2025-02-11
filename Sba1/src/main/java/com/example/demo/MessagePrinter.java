package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class MessagePrinter {
    public void printMessage() {
        System.out.println("Spring Application Started Successfully Without Starters!");
    }
}
