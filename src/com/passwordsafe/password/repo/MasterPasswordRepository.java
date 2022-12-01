package com.passwordsafe.password.repo;

// Interface for general repository
public interface MasterPasswordRepository {
    void StoreMasterPassword(String masterPassword) throws Exception;

    void setMasterPasswordPlain(String masterPw) throws Exception;

    boolean MasterPasswordIsEqualTo(String masterPw) throws Exception;
}
