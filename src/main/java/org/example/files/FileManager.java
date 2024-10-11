package org.example.files;

import org.example.ecxeptions.CaesarsCipherException;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Класс для работы с файлами. Использует для чтения Files.newBufferedReader, что требует закрытие потока чтения после
 * считывания файла методом close().
 */
public class FileManager implements Closeable {
    private BufferedReader bufferedReader;

    /**
     * Метод читает и возвращает строку из файла.
     *
     * @param filePath - путь к файлу для чтения.
     * @return возвращает объект типа String, возвращает null, если файл был считан.
     * @throws CaesarsCipherException
     */
    public String readLineFromFile(String filePath) {
        try {
            Path path = Path.of(filePath);
            if (bufferedReader == null) {
                bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            }
            //TODO закрытие потока?
            return bufferedReader.readLine();
        } catch (IOException | RuntimeException e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }

    /**
     * Добавляет строку в файл.
     *
     * @param content  - Строка с данными для записи.
     * @param filePath - Файл для записи.
     * @throws CaesarsCipherException
     */
    public void appendToFile(String content, String filePath) {
        try {
            Path path = Path.of(filePath);
            Files.writeString(path, content, StandardOpenOption.APPEND);
        } catch (IOException | RuntimeException e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }

    /**
     * Создает файл по пути.
     *
     * @param filePath - путь к файлу.
     * @throws CaesarsCipherException
     */
    public void createFile(String filePath) {
        try {
            Files.createFile(Path.of(filePath));
        } catch (IOException | RuntimeException e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }

    /**
     * Закрытие потока для чтения.
     */
    @Override
    public void close() {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }
}
