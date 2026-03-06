package com.wipro.karat_coding_problems;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ReferralDomains {
    public static void main(String[] args) {
        try {
            // Load the file from the given URL
           //URL fileUrl = new URL("https://public.karat.io/content/referrals_4.txt");
          // BufferedReader reader = new BufferedReader(new InputStreamReader(fileUrl.openStream()));
        	
        	
        		BufferedReader br = new BufferedReader(new FileReader("referrals_4.txt"));

            // Read the first two lines (URLs)
            String firstUrl = br.readLine();
            String secondUrl = br.readLine();
            br.close();

            // Process and print results
            System.out.println("1st URL: " + extractDomains(firstUrl));
            System.out.println("2nd URL: " + extractDomains(secondUrl));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String extractDomains(String rawUrl) {
        // Remove protocol
	 
        if (rawUrl.startsWith("http://")) {
            rawUrl = rawUrl.substring(7); //world.news.yahoo.com/news/olympics/
        } else if (rawUrl.startsWith("https://")) {
            rawUrl = rawUrl.substring(8); //world.news.yahoo.com/news/olympics/
        }

        // Isolate domain (before first '/')
        String domain = rawUrl.split("/")[0];//world.news.yahoo.com

        // Split domain into parts
        String[] parts = domain.split("\\.");

        // Last two pieces
        String lastTwo;
        if (parts.length >= 2) {
            lastTwo = parts[parts.length - 2] + "." + parts[parts.length - 1];
        } else {
            lastTwo = domain;
        }

        return "[\"" + domain + "\", \"" + lastTwo + "\"]";
    }
}
