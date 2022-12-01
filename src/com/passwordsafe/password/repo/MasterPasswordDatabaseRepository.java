package com.passwordsafe.password.repo;

public class MasterPasswordDatabaseRepository implements MasterPasswordRepository {
    @Override
    public void StoreMasterPassword(String masterPassword) throws Exception {
        System.out.println("Master password saved to database!");
    }

    @Override
    public void setMasterPasswordPlain(String masterPw) {
        System.out.println("Plain master password set!");
    }

    @Override
    public boolean MasterPasswordIsEqualTo(String masterPw) {
        return false;
    }
}
