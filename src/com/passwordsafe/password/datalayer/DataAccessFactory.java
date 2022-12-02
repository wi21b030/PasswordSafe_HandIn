package com.passwordsafe.password.datalayer;

import com.passwordsafe.Config;
import com.passwordsafe.password.cipher.CipherFacility;

import java.net.UnknownServiceException;

public class DataAccessFactory {
    public static DataAccess create(String key) throws UnknownServiceException {
        switch (Config.PW_REPO) {
            case "DB": return new PasswordSafeEngineDatabase("@localhost:33060", key);
            case "FILE": return new PasswordSafeEngineFile("./passwords.pw", CipherFacility.create(key));
        }

        throw new UnknownServiceException();
    }
}
