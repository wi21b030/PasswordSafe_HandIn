package com.passwordsafe.password.repo;

// simple MasterPasswordDatabaseRepository to show simple implementation
public class MasterPasswordDatabaseRepository implements MasterPasswordRepository {
    private String databaseConnection;

    public MasterPasswordDatabaseRepository(String databaseConnection){
        this.databaseConnection = databaseConnection;
    }
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

    public String getMasterPasswordPlain() throws Exception {
        return this.GetMasterPasswordFromDatabase();
    }

    private String GetMasterPasswordFromDatabase() {
        return "password";
    }

}
