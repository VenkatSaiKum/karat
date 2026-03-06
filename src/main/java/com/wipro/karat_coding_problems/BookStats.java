package com.wipro.karat_coding_problems;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

/*
 * Problem Description
You are given a CSV file hosted at a URL. Each row in the file contains information about a user’s reading activity with the following fields:
- userid (string or integer)
- name (string)
- bookname (string)
- no.of pages read (integer)

Your task is to:
- Download the file from the given URL and save it locally.
- Process the file to compute, for each user:
- The total number of books they have read (bookCount).
- The total number of pages they have read (totalPagesRead).
- Output the results in a readable format, showing each user’s userid, name, bookCount, and totalPagesRead.

Example Input (CSV file)
userid,name,bookname,no.of pages read
1,Alice,Book A,120
1,Alice,Book B,200
2,Bob,Book C,150


Example Output
User: Alice (ID: 1) | Books: 2 | Pages Read: 320
User: Bob (ID: 2) | Books: 1 | Pages Read: 150



This is the kind of concise, interview-style problem statement 

 */
public class BookStats {
    public static void main(String[] args) {
        try {
            // Step 1: Download file from URL
            String fileUrl = "https://example.com/yourfile.csv"; // replace with actual URL
            String localFile = "books.csv"; // file will be saved locally

            downloadFile(fileUrl, localFile);

            // Step 2: Process the CSV file
            Map<String, UserStats> statsMap = new HashMap<>();

            try (BufferedReader br = new BufferedReader(new FileReader(localFile))) {
                String line;
                boolean firstLine = true;
                while ((line = br.readLine()) != null) {
                    if (firstLine) { // skip header
                        firstLine = false;
                        continue;
                    }
                    String[] parts = line.split(",");
                    if (parts.length < 4) continue;

                    String userId = parts[0].trim();
                    String name = parts[1].trim();
                    String bookName = parts[2].trim();
                    int pagesRead = Integer.parseInt(parts[3].trim());

                    String key = userId + "-" + name;
                    statsMap.putIfAbsent(key, new UserStats(userId, name));
                    statsMap.get(key).addBook(bookName, pagesRead);
                }
            }

            // Step 3: Display results
            for (UserStats us : statsMap.values()) {
                System.out.println(us);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to download file
    public static void downloadFile(String fileUrl, String localFile) throws IOException {
        URL url = new URL(fileUrl);
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(localFile), StandardCopyOption.REPLACE_EXISTING);
        }
        System.out.println("File downloaded to: " + localFile);
    }
}

// Helper class to store stats
class UserStats {
    String userId;
    String name;
    int bookCount;
    int totalPages;

    UserStats(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.bookCount = 0;
        this.totalPages = 0;
    }

    void addBook(String bookName, int pagesRead) {
        bookCount++;
        totalPages += pagesRead;
    }

    @Override
    public String toString() {
        return "User: " + name + " (ID: " + userId + ") | Books: " + bookCount + " | Pages Read: " + totalPages;
    }
}