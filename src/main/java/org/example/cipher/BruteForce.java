package org.example.cipher;


import org.example.alphabet.Alphabet;
import org.example.ecxeptions.CaesarsCipherException;
import org.example.files.FileManager;
import org.example.validation.Validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class BruteForce {

    private int key;
    private final String fileSrcPath;
    private final String fileDstPath;
    private final String representativeText;

    private final Validator validator;
    private final FileManager fileManager;

    private final Alphabet alphabet;
    private HashSet<String> setRep;

    public BruteForce(String fileSrcPath, String fileDstPath, String representativeText) {
        this.fileSrcPath = fileSrcPath;
        this.fileDstPath = fileDstPath;
        this.representativeText = representativeText;
        this.validator = new Validator();
        this.fileManager = new FileManager();
        this.alphabet = new Alphabet();
        setSetRep();
    }

    private void setSetRep() {
        setRep = new HashSet<>();
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
    }

    private void setKey() {
        OUT:
        for (int i = 0; i < alphabet.getSize(); i++) {
            FileManager fmSrc = new FileManager();
            HashSet<String> setDec = new HashSet<>();
            this.key = i;
            if (validator.isFileExists(fileSrcPath)
                    && validator.isFileTxt(fileSrcPath)) {
                String line = fmSrc.readFile(fileSrcPath);
                for (int j = 0; j < 10; j++) {
                    if (line == null) {
                        break;
                    }
                    setDec.addAll(Arrays.stream(decryptByBruteForce(line).split(" ")).toList());
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
    }

    private String decryptByBruteForce(String encryptedText) {
        try {
            StringBuilder result = new StringBuilder();
            String textLC = encryptedText.toLowerCase();
            for (int i = 0; i < encryptedText.length(); i++) {
                if (alphabet.containsChar(textLC.charAt(i))) {
                    result.append(alphabet.getChar((alphabet.indexOf(textLC.charAt(i)) - key + alphabet.getSize()) % alphabet.getSize()));
                }
            }
            return result.toString();
        } catch (Exception e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }

    public void start() {
        if (!validator.isFileExists(fileDstPath)
                && validator.isFileTxt(fileDstPath)) {
            setKey();
            fileManager.createFile(fileDstPath);
            String line = fileManager.readFile(fileSrcPath);
            while (line != null) {
                fileManager.writeFile(decryptByBruteForce(line) + '\n', fileDstPath);
                line = fileManager.readFile(fileSrcPath);
            }
            fileManager.close();
        }
    }
}
