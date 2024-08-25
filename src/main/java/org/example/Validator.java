package org.example;

import org.example.ecxeptions.CaesarsCipherException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class Validator {
    public boolean isValidKey(int key, char[] alphabet) {
        if (key >= 0 && key < alphabet.length) {
            return true;
        }
        throw new CaesarsCipherException("Invalid key");
    }

    public boolean isFileExists(String filePath) {
        try {
            if (Files.exists(Path.of(filePath))) {
                return true;
            }
        } catch (SecurityException | InvalidPathException e) {
            throw new CaesarsCipherException(e.getMessage());
        }
        return false;
    }
}
