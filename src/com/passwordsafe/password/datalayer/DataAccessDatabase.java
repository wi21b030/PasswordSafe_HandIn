package com.passwordsafe.password.datalayer;

import com.passwordsafe.password.PasswordInfo;
import com.passwordsafe.password.logic.PasswordSafeEngine;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


// simple example of implementation with a database storage system, can be expanded/implemented easily
// without destroying/revamping central logic
public class DataAccessDatabase implements DataAccess{
    @Override
    public String[] getStoredPasswords() throws Exception {
        return new String[0];
    }

    @Override
    public void addNewPassword(String passwordName, String cypher) throws IOException, Exception {

    }

    @Override
    public void deletePassword(String passwordName) throws Exception, IOException {

    }

    @Override
    public char[] getPassword(String passwordName, PasswordSafeEngine passwordSafeEngine) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return new char[0];
    }

    @Override
    public void updatePassword(PasswordInfo info) throws Exception {

    }

    @Override
    public String getMasterPasswordFromFile() throws Exception {
        return null;
    }

    @Override
    public void storeMasterPasswordToFile(String masterPassword) throws Exception {

    }
}
