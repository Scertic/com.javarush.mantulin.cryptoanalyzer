package org.example;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        Cipher cipher = new Cipher();
        System.out.println(cipher.encrypt("проверить что он есть в вашем алфавите. Если его нет, пропускаем этот символ. ",1));
        System.out.println(cipher.decrypt("рспгжскуэашупапоажтуэагагбщжнабмхбгкуж,ажтмкаждпаожу«арспрфтлбжнаяупуаткнгпм,а",1));

    }
}
