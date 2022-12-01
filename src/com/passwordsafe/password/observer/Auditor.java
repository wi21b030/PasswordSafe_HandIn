package com.passwordsafe.password.observer;

// Auditor class with concrete implementation of Subscriber interface
public class Auditor implements Subscriber{
    @Override
    public void notify(String message) {
        System.out.println("[PasswordSafeEngine]: " + message);
    }
}
