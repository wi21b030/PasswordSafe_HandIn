package com.passwordsafe.password.observer;

public class Auditor implements Subscriber{
    @Override
    public void notify(String message) {
        System.out.println( "[PasswordSafeEngine]: " + message);
    }
}
