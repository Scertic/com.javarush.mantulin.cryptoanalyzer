package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
    private BufferedReader bufferedReader;
    public String readFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        if (bufferedReader == null) {
            bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        }
//        if (!bufferedReader.ready()) {
//            bufferedReader.close();
//        }
        return bufferedReader.readLine();
    }
    public void writeFile(String content, String filePath) {
        //TODO Логика записи файла
    }
}
