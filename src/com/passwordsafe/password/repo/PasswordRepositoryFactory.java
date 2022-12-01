package com.passwordsafe.password.repo;

import com.passwordsafe.Config;

import java.net.UnknownServiceException;

public class PasswordRepositoryFactory {
    public static MasterPasswordRepository create() throws UnknownServiceException {
        switch (Config.PW_REPO) {
            case "DB": return new MasterPasswordDatabaseRepository();
            case "FILE": return new MasterPasswordFileRepository("./master.pw");
        }

        throw new UnknownServiceException();
    }
}
