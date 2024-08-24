package org.example;

import java.util.HashMap;

public class Cipher {
    private final char[] alphabet;
    private final HashMap<Character, Integer> alphabetSet;
    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;
        this.alphabetSet = convertAlphabet(alphabet);
    }
    public Cipher() {
        this(new char[]{'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
                'и','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
                'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '});
    }

    public String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (alphabetSet.containsKey(text.toLowerCase().charAt(i))) {
                result.append(alphabet[(alphabetSet.get(text.toLowerCase().charAt(i)) + shift) % alphabet.length]);
            }
        }
        return result.toString();
    }
    public String decrypt(String encryptedText, int shift) {
        StringBuilder result = new StringBuilder();
        int findSymbol;
        for (int i = 0; i < encryptedText.length(); i++) {
            findSymbol = alphabetSet.get(encryptedText.toLowerCase().charAt(i));
            if (findSymbol - shift < 0) {
                findSymbol = alphabet.length;
            }
            if (alphabetSet.containsKey(encryptedText.toLowerCase().charAt(i))) {
                result.append(alphabet[Math.abs(findSymbol - shift) % alphabet.length]);
            }
        }
        return result.toString();
    }

    private HashMap<Character, Integer> convertAlphabet(char[] alphabet) {
        HashMap<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < alphabet.length; i++) {
            result.put(alphabet[i],i );
        }
        return result;
    }
}
