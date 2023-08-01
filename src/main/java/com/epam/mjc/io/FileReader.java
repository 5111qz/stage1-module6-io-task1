package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public Profile getDataFromFile(File file) {
        Map<String, String> profileData = new HashMap<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            String rawData = "";
            int i = fileInputStream.read();
            while(i != -1) {
                rawData += (char)i;
                i = fileInputStream.read();
            }
            rawData = rawData.replace("\n", ": ");
            rawData = rawData.substring(0, rawData.length() - 2);
            fileInputStream.close();
            String[] rawDataSplit = rawData.split(": ");
            for(i = 0; i < rawDataSplit.length - 1; i = i + 2) {
                profileData.put(rawDataSplit[i], rawDataSplit[i+1]);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return new Profile(profileData.get("Name"),
                Integer.parseInt(profileData.get("Age")),
                profileData.get("Email"),
                Long.parseLong(profileData.get("Phone")));
    }
}
