package com.javarush.mantulin.cryptoanalyzer.service.validation;

import com.javarush.mantulin.cryptoanalyzer.service.Alphabet;
import com.javarush.mantulin.cryptoanalyzer.ecxeption.CaesarsCipherException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class Validator {
    /**
     * Проверяет ключ на соответвию алфавита.
     * @param key
     * @param alphabet
     * @return
     */
    public boolean isValidKey(int key, Alphabet alphabet) {
        return key >= 0 && key < alphabet.getSize();
    }

    /**
     * Проверка файла на возможность чтения из него.
     * @param filePath - путь к файлу для чтения
     * @throws CaesarsCipherException
     */
    public void validateForReading(String filePath) {
        Path path = validatePath(filePath);
        if (isFileExists(path.toString())) {
            if (!Files.isReadable(path)) {
                throw new CaesarsCipherException("File " + path + "is not readable!");
            }
        }
    }

    /**
     * Возвращает истину, если файл существует и имеет корректный формат txt.
     * @param filePath - путь до файла.
     * @throws CaesarsCipherException
     */
    public boolean isFileExists(String filePath) {
        Path path = validatePath(filePath);
        try {
            if (!path.toString().endsWith(".txt")) {
                throw new CaesarsCipherException("Invalid file "+ filePath + " format.");
            }
            if (Files.exists(path) && Files.isRegularFile(path)) {
                return true;
            }
        } catch (SecurityException e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
        return false;
    }

    /**
     * Проверка пути к файлу на валидность.
     * @param filePath - путь к файлу
     * @return Path к файлу
     * @throws CaesarsCipherException
     */
    private Path validatePath(String filePath) {
        try {
            return Path.of(filePath);
        } catch (SecurityException | InvalidPathException e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }
}
