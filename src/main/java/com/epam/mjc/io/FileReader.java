package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FileReader {
    public static Profile getDataFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader reader = new BufferedReader(isr);

        Map<String, String> keyValuePairs = new HashMap<>();

        String line = reader.readLine();
        while (line != null) {
            String[] parts = line.split(": ");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();
                keyValuePairs.put(key, value);
            }
            line = reader.readLine();
        }

        reader.close();

        String name = keyValuePairs.get("Name");
        int age = Integer.parseInt(keyValuePairs.get("Age"));
        String email = keyValuePairs.get("Email");
        String phone = keyValuePairs.get("Phone");

        return new Profile(name, age, email, phone);
    }
}