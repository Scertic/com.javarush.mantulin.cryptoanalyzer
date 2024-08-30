package org.example.cipher;

import org.example.ecxeptions.CaesarsCipherException;

public class BruteForce {
    public String decryptByBruteForce(String encryptedText, char[] alphabet) {
//        for (int i = 0; i < alphabet.length; i++) {
//            int key = i;
//            try {
//                StringBuilder result = new StringBuilder();
//                int findSymbol;
//                String text = encryptedText.toLowerCase();
//                for (int i = 0; i < encryptedText.length(); i++) {
//                    if (alphabetSet.get(text.charAt(i)) == -1) {
//                        throw new CaesarsCipherException("Wrong text.");
//                    }
//                    findSymbol = alphabetSet.get(text.charAt(i)) - key;
//                    if (findSymbol < 0) {
//                        findSymbol = alphabet.length - 1;
//                    }
//                    if (alphabetSet.containsKey(text.charAt(i))) {
//                        result.append(alphabet[Math.abs(findSymbol) % alphabet.length]);
//                    }
//                }
//                return result.toString();
//            } catch (Exception e) {
//                throw new CaesarsCipherException(e.getMessage());
//            }
//        }
        return null;
    }
}
