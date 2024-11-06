package com.javarush.mantulin.cryptoanalyzer.controller.consoleui;

import com.javarush.mantulin.cryptoanalyzer.service.Alphabet;
import com.javarush.mantulin.cryptoanalyzer.service.cipher.BruteForce;
import com.javarush.mantulin.cryptoanalyzer.service.cipher.CaesarCoder;
import com.javarush.mantulin.cryptoanalyzer.service.cipher.StatisticalAnalyzer;
import com.javarush.mantulin.cryptoanalyzer.ecxeption.CaesarsCipherException;

import java.util.Scanner;

/**
 * Класс для консольного меню.
 */
public class ConsoleApp {

    /**
     * Составляет и выводит на экран меню из операций.
     */
    private void showMenu() {
        System.out.println("-".repeat(100));
        for (Operation o : Operation.values()) {
            System.out.printf("%d - %s", o.ordinal(), o.getDescription());
            System.out.println();
        }
        System.out.print("Выберите способ: ");
    }

    /**
     * Запуск выполнения и отображения консольного меню.
     */
    public void start() {
        showMenu();
        Operation operation = getOperation();
        processOperation(operation);
    }

    /**
     * Вызов методов на основе передаваемой операции.
     *
     * @param operation - операция на основе которой необъодимо выполнить действие по шифровнанию/дешифрованию.
     */
    private void processOperation(Operation operation) {
        switch (operation) {
            case EXIT -> processExit();
            case ENCRYPT -> processEncrypt();
            case DECRYPT -> processDecrypt();
            case BRUTEFORCE -> processBruteForce();
            case STATISTIC_ANALISE -> processStatisticAnalise();
        }
    }

    /**
     * Метод запускает взаимодействие с пользователем, при выборе статистического анализа.
     */
    private void processStatisticAnalise() {
        boolean isAgain = true;
        while (isAgain) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(System.in);
                System.out.println("-".repeat(100));
                System.out.print("Введите полный путь до файла источника: ");
                String fileSrcPath = scanner.nextLine();
                System.out.print("Введите полный путь до файла результата: ");
                String fileDstPath = scanner.nextLine();
                System.out.print("Введите полный путь до файла примера текста: ");
                String representativeText = scanner.nextLine();
                int key = new StatisticalAnalyzer().findMostLikelyShift(fileSrcPath, representativeText, new Alphabet());
                new CaesarCoder().encrypt(fileSrcPath, fileDstPath, key);
                isAgain = false;
            } catch (CaesarsCipherException e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
                isAgain = isTryAgain(scanner);
            }
        }
    }

    /**
     * Метод запускает взаимодействие с пользователем, при выборе расшифровки методом Цезаря.
     */
    private void processDecrypt() {
        boolean isAgain = true;
        while (isAgain) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(System.in);
                System.out.println("-".repeat(100));
                System.out.print("Введите полный путь до файла источника: ");
                String fileSrcPath = scanner.nextLine();
                System.out.print("Введите полный путь до файла результата: ");
                String fileDstPath = scanner.nextLine();
                System.out.print("Введите ключ: ");
                int key = Integer.parseInt(scanner.nextLine());
                new CaesarCoder().decrypt(fileSrcPath, fileDstPath, key);
                isAgain = false;
            } catch (CaesarsCipherException | NumberFormatException e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
                isAgain = isTryAgain(scanner);
            }
        }
    }

    /**
     * Метод запускает взаимодействие с пользователем, при выборе расшифровки методом брутфорса.
     */
    private void processBruteForce() {
        boolean isAgain = true;
        while (isAgain) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(System.in);
                System.out.println("-".repeat(100));
                System.out.print("Введите полный путь до файла источника: ");
                String fileSrcPath = scanner.nextLine();
                System.out.print("Введите полный путь до файла результата: ");
                String fileDstPath = scanner.nextLine();
                System.out.print("Введите полный путь до файла примера текста (или нажмите Enter): ");
                String representativeText = scanner.nextLine();
                new BruteForce().decrypt(fileSrcPath, fileDstPath, representativeText);
                isAgain = false;
            } catch (CaesarsCipherException e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
                isAgain = isTryAgain(scanner);
            }
        }
    }

    /**
     * Метод запускает взаимодействие с пользователем, при выборе шифрования методом Цезаря.
     */
    private void processEncrypt() {
        boolean isAgain = true;
        while (isAgain) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(System.in);
                System.out.println("-".repeat(100));
                System.out.print("Введите полный путь до файла источника: ");
                String fileSrcPath = scanner.nextLine();
                System.out.print("Введите полный путь до файла результата: ");
                String fileDstPath = scanner.nextLine();
                System.out.print("Введите ключ: ");
                int key = Integer.parseInt(scanner.nextLine());
                new CaesarCoder().encrypt(fileSrcPath, fileDstPath, key);
                isAgain = false;
            } catch (CaesarsCipherException | NumberFormatException e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
                isAgain = isTryAgain(scanner);
            }
        }

    }

    /**
     * Метод запускает взаимодействие с пользователем, при выборе выхода из приложения.
     */
    private void processExit() {
        System.out.println("Завершение работы.");
    }

    /**
     * Получить операцию по номеру. Если номер введен некорректно повторяет взяимодействие с пользователем, если
     * введено не again возвращает операцию EXIT.
     *
     * @return возвращает операцию в зависимости от введенного пользователем значения.
     */
    private Operation getOperation() {
        boolean isAgain = false;
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                if (isAgain) {
                    showMenu();
                }
                return Operation.getByNumber(Integer.parseInt(scanner.nextLine()));
            } catch (RuntimeException e) {
                System.out.println("Operation number is wrong.");
                System.out.println("Reason: " + e.getMessage());
                System.out.print("Введите \"again\" для повторного запуска: ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("again")) {
                    isAgain = true;
                }

            }
        } while (isAgain);

        return Operation.EXIT;
    }

    /**
     * Метод возвращет истину или ложь, если было введено again.
     *
     * @param scanner - объект сканера для ввода данных.
     * @return true если введено again, иначе false.
     */
    private boolean isTryAgain(Scanner scanner) {
        System.out.println("Чтобы попробовать снова введите \"again\":");
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        String s = scanner.nextLine();
        if (!s.equalsIgnoreCase("again")) {
            return false;
        }
        return true;
    }
}
