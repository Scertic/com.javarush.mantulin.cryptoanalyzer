package org.example.consoleui;

/**
 * enum операций.
 */
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

    /**
     * Возвращает объект String с описанием операции.
     *
     * @return - объект String с описанием операции.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Возвращает операцию, по ее порядковому номеру.
     *
     * @param number - Порядковый номер операции.
     * @return Возвращает операцию, по ее порядковому номеру.
     * @throws IllegalArgumentException - если введен некорректный номер операции.
     */
    public static Operation getByNumber(int number) {
        for (Operation o : Operation.values()) {
            if (o.ordinal() == number) {
                return o;
            }
        }

        throw new IllegalArgumentException("Wrong number of operation");
    }

}
