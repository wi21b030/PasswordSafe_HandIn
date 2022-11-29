package com.passwordsafe.factory.logger;

public class Logger implements Log {
    //Implementation of method info defined in the Log Interface
    @Override
    public void info(LoggerType type, String message){
        System.out.println(type.toString() + ": " + message);
    }
}
