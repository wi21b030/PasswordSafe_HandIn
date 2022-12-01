package com.passwordsafe.password.repo;

public interface MasterPasswordRepository {
    void StoreMasterPassword(String masterPassword) throws Exception;
}
