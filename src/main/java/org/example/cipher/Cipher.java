package org.example.cipher;

import org.example.ecxeptions.CaesarsCipherException;

import java.util.HashMap;

public class Cipher {
    private final char[] alphabet;
    private final HashMap<Character, Integer> alphabetMap;

    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;
        this.alphabetMap = convertAlphabet(alphabet);
    }

    public Cipher() {
        this(new char[]{'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
                'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
                'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', '-'});
    }

    public String encrypt(String text, int shift) {
        return cryptProcess(text, shift);
    }

    public String decrypt(String encryptedText, int shift) {
       return cryptProcess(encryptedText, -shift);
    }

    private String cryptProcess(String text, int shift) {
        try {
            StringBuilder result = new StringBuilder();
            String textLC = text.toLowerCase();
            for (int i = 0; i < text.length(); i++) {
                if (alphabetMap.get(textLC.charAt(i)) == -1) {
                    throw new CaesarsCipherException("Wrong text.");
                }
                if (alphabetMap.containsKey(textLC.charAt(i))) {
                    result.append(alphabet[(alphabetMap.get(textLC.charAt(i)) + shift + alphabet.length) % alphabet.length]);
                }
            }
            return result.toString();
        } catch (Exception e) {
            throw new CaesarsCipherException(e.getMessage());
        }
    }

    private HashMap<Character, Integer> convertAlphabet(char[] alphabet) {
        HashMap<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < alphabet.length; i++) {
            result.put(alphabet[i], i);
        }
        return result;
    }
}
