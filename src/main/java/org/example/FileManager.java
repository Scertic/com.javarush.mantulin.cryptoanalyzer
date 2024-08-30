package org.example;

import org.example.ecxeptions.CaesarsCipherException;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManager {
    private BufferedReader bufferedReader;

    public String readFile(String filePath){
        try {
            Path path = Path.of(filePath);
            if (bufferedReader == null) {
                bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            }
            //TODO закрытие потока?
            return bufferedReader.readLine();
        } catch (Exception e) {
            throw new CaesarsCipherException(e.getMessage());
        }
    }

    public void writeFile(String content, String filePath){
        try {
            Path path = Path.of(filePath);
            Files.writeString(path, content, StandardOpenOption.APPEND);
        } catch (Exception e) {
            throw new CaesarsCipherException(e.getMessage());
        }
    }
}
