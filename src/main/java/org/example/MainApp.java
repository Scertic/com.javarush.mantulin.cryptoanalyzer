package org.example;


import org.example.consoleui.ConsoleApp;
import org.example.ecxeptions.CaesarsCipherException;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("""
                1 - Консольное меню
                2 - Запуск с праметрами
                3 - Оконное приложение
                0 - Выход из программы (0 или любой другой символ).""");
        System.out.print("Выберите способ запуска: ");
        try (Scanner scanner = new Scanner(System.in)) {
            switch(scanner.nextLine()) {
                case "1" : {
                    new ConsoleApp().start();
                    break;
                }
                case "2" : {
                    System.out.println("В разработке.");
                    //new PicMenu().run();
                    break;
                }
                case "3" : {
                    MainFX.main(args);
                    break;
                }
                default:
                    System.out.println("Программа завершается.");
            }
        } catch (Exception e) {
            System.out.println("Некорректный ввод, приложение завершилось. Повторите попытку.");
            throw new CaesarsCipherException(e);
        }
    }
}
