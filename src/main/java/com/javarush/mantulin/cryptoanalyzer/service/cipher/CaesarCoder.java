package com.javarush.mantulin.cryptoanalyzer.service.cipher;

import com.javarush.mantulin.cryptoanalyzer.ecxeption.CaesarsCipherException;
import com.javarush.mantulin.cryptoanalyzer.service.files.FileManager;
import com.javarush.mantulin.cryptoanalyzer.service.validation.Validator;

/**
 * Класс для шифрования и дешифрования файла.
 */
public class CaesarCoder {
    private final Validator validator;
    private final FileManager fileManager;
    private final CaesarCipher cipher;

    public CaesarCoder() {
        this.validator = new Validator();
        this.fileManager = new FileManager();
        this.cipher = new CaesarCipher();
    }

    /**
     * Метод читает и шифрует файл и записывает результат в другой файл.
     * Файл для записи не должен быть создан заранее.
     *
     * @param fileSrcPath - путь до файла, который необходимо зашифровать.
     * @param fileDstPath - путь до файла результата шифрования.
     * @param key         - Ключ шифрования.
     * @throws CaesarsCipherException
     */
    public void encrypt(String fileSrcPath, String fileDstPath, int key) {
        checkBeforeCrypt(fileSrcPath, fileDstPath, key);
        fileManager.createFile(fileDstPath);
        validator.validateForReading(fileSrcPath);
        String line = fileManager.readLineFromFile(fileSrcPath);
        while (line != null) {
            fileManager.appendToFile(cipher.encrypt(line, key) + '\n', fileDstPath);
            line = fileManager.readLineFromFile(fileSrcPath);
        }
        fileManager.close();
        System.out.println("Done!");
    }

    /**
     * Метод читает и дешифрует файл и записывает результат в другой файл.
     * Файл для записи не должен быть создан заранее.
     *
     * @param fileSrcPath - путь до файла, который необходимо зашифровать.
     * @param fileDstPath - путь до файла результата шифрования.
     * @param key         - Ключ шифрования.
     * @throws CaesarsCipherException
     */
    public void decrypt(String fileSrcPath, String fileDstPath, int key) {
        checkBeforeCrypt(fileSrcPath, fileDstPath, key);
        fileManager.createFile(fileDstPath);
        validator.validateForReading(fileSrcPath);
        String line = fileManager.readLineFromFile(fileSrcPath);
        while (line != null) {
            //System.out.println(cipher.encrypt(line, key));
            fileManager.appendToFile(cipher.decrypt(line, key) + '\n', fileDstPath);
            line = fileManager.readLineFromFile(fileSrcPath);
        }
        fileManager.close();
        System.out.println("Done!");
    }

    /**
     * Метод проверки валидности ключа и файлов.
     * @param fileSrcPath - путь до файла, который необходимо зашифровать.
     * @param fileDstPath - путь до файла результата шифрования.
     * @param key         - Ключ шифрования.
     * @throws CaesarsCipherException
     */
    private void checkBeforeCrypt(String fileSrcPath, String fileDstPath, int key) {
        if (!validator.isValidKey(key, cipher.getAlphabet())) {
            throw new CaesarsCipherException("The key is invalid. The key must be between 0 and " + (cipher.getAlphabet().getSize()-1));
        }
        if (!validator.isFileExists(fileSrcPath)) {
            throw new CaesarsCipherException("The file \"" + fileSrcPath + "\" does not exist.");
        }
        if (validator.isFileExists(fileDstPath)) {
            throw new CaesarsCipherException("The file \"" + fileDstPath + "\" already exists.");
        }
    }

}
