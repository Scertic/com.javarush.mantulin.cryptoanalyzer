package org.example.cipher;

import org.example.alphabet.Alphabet;
import org.example.files.FileManager;
import org.example.validation.Validator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс для дешифрования файла методом статистического анализа.
 */
public class StatisticalAnalyzer {

    /**
     * Поиск возможного ключа для дешифрования.
     *
     * @param file     - путь до файла дешифрования.
     * @param repFile  - путь до файла с репрезентативным текстом.
     * @param alphabet - алфавит
     * @return возвращает вероятный ключ.
     */
    public int findMostLikelyShift(String file, String repFile, Alphabet alphabet) {
        Validator validator = new Validator();
        validator.validateForReading(file);
        Map<Character, Integer> charMap = getCharacterIntegerMap(file);
        Map<Character, Integer> charMapRep = getCharacterIntegerMap(repFile);

        int maxCharMap = Collections.max(charMap.values());
        int maxCharMapRep = Collections.max(charMapRep.values());

        Character maxCharCharacter = getMaxCharacter(charMap, maxCharMap);
        Character maxCharRepCharacter = getMaxCharacter(charMapRep, maxCharMapRep);

        return Math.abs(alphabet.indexOf(maxCharCharacter) - alphabet.indexOf(maxCharRepCharacter));
    }

    /**
     * Метод возвращает символ из Map со значением maxCharMap.
     *
     * @param charMap
     * @param maxCharMap
     * @return
     */
    private Character getMaxCharacter(Map<Character, Integer> charMap, int maxCharMap) {
        Character maxCharCharacter = null;
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            if (entry.getValue() == maxCharMap) {
                maxCharCharacter = entry.getKey();
                break;
            }
        }
        return maxCharCharacter;
    }

    /**
     * Метод создает карту символов со счетчиком повторений из файла.
     *
     * @param file - путь до файла.
     * @return
     */
    private Map<Character, Integer> getCharacterIntegerMap(String file) {
        FileManager fileManager = new FileManager();
        Map<Character, Integer> charMap = new HashMap<>();
        String line = fileManager.readLineFromFile(file);
        for (int i = 0; i < 100; i++) {
            if (line == null) {
                break;
            }
            line = fileManager.readLineFromFile(file);
            for (int j = 0; j < line.length(); j++) {
                Character ch = line.charAt(j);
                if (charMap.containsKey((ch + "").toLowerCase().charAt(0))) {
                    charMap.put((ch + "").toLowerCase().charAt(0), charMap.get((ch + "").toLowerCase().charAt(0)) + 1);
                } else {
                    charMap.put((ch + "").toLowerCase().charAt(0), 1);
                }
            }
        }
        fileManager.close();
        return charMap;
    }
}
