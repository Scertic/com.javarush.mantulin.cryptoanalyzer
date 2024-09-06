package org.example.alphabet;

import org.example.ecxeptions.CaesarsCipherException;

import java.util.HashMap;

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
                'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', '–', '('
                , ')', ';', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c'
                , 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u'
                , 'v', 'w', 'x', 'y', 'z', '-', '…', 'â', '’', '^'});
    }

    private HashMap<Character, Integer> convertAlphabet(char[] alphabet) {
        HashMap<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < alphabet.length; i++) {
            result.put(alphabet[i], i);
        }
        return result;
    }

    public int indexOf(char c){
        if (!alphabetMap.containsKey(c)) {
            throw new CaesarsCipherException("Wrong symbol: " + c);
        }
        return alphabetMap.get(c);
    }

    public char charOf(int index){
        if (index < 0 || index > alphabet.length) {
            throw new CaesarsCipherException("Index is invalid.");
        }
        return alphabet[index];
    }

    public int getSize() {
        return this.alphabet.length;
    }
}
