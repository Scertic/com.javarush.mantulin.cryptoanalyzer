package org.example.cipher;

import org.example.files.FileManager;
import org.example.validation.Validator;

public class CaesarCoder {
    private final Validator validator;
    private final FileManager fileManager;
    private final CaesarCipher cipher;

    public CaesarCoder() {
        this.validator = new Validator();
        this.fileManager = new FileManager();
        this.cipher = new CaesarCipher();
    }


    public void encrypt(String fileSrcPath, String fileDstPath, int key) {
        //D:\Java\IDEA\v17\UniverrsityJavaRush\2\wap.txt
        if (validator.isValidKey(key, cipher.getAlphabet())
                && validator.isFileExists(fileSrcPath)
                && !validator.isFileExists(fileDstPath)) {
            fileManager.createFile(fileDstPath);
            validator.validateForReading(fileSrcPath);
            String line = fileManager.readLineFromFile(fileSrcPath);
            while (line != null) {
                //System.out.println(cipher.encrypt(line, key));
                fileManager.appendToFile(cipher.encrypt(line, key) + '\n', fileDstPath);
                line = fileManager.readLineFromFile(fileSrcPath);
            }
            fileManager.close();
            System.out.println("Done!");
        }
    }

    public void decrypt(String fileSrcPath, String fileDstPath, int key) {
        //D:\Java\IDEA\v17\UniverrsityJavaRush\2\wap.txt
        if (validator.isValidKey(key, cipher.getAlphabet())
                && validator.isFileExists(fileSrcPath)
                && !validator.isFileExists(fileDstPath)) {
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
    }

}
