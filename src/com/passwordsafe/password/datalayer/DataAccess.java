package com.passwordsafe.password.datalayer;

import com.passwordsafe.password.PasswordInfo;
import com.passwordsafe.password.logic.PasswordSafeEngine;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface DataAccess {
    String[] getStoredPasswords() throws Exception;
    void addNewPassword(String passwordName, String cypher) throws IOException, Exception;
    void deletePassword(String passwordName) throws Exception, IOException;
    char[] getPassword(String passwordName, PasswordSafeEngine passwordSafeEngine) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;
    void updatePassword(PasswordInfo info) throws Exception;
    String getMasterPasswordFromFile() throws Exception;
    void storeMasterPasswordToFile(String masterPassword) throws Exception;
}
