package com.passwordsafe;

import com.passwordsafe.factory.logger.Log;
import com.passwordsafe.factory.logger.LoggerFactory;
import com.passwordsafe.factory.logger.LoggerType;
import com.passwordsafe.password.PasswordInfo;
import com.passwordsafe.password.PasswordSafeEngine;
import com.passwordsafe.password.cipher.CipherFacility;
import com.passwordsafe.password.repo.MasterPasswordRepository;
import com.passwordsafe.password.repo.PasswordRepositoryFactory;

import java.io.File;
import java.net.UnknownServiceException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Log log = LoggerFactory.getInstance();

    // instantiating a master password repository, depending on config setting we receive either
    // database repository or file repository
    // currently we only have the file repository completely implemented
    // but this shows how easily now we can adapt and add/change different type of repositories
    private static final MasterPasswordRepository masterRepository;
    static {
        try {
            masterRepository = PasswordRepositoryFactory.create();
        } catch (UnknownServiceException e) {
            throw new RuntimeException(e);
        }
    }

    private static PasswordSafeEngine passwordSafeEngine;

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Passwordsafe");

        boolean abort = false;
        boolean locked = true;
        Scanner read = new Scanner(System.in);
        while (!abort) {
            // added new case 'update single (4)' using UpdatePassword method of PassWordSafeEngine
            System.out.println("Enter master (1), show all (2), show single (3), update single (4), add (5), delete(6), set new master (7), Abort (0)");
            int input = read.nextInt();
            switch (input) {
                case 0: {
                    abort = true;
                    break;
                }
                case 1: {
                    System.out.println("Enter master password");
                    String masterPw = read.next();
                    locked = !masterRepository.MasterPasswordIsEqualTo(masterPw);
                    if (!locked) {
                        passwordSafeEngine = new PasswordSafeEngine("./passwords.pw", CipherFacility.create(masterPw));
                        System.out.println("unlocked");
                    } else {
                        System.out.println("master password did not match ! Failed to unlock.");
                    }
                    break;
                }
                case 2: {
                    if (locked) {
                        System.out.println("Please unlock first by entering the master password.");
                    } else {
                        Arrays.stream(passwordSafeEngine.GetStoredPasswords()).forEach(pw -> System.out.println(pw));
                    }
                    break;
                }
                case 3: {
                    if (locked) {
                        System.out.println("Please unlock first by entering the master password.");
                    } else {
                        System.out.println("Enter password name");
                        String passwordName = read.next();
                        System.out.println(passwordSafeEngine.GetPassword(passwordName));
                    }
                    break;
                }
                case 4: {
                    //update single password using some pre-existing methods
                    if (locked) {
                        System.out.println("Please unlock first by entering the master password.");
                    } else {
                        System.out.println("Enter name of password");
                        String passwordName = read.next();
                        System.out.println("Enter new password");
                        String newPassword = read.next();
                        passwordSafeEngine.UpdatePassword(new PasswordInfo(newPassword, passwordName));
                    }
                    break;
                }
                case 5: {
                    if (locked) {
                        System.out.println("Please unlock first by entering the master password.");
                    } else {
                        System.out.println("Enter new name of password");
                        String passwordName = read.next();
                        System.out.println("Enter password");
                        String password = read.next();
                        passwordSafeEngine.AddNewPassword(new PasswordInfo(password, passwordName));
                    }
                    break;
                }
                case 6: {
                    if (locked) {
                        System.out.println("Please unlock first by entering the master password.");
                    } else {
                        System.out.println("Enter password name");
                        String passwordName = read.next();
                        passwordSafeEngine.DeletePassword(passwordName);
                    }
                    break;
                }
                case 7: {
                    locked = true;
                    passwordSafeEngine = null;
                    System.out.println("Enter new master password ! (Warning you will loose all already stored passwords)");
                    String masterPw = read.next();
                    System.out.println("Enter new master password again !");
                    String second = read.next();
                    // Simple while loop to compare and check if second entry matches first
                    while(!masterPw.equals(second)){
                        System.out.println("Password does not match the first entry !");
                        log.info(LoggerType.ERROR, "Password incorrect!");
                        System.out.println("Enter new master password again !");
                        second = read.next();
                    }
                    // Once the second entry is correct, we make use of the logger to send a message to the console
                    log.info(LoggerType.INFO, "New master password has been set !");
                    masterRepository.setMasterPasswordPlain(masterPw);
                    // urgent hotfix delete old passwords after changing the master
                    File oldPasswords = new File("./passwords.pw");
                    if (oldPasswords.isDirectory())
                    {
                        String[] children = oldPasswords.list();
                        for (int i=0; i<children.length; i++) {
                            (new File(oldPasswords, children[i])).delete();
                        }
                    }
                    // The directory is now empty or this is a file so delete it
                    oldPasswords.delete();
                    break;
                }
                default:
                    System.out.println("Invalid input");
            }
        }

        System.out.println("Good bye!");
    }
}
