package com.epam.mjc.io;

import java.io.File;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileReader {
    public static Profile getDataFromFile(File file){
        Path filePath = file.toPath();
        String fileContent = null;
        try {
            fileContent = Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert fileContent != null;
        Map<String, String> keyValuePairs = parseStringForKeyValuePairs(fileContent);

        String name = keyValuePairs.get("Name");
        int age = Integer.parseInt(keyValuePairs.get("Age"));
        String email = keyValuePairs.get("Email");
        String phone = keyValuePairs.get("Phone");

        return new Profile(name, age, email, phone);
    }

    private static Map<String, String> parseStringForKeyValuePairs(String string) {
        Map<String, String> keyValuePairs = new HashMap<>();

        String[] lines = string.split("\n");
        for (String line : lines) {
            String[] parts = line.split(": ");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();
                keyValuePairs.put(key, value);
            }
        }

        return keyValuePairs;
    }
}
