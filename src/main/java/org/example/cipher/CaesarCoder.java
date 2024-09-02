package org.example.cipher;

import org.example.cipher.CaesarCipher;
import org.example.files.FileManager;
import org.example.validation.Validator;

public class CaesarCoder {
    private final Validator validator = new Validator();
    private final FileManager fileManager = new FileManager();
    private final CaesarCipher cipher;
    private final String operation;
    private final String fileSrcPath;
    private final String fileDstPath;

    int key;

    public CaesarCoder(String operation, String fileSrcPath, String fileDstPath, int key) {
        this.operation = operation;
        this.fileSrcPath = fileSrcPath;
        this.fileDstPath = fileDstPath;
        this.key = key;
        this.cipher = new CaesarCipher();
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
            fileManager.close();
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
            fileManager.close();
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
