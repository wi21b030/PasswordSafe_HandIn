package com.passwordsafe.password.datalayer;

import com.passwordsafe.password.PasswordInfo;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface DataAccess {
    String[] GetStoredPasswords() throws Exception;
    void AddNewPassword(PasswordInfo info) throws IOException, Exception;
    void DeletePassword(String passwordName) throws Exception;
    String GetPassword(String passwordName) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;
    void UpdatePassword(PasswordInfo info) throws Exception;
}
