package com.passwordsafe.factory.repo;

public interface PasswordRepository {
    void save (String service, String password);
    String read(String service);
}
