package com.passwordsafe.password.datalayer;

import com.passwordsafe.password.PasswordInfo;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class PasswordSafeEngineDatabase implements DataAccess{

    private String path;
    private String key;

    public PasswordSafeEngineDatabase(String path, String key) {
        this.path = path;
        this.key = key;
    }

    @Override
    public String[] GetStoredPasswords() throws Exception {
        return new String[0];
    }

    @Override
    public void AddNewPassword(PasswordInfo info) throws IOException, Exception {
        System.out.println(info.getName() + " added to database");
    }

    @Override
    public void DeletePassword(String passwordName) throws Exception {
        System.out.println(passwordName + " deleted from database");
    }

    @Override
    public String GetPassword(String passwordName) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return "The password of " + passwordName + " is: this!";
    }

    @Override
    public void UpdatePassword(PasswordInfo info) throws Exception {
        System.out.println(info.getName() + " updated in database");
    }
}
