package com.passwordsafe.password.repo;

public class MasterPasswordDatabaseRepository implements MasterPasswordRepository {
    @Override
    public void StoreMasterPassword(String masterPassword) throws Exception {
        System.out.println("Master password saved to database!");
    }
}
