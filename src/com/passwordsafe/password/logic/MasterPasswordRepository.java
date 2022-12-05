package com.passwordsafe.password.logic;

import com.passwordsafe.password.datalayer.DataAccess;

// both MasterPasswordRepository and PasswordSafeEngine now access concrete methods via reference of
// the DataAccess interface
public class MasterPasswordRepository{
    private final DataAccess dataAccess;

    public MasterPasswordRepository(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }
    public void setMasterPasswordPlain(String masterPassword) throws Exception {
        this.StoreMasterPasswordToFile(masterPassword);
    }
    public String getMasterPasswordPlain() throws Exception {
        return this.dataAccess.getMasterPasswordFromFile();
    }
    public boolean MasterPasswordIsEqualTo(String masterPassword) throws Exception {
        return masterPassword.equals(this.dataAccess.getMasterPasswordFromFile());
    }
    private String GetMasterPasswordFromFile() throws Exception {
       return this.dataAccess.getMasterPasswordFromFile();
    }
    private void StoreMasterPasswordToFile(String masterPassword) throws Exception {
        this.dataAccess.storeMasterPasswordToFile(masterPassword);
    }
}
