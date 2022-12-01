package com.passwordsafe.password.repo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// changed the MasterPasswordRepository to MasterPasswordFileRepository
public class MasterPasswordFileRepository implements MasterPasswordRepository {
    private String masterPasswordPath;

    public MasterPasswordFileRepository(String masterPasswordPath) {
        this.masterPasswordPath = masterPasswordPath;
    }
    public void setMasterPasswordPlain(String masterPassword) throws Exception {
        this.StoreMasterPassword(masterPassword);
    }
    public String getMasterPasswordPlain() throws Exception {
        return this.GetMasterPasswordFromFile();
    }
    public boolean MasterPasswordIsEqualTo(String masterPassword) throws Exception {
        return masterPassword.equals(this.GetMasterPasswordFromFile());
    }
    private String GetMasterPasswordFromFile() throws Exception {
        File passwordFile = new File(this.masterPasswordPath);
        char[] buffer = null;
        if (passwordFile.exists()) {
            FileReader reader = null;
            try {
                buffer = new char[(int)passwordFile.length()];
                reader = new FileReader(passwordFile);
                reader.read(buffer);
            }
            finally {
                if (reader != null) { try { reader.close(); } catch (IOException ex) { } };
            }
        }
        return buffer == null ? null : new String(buffer);
    }
    @Override
    public void StoreMasterPassword(String masterPassword) throws Exception {
        FileWriter writer = null;
        try {
            writer = new FileWriter(this.masterPasswordPath);
            writer.write(masterPassword);
            // added print line to show when master password is saved to file
            System.out.println("Password saved to file");
        } finally {
            if (writer != null) try { writer.close(); } catch (IOException ignore) {}
        }
    }
}
