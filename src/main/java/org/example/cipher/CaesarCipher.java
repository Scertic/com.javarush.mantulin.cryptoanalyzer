package org.example.cipher;

import org.example.alphabet.Alphabet;
import org.example.ecxeptions.CaesarsCipherException;


public class CaesarCipher {

    private final Alphabet alphabet;

    public CaesarCipher(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public CaesarCipher() {
        this(new Alphabet());
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
                if (alphabet.containsChar(textLC.charAt(i))) {
                    result.append(alphabet.getChar((alphabet.indexOf(textLC.charAt(i)) + shift + alphabet.getSize()) % alphabet.getSize()));
                }
            }
            return result.toString();
        } catch (Exception e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

}
