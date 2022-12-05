package com.passwordsafe.password.logic;
import com.passwordsafe.password.PasswordInfo;
import com.passwordsafe.password.cipher.CipherFacility;
import com.passwordsafe.password.datalayer.DataAccess;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

// both MasterPasswordRepository and PasswordSafeEngine now access concrete methods via reference of
// the DataAccess interface
public class PasswordSafeEngine {
    private final DataAccess dataAccess;
    private final CipherFacility cipherFacility;

    public PasswordSafeEngine(DataAccess dataAccess, CipherFacility cipherFacility) {
        this.dataAccess = dataAccess;
        this.cipherFacility = cipherFacility;
    }
    public String[] GetStoredPasswords() throws Exception {
        return this.dataAccess.getStoredPasswords();
    }
    public void AddNewPassword(PasswordInfo info) throws IOException, Exception {
        this.dataAccess.addNewPassword(info.getName(),this.cipherFacility.Encrypt(info.getPlain()));
    }
    public void DeletePassword(String passwordName) throws Exception, IOException {
        this.dataAccess.deletePassword(passwordName);
    }
    public String GetPassword(String passwordName) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        char[] buffer = this.dataAccess.getPassword(passwordName,this);
        return this.cipherFacility.Decrypt(new String(buffer));
    }

    public void UpdatePassword(PasswordInfo info) throws Exception {
        this.dataAccess.updatePassword(info);
    }

    public void secondEntryWrong(String second){
        this.dataAccess.secondWrong();
    }
}
