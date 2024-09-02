package org.example.crypter;

import org.example.cipher.BruteForce;
import org.example.cipher.CaesarCipher;
import org.example.files.FileManager;
import org.example.validation.Validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class Cryptographer {
    private final Validator validator = new Validator();
    private final FileManager fileManager = new FileManager();
    private CaesarCipher cipher;
    private BruteForce bruteForce;

    private final String operation;
    private final String fileSrcPath;
    private final String fileDstPath;
    private String representativeText;

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
        this.representativeText = representativeText;
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

    public void decryptByBruteForce() {
        //D:\Java\IDEA\v17\UniverrsityJavaRush\2\wap.txt
        //D:\Java\IDEA\v17\UniverrsityJavaRush\2\bf_result_1.txt
        //D:\Java\IDEA\v17\UniverrsityJavaRush\2\rt.txt
        HashSet<String> setRep = new HashSet<>();
        HashSet<String> setDec;
        FileManager fmRep = new FileManager();
        //создание репрезентативной выборки
        if (validator.isFileExists(representativeText)
                && validator.isFileTxt(representativeText)) {
            String line = fmRep.readFile(representativeText);
            for (int i = 0; i < 100; i++) {
                if (line == null) {
                    break;
                }
                setRep.addAll(Arrays.stream(line.toLowerCase().split(" ")).toList());
                Iterator<String> it = setRep.iterator();
                while (it.hasNext()) {
                    if ((it.next()).length() < 4) {
                        it.remove();
                    }
                }
                line = fmRep.readFile(representativeText);
            }
        }
        fmRep.close();
        //поиск ключа
        key = 0;
        OUT:
        for (int i = 1; i < bruteForce.getAlphabet().getSize(); i++) {
            FileManager fmSrc = new FileManager();
            setDec = new HashSet<>();
            key = i;
            bruteForce.setShift(key);
            if (validator.isFileExists(fileSrcPath)
                    && validator.isFileTxt(fileSrcPath)) {
                String line = fmSrc.readFile(fileSrcPath);
                for (int j = 0; j < 10; j++) {
                    if (line == null) {
                        break;
                    }
                    setDec.addAll(Arrays.stream(bruteForce.decryptByBruteForce(line).split(" ")).toList());
                    Iterator<String> it = setDec.iterator();
                    while (it.hasNext()) {
                        if ((it.next()).length() < 4) {
                            it.remove();
                        }
                    }
                    line = fmSrc.readFile(fileSrcPath);
                }
            }
            fmSrc.close();
            //количество совпадений > 1
            for (String s : setDec) {
                if (setRep.contains(s)) {
                    break OUT;
                }
            }
        }
        if (!validator.isFileExists(fileDstPath)
                && validator.isFileTxt(fileDstPath)) {
            fileManager.createFile(fileDstPath);
            String line = fileManager.readFile(fileSrcPath);
            while (line != null) {
                fileManager.writeFile(bruteForce.decryptByBruteForce(line) + '\n', fileDstPath);
                line = fileManager.readFile(fileSrcPath);
            }
            fileManager.close();
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
            case ("BF"): {
                decryptByBruteForce();
                break;
            }
            default:
                System.out.println("Неверный тип команды");
        }
    }
}
