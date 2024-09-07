package org.example.validation;

import org.example.alphabet.Alphabet;
import org.example.ecxeptions.CaesarsCipherException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class Validator {
    public boolean isValidKey(int key, Alphabet alphabet) {
        return key >= 0 && key <= alphabet.getSize();
    }

    public void validateForReading(String filePath) {
        Path path = validatePath(filePath);
        if (isFileExists(path.toString())) {
            if (!Files.isReadable(path)) {
                throw new CaesarsCipherException("File " + path + "is not readable!");
            }
        }
    }

    public boolean isFileExists(String filePath) {
        Path path = validatePath(filePath);
        try {
            if (!path.toString().endsWith(".txt")) {
                throw new CaesarsCipherException("Invalid file format.");
            }
            if (Files.exists(path) && Files.isRegularFile(path)) {
                return true;
            }
        } catch (SecurityException e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
        return false;
    }

    private Path validatePath(String filePath) {
        try {
            return Path.of(filePath);
        } catch (SecurityException | InvalidPathException e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }
}
