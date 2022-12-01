package com.passwordsafe.password.observer;

import java.util.ArrayList;
import java.util.List;
// Publisher class which sends the message
public class PasswordSafeEnginePublisher {
    private final List<Subscriber> subscribers;

    public PasswordSafeEnginePublisher() {
        this.subscribers = new ArrayList<>();
    }
    public void addSubscriber(Subscriber sub){
        subscribers.add(sub);
    }

    public void removeSubscriber(Subscriber sub){
        subscribers.remove(sub);
    }

    public void send(String message){
        for (Subscriber subscriber : subscribers) {
            subscriber.notify(message);
        }
    }
}
