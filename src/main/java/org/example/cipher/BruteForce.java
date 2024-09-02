package org.example.cipher;


import org.example.alphabet.Alphabet;
import org.example.ecxeptions.CaesarsCipherException;

public class BruteForce {

    private int shift;
    private Alphabet alphabet = new Alphabet();

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public String decryptByBruteForce(String encryptedText) {
        try {
            StringBuilder result = new StringBuilder();
            String textLC = encryptedText.toLowerCase();
            for (int i = 0; i < encryptedText.length(); i++) {
                if (alphabet.containsChar(textLC.charAt(i))) {
                    result.append(alphabet.getChar((alphabet.indexOf(textLC.charAt(i)) - shift + alphabet.getSize()) % alphabet.getSize()));
                }
            }
            return result.toString();
        } catch (Exception e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }
}
