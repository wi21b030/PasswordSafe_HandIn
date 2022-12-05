package com.passwordsafe.password.datalayer;

import com.passwordsafe.password.PasswordInfo;
import com.passwordsafe.password.logic.PasswordSafeEngine;
import com.passwordsafe.password.observer.Auditor;
import com.passwordsafe.password.observer.PasswordSafeEnginePublisher;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// concrete implementation of interface methods for file storage of master password and normal passwords
public class DataAccessFile implements DataAccess {

    private final String path;
    private final String masterPath;
    private final PasswordSafeEnginePublisher publisher = new PasswordSafeEnginePublisher();

    public DataAccessFile(String path, String masterPath) {
        this.path = path;
        this.masterPath = masterPath;
        this.publisher.addSubscriber(new Auditor());
    }


    @Override
    public String[] getStoredPasswords() throws Exception {
        File directory = new File(path);
        if (!directory.isDirectory() && !directory.mkdir()) {
            throw new Exception("Unable to create directory");
        }
        List<File> files = Arrays.asList(directory.listFiles());
        return files.stream()
                .filter(s -> s.getName().endsWith(".pw"))
                .map(f -> f.getName().split("\\.")[0])
                .collect(Collectors.toList()).toArray(new String[0]);
    }

    @Override
    public void addNewPassword(String passwordName, String cypher) throws Exception {
        File directory = new File(path);
        if (!directory.isDirectory() && !directory.mkdir()) {
            throw new Exception("Unable to create directory");
        }
        File storage = (this.GetFileFromName(passwordName));
        if (storage.createNewFile()) {
            this.WriteToFile(storage.getPath(),cypher);
        } else {
            throw new Exception("Password with the same name already existing. Please choose another name of update the existing one.");
        }
    }

    @Override
    public void deletePassword(String passwordName) throws Exception {
        File storage = this.GetFileFromName(passwordName);
        // send message to subscribers (or auditor only in this case) to notify when password is being reset
        publisher.send("Password is being reset!");
        if (!storage.delete()) {
            throw new Exception("Unable to delete password setting under " + storage.getName());
        }
    }

    @Override
    public char[] getPassword(String passwordName, PasswordSafeEngine passwordSafeEngine) throws IOException {
        File passwordFile = this.GetFileFromName(passwordName);
        char[] buffer = null;
        if (passwordFile.exists()) {
            FileReader reader = null;
            try {
                buffer = new char[(int) passwordFile.length()];
                reader = new FileReader(passwordFile);
                reader.read(buffer);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }
        return buffer;
    }

    @Override
    public void updatePassword(PasswordInfo info) throws Exception {
        File storage = this.GetFileFromName(info.getName());
        if (storage.exists()) {
            this.WriteToFile(storage.getPath(), info.getPlain());
            // every time we update a password correctly the subscribers (for now the auditor) are informed
            publisher.send("Password '" + info.getName() + "' has been updated!");
        } else {
            // every time we update a password incorrectly the subscribers (for now the auditor) are informed
            publisher.send("Wrong password entered!");
            throw new Exception("Password with the same name not existing.");
        }
    }

    private void WriteToFile(String filename, String crypted) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            writer.write(crypted);
        } finally {
            if (writer != null) try { writer.close(); } catch (IOException ignore) {}
        }
    }

    private File GetFileFromName(String name) {
        return new File( path + "/" + name + ".pw");
    }


    @Override
    public String getMasterPasswordFromFile() throws Exception {
        File passwordFile = new File(masterPath);
        char[] buffer = null;
        if (passwordFile.exists()) {
            FileReader reader = null;
            try {
                buffer = new char[(int)passwordFile.length()];
                reader = new FileReader(passwordFile);
                reader.read(buffer);
            }
            finally {
                if (reader != null) { try { reader.close(); } catch (IOException ignored) { } }
            }
        }
        return buffer == null ? null : new String(buffer);
    }

    @Override
    public void storeMasterPasswordToFile(String masterPassword) throws Exception {
        FileWriter writer = null;
        try {
            writer = new FileWriter(masterPath);
            writer.write(masterPassword);
            publisher.send("New master password has been set");
        } finally {
            if (writer != null) try { writer.close(); } catch (IOException ignore) {}
        }
    }
}
