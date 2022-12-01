package com.passwordsafe.password.repo;

import com.passwordsafe.Config;

import java.net.UnknownServiceException;

// Factory for creating repository based on config
public class PasswordRepositoryFactory {
    public static MasterPasswordRepository create() throws UnknownServiceException {
        switch (Config.PW_REPO) {
            case "DB": return new MasterPasswordDatabaseRepository("@localhost:33060");
            case "FILE": return new MasterPasswordFileRepository("./master.pw");
        }

        throw new UnknownServiceException();
    }
}
