package org.example;


import org.example.consoleui.ConsoleApp;
import org.example.ecxeptions.CaesarsCipherException;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("1 - Консольное меню\n" +
                "2 - Запуск с праметрами\n" +
                "3 - Оконное приложение");
        System.out.print("Выберите способ запуска: ");
        try (Scanner scanner = new Scanner(System.in)) {
            switch(scanner.nextInt()) {
                case 1 : {
                    new ConsoleApp().start();
                    break;
                }
                case 2 : {
                    System.out.println("В разработке.");
                    //new PicMenu().run();
                    break;
                }
                case 3 : {
                    System.out.println("В разработке.");
                    break;
                }
                default:
                    System.out.println("Данный пункт не существует. Программа завершается.");
            }
        } catch (Exception e) {
            System.out.println("Некорректный ввод, приложение завершилось. Повторите попытку.");
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }
}
