package org.example.consoleui;

public enum Operation {
    EXIT("Выход"),
    ENCRYPT("Шифрование файла методом Цезаря"),
    DECRYPT("Дешифровка файла методом Цезаря"),
    BRUTEFORCE("Дешифровка файла перебором ключа"),
    STATISTIC_ANALISE("Статический анализ");

    private final String description;

    Operation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Operation getByNumber(int number) {
        for (Operation o : Operation.values()) {
            if (o.ordinal() == number) {
                return o;
            }
        }

        throw new IllegalArgumentException("Wrong number of operation");
    }

}
