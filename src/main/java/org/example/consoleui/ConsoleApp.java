package org.example.consoleui;

import org.example.cipher.BruteForce;
import org.example.cipher.CaesarCoder;
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
        }
    }

    private void processDecrypt() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("-".repeat(100));
            System.out.print("Введите полный путь до файла источника: ");
            String fileSrcPath = scanner.nextLine();
            System.out.print("Введите полный путь до файла результата: ");
            String fileDstPath = scanner.nextLine();
            System.out.print("Введите ключ: ");
            int key = scanner.nextInt();
            new CaesarCoder().decrypt(fileSrcPath, fileDstPath, key);
        } catch (CaesarsCipherException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processBruteForce() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("-".repeat(100));
            System.out.print("Введите полный путь до файла источника: ");
            String fileSrcPath = scanner.nextLine();
            System.out.print("Введите полный путь до файла результата: ");
            String fileDstPath = scanner.nextLine();
            System.out.print("Введите полный путь до файла примера текста: ");
            String representativeText = scanner.nextLine();
            new BruteForce().decrypt(fileSrcPath, fileDstPath, representativeText);
        } catch (CaesarsCipherException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processEncrypt() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("-".repeat(100));
            System.out.print("Введите полный путь до файла источника: ");
            String fileSrcPath = scanner.nextLine();
            System.out.print("Введите полный путь до файла результата: ");
            String fileDstPath = scanner.nextLine();
            System.out.print("Введите ключ: ");
            int key = scanner.nextInt();
            new CaesarCoder().encrypt(fileSrcPath, fileDstPath, key);
        } catch (CaesarsCipherException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
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
