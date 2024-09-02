package org.example.validation;

import org.example.alphabet.Alphabet;
import org.example.ecxeptions.CaesarsCipherException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class Validator {
    public boolean isValidKey(int key, Alphabet alphabet) {
        return key >= 0 && key <= alphabet.getSize();
    }

    public boolean isFileExists(String filePath) {
        Path path = validatePath(filePath);
        try {
            if (Files.exists(path) && Files.isRegularFile(path)) {
                return true;
            }
        } catch (SecurityException e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
        return false;
    }
    
    public boolean isFileTxt(String filePath) {
        Path path = validatePath(filePath);
        if (!path.toString().endsWith(".txt")) {
            throw new CaesarsCipherException("Invalid file format.");
        }
        return true;
    }

    public Path validatePath(String filePath) {
        try {
            return Path.of(filePath);
        } catch (SecurityException | InvalidPathException e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }
}
