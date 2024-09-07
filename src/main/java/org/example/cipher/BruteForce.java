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
    private final Validator validator;
    private final FileManager fileManager;
    private final Alphabet alphabet;
    private HashSet<String> setRep;

    public BruteForce() {
        this.validator = new Validator();
        this.fileManager = new FileManager();
        this.alphabet = new Alphabet();
    }

    private void setSetRep(String representativeText) {
        setRep = new HashSet<>();
        FileManager fmRep = new FileManager();
        //создание репрезентативной выборки
        if (validator.isFileExists(representativeText)) {
            String line = fmRep.readLineFromFile(representativeText);
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
                line = fmRep.readLineFromFile(representativeText);
            }
        }
        fmRep.close();
    }

    private void findTheKey(String fileSrcPath) {
        OUT:
        for (int i = 0; i < alphabet.getSize(); i++) {
            FileManager fmSrc = new FileManager();
            HashSet<String> setDec = new HashSet<>();
            this.key = i;
            if (validator.isFileExists(fileSrcPath)) {
                String line = fmSrc.readLineFromFile(fileSrcPath);
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
                    line = fmSrc.readLineFromFile(fileSrcPath);
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
            for (int i = 0; i < encryptedText.length(); i++) {
                char charFromText = toLowerCase(encryptedText.charAt(i));
                result.append(alphabet.charOf((alphabet.indexOf(charFromText) - key + alphabet.getSize()) % alphabet.getSize()));
            }
            return result.toString();
        } catch (Exception e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }

    public void decrypt(String fileSrcPath, String fileDstPath, String representativeText) {
        validator.validateForReading(representativeText);
        validator.validateForReading(fileSrcPath);
        if (!validator.isFileExists(fileDstPath)) {
            setSetRep(representativeText);
            findTheKey(fileSrcPath);
            fileManager.createFile(fileDstPath);
            String line = fileManager.readLineFromFile(fileSrcPath);
            while (line != null) {
                fileManager.appendToFile(decryptByBruteForce(line) + '\n', fileDstPath);
                line = fileManager.readLineFromFile(fileSrcPath);
            }
            fileManager.close();
        }
    }

    private char toLowerCase(char ch) {
        return (ch + "").toLowerCase().charAt(0);
    }
}
