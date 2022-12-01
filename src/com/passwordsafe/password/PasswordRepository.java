package com.passwordsafe.password;

import java.io.IOException;

public interface PasswordRepository {
    void StoreMasterPassword(String masterPassword) throws Exception;
}
