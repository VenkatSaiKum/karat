package com.wipro.karat_coding_problems;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

/*

 * Problem Description
You are given a file located at a URL. The file contains a list of URLs accessed by users. Your task is to:
- Download the file from the given URL and save it locally.
- Read the file contents line by line.
- Find the most popular URL (the one that appears most frequently).
- Print the most popular URL and its count.

Example Input (file contents)
https://example.com/home
https://example.com/setting
https://example.com/home
https://example.com/pro
https://example.com/kaka
https://example.com/miorss


Expected Output
Most popular URL: https://example.com/home
Count: 2

 * 
 */

public class PopularURLFinder {
    public static void main(String[] args) {
        try {
            // Step 1: Download file
            String fileUrl = "https://example.com/urls.txt"; // replace with actual URL
            String localFile = "urls.txt";
            downloadFile(fileUrl, localFile);

            // Step 2: Read file and count URLs
            Map<String, Integer> urlCountMap = new HashMap<>();
            try (BufferedReader br = new BufferedReader(new FileReader(localFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        urlCountMap.put(line, urlCountMap.getOrDefault(line, 0) + 1);
                    }
                }
            }

            // Step 3: Find most popular URL
            String mostPopularUrl = null;
            int maxCount = 0;
            for (Map.Entry<String, Integer> entry : urlCountMap.entrySet()) {
                if (entry.getValue() > maxCount) {
                    mostPopularUrl = entry.getKey();
                    maxCount = entry.getValue();
                }
            }

            // Step 4: Print result
            System.out.println("Most popular URL: " + mostPopularUrl);
            System.out.println("Count: " + maxCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to download file from URL
    public static void downloadFile(String fileUrl, String localFile) throws IOException {
        URL url = new URL(fileUrl);
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(localFile), StandardCopyOption.REPLACE_EXISTING);
        }
        System.out.println("File downloaded to: " + localFile);
    }
}