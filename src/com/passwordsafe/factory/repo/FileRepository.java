package com.passwordsafe.factory.repo;

public class FileRepository implements PasswordRepository{
    @Override
    public void save(String service, String password) {
        System.out.println("Saving " + password +  " for " + service + " in file");
    }

    @Override
    public String read(String service) {
        return "read test file";
    }
}
