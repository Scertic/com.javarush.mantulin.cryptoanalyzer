package com.javarush.mantulin.cryptoanalyzer.cipher;

import com.javarush.mantulin.cryptoanalyzer.alphabet.Alphabet;
import com.javarush.mantulin.cryptoanalyzer.ecxeptions.CaesarsCipherException;

/**
 * Класс для шифровки/расшифровки строки методом Цезаря.
 */
public class CaesarCipher {

    private final Alphabet alphabet;

    public CaesarCipher(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public CaesarCipher() {
        this(new Alphabet());
    }

    /**
     * Шифрование строки методом Цезаря со сдвигом shift.
     *
     * @param text  - Текст для шифрования.
     * @param shift - Ключ/сдвиг шифрования.
     * @return - возвращает зашифрованную строку на основе входящей text и свдига shift.
     */
    public String encrypt(String text, int shift) {
        return cryptProcess(text, shift);
    }

    /**
     * Дешифрование строки методом Цезаря со сдвигом shift.
     *
     * @param encryptedText - строка для расшифровки.
     * @param shift         - Ключ/сдвиг дешифрования.
     * @return - возвращает расшифрованную строку.
     */
    public String decrypt(String encryptedText, int shift) {
        return cryptProcess(encryptedText, -shift);
    }

    /**
     * Процесс шифрования строки.
     *
     * @param text  - строка для шифрования.
     * @param shift - Ключ/сдвиг.
     * @return Возвращает зашифрованную строку.
     */
    private String cryptProcess(String text, int shift) {
        try {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                char charFromText = toLowerCase(text.charAt(i));
                result.append(alphabet.charOf((alphabet.indexOf(charFromText) + shift + alphabet.getSize()) % alphabet.getSize()));
            }
            return result.toString();
        } catch (Exception e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }

    /**
     * Приведение символа к нижнему регистру.
     *
     * @param ch символ
     * @return возвращает входящий символ в нижнем регистре.
     */
    private char toLowerCase(char ch) {
        return (ch + "").toLowerCase().charAt(0);
    }

    /**
     * Возвращает объект алфавита.
     *
     * @return возвращает алфавит.
     */
    public Alphabet getAlphabet() {
        return alphabet;
    }

}
