package org.example.consoleui;

import org.example.alphabet.Alphabet;
import org.example.cipher.BruteForce;
import org.example.cipher.CaesarCoder;
import org.example.cipher.StatisticalAnalyzer;
import org.example.ecxeptions.CaesarsCipherException;

import java.util.Scanner;

public class ConsoleApp {

    private void showMenu() {
        System.out.println("-".repeat(100));
        for (Operation o : Operation.values()) {
            System.out.printf("%d - %s", o.ordinal(), o.getDescription());
            System.out.println();
        }
        System.out.print("Выберите способ: ");
    }

    public void start() {
        showMenu();
        Operation operation = getOperation();
        processOperation(operation);
    }

    private void processOperation(Operation operation) {
        switch (operation) {
            case EXIT -> processExit();
            case ENCRYPT -> processEncrypt();
            case DECRYPT -> processDecrypt();
            case BRUTEFORCE -> processBruteForce();
            case STATISTIC_ANALISE -> processStatisticAnalise();
        }
    }

    private void processStatisticAnalise() {
        boolean isAgain = false;
        while (!isAgain) {
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
                new CaesarCoder().decrypt(fileSrcPath, fileDstPath, key);
            } catch (CaesarsCipherException e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
                System.out.println("Чтобы попробовать снова введите \"again\":");
                String s = scanner.nextLine();
                if (!s.equalsIgnoreCase("again")) {
                    isAgain = true;
                }
            }
        }
    }

    private void processDecrypt() {
        boolean isAgain = false;
        while (!isAgain) {
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
            } catch (CaesarsCipherException | NumberFormatException e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
                System.out.println("Чтобы попробовать снова введите \"again\":");
                String s = scanner.nextLine();
                if (!s.equalsIgnoreCase("again")) {
                    isAgain = true;
                }
            }
        }
    }

    private void processBruteForce() {
        boolean isAgain = false;
        while (!isAgain) {
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
                new BruteForce().decrypt(fileSrcPath, fileDstPath, representativeText);
            } catch (CaesarsCipherException e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
                System.out.println("Чтобы попробовать снова введите \"again\":");
                String s = scanner.nextLine();
                if (!s.equalsIgnoreCase("again")) {
                    isAgain = true;
                }
            }
        }
    }

    private void processEncrypt() {
        boolean isAgain = false;
        while (!isAgain) {
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
            } catch (CaesarsCipherException | NumberFormatException e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
                System.out.println("Чтобы попробовать снова введите \"again\". Чтобы завершить введите любой символ.");
                String s = scanner.nextLine();
                if (!s.equalsIgnoreCase("again")) {
                    isAgain = true;
                }
            }
        }

    }

    private void processExit() {
        System.out.println("Завершение работы.");
    }

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
}
