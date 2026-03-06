package com.wipro.karat_coding_problems;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/*
 * Our web server receives block and release malicious IP from the network.
When a block request is received, the malicious IP address is blocked for a certain period, and later a release request may unblock it.

A log file contains entries in the following format:
2021-03-07 01:30.304 Block 74.152.231.66
2021-03-07 01:30.304 Block 74.152.231.66
2021-03-07 01:30.304 Release 74.152.231.66
2021-03-07 01:30.304 Block 74.152.231.66
...

The file is available at (www.karat.test/test/interview/blockedIP.txt)

Task:
Read the log file and calculate how many IP addresses were blocked on a given day.

Output Example:
Total blocked IPs for today: 669

 */



public class BlockedIPCounter {
    public static void main(String[] args) {
        // Adjust this path to point to your log file
        String filePath = "blockedIP.txt";  

        Set<String> blockedIPs = new HashSet<>();
        //int blockedCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Each line looks like: "2021-03-07 01:30.304 Block 74.152.231.66"
                String[] parts = line.split("\\s+");
                if (parts.length >= 3) {
                    String action = parts[2];
                    if (action.equalsIgnoreCase("Block")) {
                        blockedIPs.add(parts[3]);
                    }
                }
            }

            System.out.println("Total blocked IPs for today: " + blockedIPs.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
