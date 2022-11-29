package com.passwordsafe.factory.repo;

public class DatabaseRepository implements PasswordRepository{
    @Override
    public void save(String service, String password) {
        System.out.println("Saving " + password +  " for " + service + " in database");
    }

    @Override
    public String read(String service) {
        return "read test database";
    }
}
