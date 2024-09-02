package org.example.crypter;

import org.example.cipher.BruteForce;
import org.example.cipher.CaesarCipher;
import org.example.files.FileManager;
import org.example.validation.Validator;

public class Cryptographer {
    private Validator validator = new Validator();
    private FileManager fileManager = new FileManager();
    private CaesarCipher cipher;
    private BruteForce bruteForce;

    private String operation;
    private String fileSrcPath;
    private String fileDstPath;

    int key;

    public Cryptographer(String operation, String fileSrcPath, String fileDstPath, int key) {
        this.operation = operation;
        this.fileSrcPath = fileSrcPath;
        this.fileDstPath = fileDstPath;
        this.key = key;
        this.cipher = new CaesarCipher();
    }

    public Cryptographer(String operation, String fileSrcPath, String fileDstPath, String representativeText) {
        this.operation = operation;
        this.fileSrcPath = fileSrcPath;
        this.fileDstPath = fileDstPath;
        this.bruteForce = new BruteForce();
        this.bruteForce.representativeText = representativeText;
    }

    private void encrypt() {
        //D:\Java\IDEA\v17\UniverrsityJavaRush\2\wap.txt
        if (validator.isValidKey(key, cipher.getAlphabet())
                && validator.isFileExists(fileSrcPath)
                && validator.isFileTxt(fileSrcPath)
                && validator.isFileTxt(fileDstPath)
                && !validator.isFileExists(fileDstPath)) {
            fileManager.createFile(fileDstPath);
            String line = fileManager.readFile(fileSrcPath);
            while (line != null) {
                //System.out.println(cipher.encrypt(line, key));
                fileManager.writeFile(cipher.encrypt(line, key) + '\n', fileDstPath);
                line = fileManager.readFile(fileSrcPath);
            }
            System.out.println("Done!");
        } else {
            System.out.println("Something gone wrong.");
        }
    }

    private void decrypt() {
        //D:\Java\IDEA\v17\UniverrsityJavaRush\2\wap.txt
        if (validator.isValidKey(key, cipher.getAlphabet())
                && validator.isFileExists(fileSrcPath)
                && validator.isFileTxt(fileSrcPath)
                && validator.isFileTxt(fileDstPath)
                && !validator.isFileExists(fileDstPath)) {
            fileManager.createFile(fileDstPath);
            String line = fileManager.readFile(fileSrcPath);
            while (line != null) {
                //System.out.println(cipher.encrypt(line, key));
                fileManager.writeFile(cipher.decrypt(line, key) + '\n', fileDstPath);
                line = fileManager.readFile(fileSrcPath);
            }
            System.out.println("Done!");
        } else {
            System.out.println("Something gone wrong.");
        }
    }

    public void start() {
        switch (operation) {
            case ("e"), ("encrypt"): {
                encrypt();
                break;
            }
            case ("d"), ("decrypt"): {
                decrypt();
                break;
            }
            default:
                System.out.println("Неверный тип команды");
        }
    }
}
