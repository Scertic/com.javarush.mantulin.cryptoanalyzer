package org.example;

import org.example.cipher.BruteForce;
import org.example.cipher.CaesarCoder;
import org.example.ecxeptions.CaesarsCipherException;

import java.util.Scanner;

public class ConsoleApp {

    public void start() {
        System.out.println("-".repeat(100));
        System.out.println("1 - Шифр Цезаря\n" +
                "2 - Brute force\n" +
                "3 - Статистический анализ");
        System.out.print("Выберите способ: ");
        try (Scanner scanner = new Scanner(System.in)) {
            switch (scanner.nextInt()) {
                case 1: {
                    scanner.nextLine();
                    System.out.println("-".repeat(100));
                    System.out.print("Введите команду (e - encrypt/d - decrypt): ");
                    String command = scanner.nextLine();
                    System.out.print("Введите полный путь до файла источника: ");
                    String fileSrcPath = scanner.nextLine();
                    System.out.print("Введите полный путь до файла результата: ");
                    String fileDstPath = scanner.nextLine();
                    System.out.print("Введите ключ: ");
                    int key = scanner.nextInt();
                    new CaesarCoder(command, fileSrcPath,fileDstPath, key).start();
                    break;
                }
                case 2: {
                    scanner.nextLine();
                    System.out.println("-".repeat(100));
                    System.out.print("Введите полный путь до файла источника: ");
                    String fileSrcPath = scanner.nextLine();
                    System.out.print("Введите полный путь до файла результата: ");
                    String fileDstPath = scanner.nextLine();
                    System.out.print("Введите полный путь до файла примера текста: ");
                    String representativeText = scanner.nextLine();
                    new BruteForce(fileSrcPath,fileDstPath, representativeText).start();
                    break;
                }
                case 3: {
                    System.out.println("-".repeat(100));
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
