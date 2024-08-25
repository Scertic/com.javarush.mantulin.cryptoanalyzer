package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManager {
    private BufferedReader bufferedReader;
    public String readFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        if (bufferedReader == null) {
            bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        }
        //TODO закрытие потока?
        return bufferedReader.readLine();
    }
    public void writeFile(String content, String filePath) throws IOException {
        Path path = Path.of(filePath);
        Files.writeString(path, content, StandardOpenOption.APPEND);
    }
}
