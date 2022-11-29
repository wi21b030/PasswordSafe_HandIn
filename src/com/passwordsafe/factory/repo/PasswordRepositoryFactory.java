package com.passwordsafe.factory.repo;

import com.passwordsafe.Config;

import java.net.UnknownServiceException;

public class PasswordRepositoryFactory {

    public static PasswordRepository create() throws UnknownServiceException {
        switch (Config.PW_REPO) {
            case "DB":
                return new DatabaseRepository();
            case "FILE":
                return new FileRepository();
        }
        throw new UnknownServiceException();
    }
}
