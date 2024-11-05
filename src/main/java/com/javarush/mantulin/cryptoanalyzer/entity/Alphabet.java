package com.javarush.mantulin.cryptoanalyzer.entity;

import com.javarush.mantulin.cryptoanalyzer.entity.ecxeptions.CaesarsCipherException;

import java.util.HashMap;

/**
 * Класс алфавита.
 */
public class Alphabet {
    private final char[] alphabet;
    private final HashMap<Character, Integer> alphabetMap;

    public Alphabet(char[] alphabet) {
        this.alphabet = alphabet;
        this.alphabetMap = convertAlphabet(alphabet);
    }

    public Alphabet() {
        this(new char[]{'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
                'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
                'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', '-'});
    }

    /**
     * Метод создает HashMap на основе массива символов, где ключ - символ, значение - индекс из массива.
     *
     * @param alphabet
     * @return
     */
    private HashMap<Character, Integer> convertAlphabet(char[] alphabet) {
        HashMap<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < alphabet.length; i++) {
            result.put(alphabet[i], i);
        }
        return result;
    }

    /**
     * Возвращает индекс символа из алфавита.
     *
     * @param c - символ
     * @return
     */
    public int indexOf(char c) {
        if (!alphabetMap.containsKey(c)) {
            throw new CaesarsCipherException("Wrong symbol: " + c);
        }
        return alphabetMap.get(c);
    }

    /**
     * Возвращает символ по индексу из алфавита.
     *
     * @param index
     * @return
     */
    public char charOf(int index) {
        if (index < 0 || index > alphabet.length) {
            throw new CaesarsCipherException("Index is invalid.");
        }
        return alphabet[index];
    }

    /**
     * Возвращает размер алфавита.
     *
     * @return
     */
    public int getSize() {
        return this.alphabet.length;
    }

}
