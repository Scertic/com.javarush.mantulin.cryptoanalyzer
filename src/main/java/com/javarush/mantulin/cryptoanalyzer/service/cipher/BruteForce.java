package com.javarush.mantulin.cryptoanalyzer.service.cipher;


import com.javarush.mantulin.cryptoanalyzer.entity.Alphabet;
import com.javarush.mantulin.cryptoanalyzer.entity.ecxeptions.CaesarsCipherException;
import com.javarush.mantulin.cryptoanalyzer.service.files.FileManager;
import com.javarush.mantulin.cryptoanalyzer.service.validation.Validator;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Класс для расшифровки файла методом брутфорс. Подбор ключа шифрования происходит на основе репрезентативного файла.
 */
public class BruteForce {

    private int key;
    private final Validator validator;
    private FileManager fileManager;
    private final Alphabet alphabet;
    private HashSet<String> setRep;

    public BruteForce() {
        this.validator = new Validator();
        this.fileManager = new FileManager();
        this.alphabet = new Alphabet();
    }

    /**
     * Метод читает считывает 100 строк репрезентативного файла, и составляет множество слов из считанных строк длина
     * которых больше 3 символов.
     *
     * @param representativeText - Путь до файла с репрезентативным текстом.
     */
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
                setRep.removeIf(s -> (s).length() < 4);
                line = fmRep.readLineFromFile(representativeText);
            }
        }
        fmRep.close();
    }

    /**
     * Поиск возможного ключа путем его перебора и поиска совпадений слов из расшифроанного текста в репрезентативном множестве.
     * Если найдено 1 совпадение, считаем, что ключ так же найден.
     *
     * @param fileSrcPath - Путь до файла расшифровки.
     */
    private void findTheKey(String fileSrcPath) {
        OUT:
        for (int i = 0; i < alphabet.getSize(); i++) {
            FileManager fmSrc = new FileManager();
            HashSet<String> setDec = new HashSet<>();
            this.key = i;
            if (validator.isFileExists(fileSrcPath)) {
                String line = fmSrc.readLineFromFile(fileSrcPath);
                for (int j = 0; j < 100; j++) {
                    if (line == null) {
                        break;
                    }
                    setDec.addAll(Arrays.stream(decryptByBruteForce(line).split(" ")).toList());
                    setDec.removeIf(s -> (s).length() < 4);
                    line = fmSrc.readLineFromFile(fileSrcPath);
                }
            }
            fmSrc.close();
            //количество совпадений = 1
            for (String s : setDec) {
                if (setRep.contains(s)) {
                    break OUT;
                }
            }
        }
    }

    /**
     * Метод расшифровки строки текста.
     *
     * @param encryptedText - строка текста для расшифровки.
     * @return
     */
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

    /**
     * Метод расшифровки файла путем метода брутфорс.
     *
     * @param fileSrcPath        - путь до файла дешифрования.
     * @param fileDstPath        - путь до файла результата.
     * @param representativeText - путь до файла с репрезентативным текстом.
     */
    public void decrypt(String fileSrcPath, String fileDstPath, String representativeText) {
        validator.validateForReading(fileSrcPath);
        if (!validator.isFileExists(fileDstPath)) {
            if (!representativeText.isBlank()) {
                validator.validateForReading(representativeText);
                setSetRep(representativeText);
                findTheKey(fileSrcPath);
                fileManager.createFile(fileDstPath);
                String line = fileManager.readLineFromFile(fileSrcPath);
                while (line != null) {
                    fileManager.appendToFile(decryptByBruteForce(line) + '\n', fileDstPath);
                    line = fileManager.readLineFromFile(fileSrcPath);
                }
                fileManager.close();
            } else {
                for (int i = 1; i < alphabet.getSize(); i++) {
                    key = i;
                    fileManager = new FileManager();
                    String newFileDstPath = fileDstPath.replace(".txt", "_key_"+key+".txt");
                    fileManager.createFile(newFileDstPath);
                    String line = fileManager.readLineFromFile(fileSrcPath);
                    while (line != null) {
                        fileManager.appendToFile(decryptByBruteForce(line) + '\n', newFileDstPath);
                        line = fileManager.readLineFromFile(fileSrcPath);
                    }
                    fileManager.close();
                }
            }
        }
    }

    /**
     * Возвращает объект алфавита.
     *
     * @return возвращает алфавит.
     */
    private char toLowerCase(char ch) {
        return (ch + "").toLowerCase().charAt(0);
    }
}
