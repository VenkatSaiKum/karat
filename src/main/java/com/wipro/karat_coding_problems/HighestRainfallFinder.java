package com.wipro.karat_coding_problems;
import java.io.BufferedReader;
import java.io.FileReader;

/*
 * We have collected some rainfall data for various cities. The data is in a file called rainfall.txt, where each line contains a record in the following format:
 * date,city,rainfall
 * * For example:
 * 2023-11-12,NewYork,5
 * 2023-11-12,LosAngeles,0
 * 2023-11-12,Chicago,3
 * * The date is in the format YYYY-MM-DD, the city is a string, and rainfall is an integer representing the amount of rainfall in millimeters.
 * We want to find out which city had the highest rainfall and on which date. Write code that reads the file and prints the name of the city with the highest rainfall, the amount of rainfall, and the date it occurred.
 * Expected output:
 * Highest rainfall city: New York (5) on 2023-11-12
 */

public class HighestRainfallFinder {

    public static void main(String[] args) {
        // Change this to your actual file name/path
        String filePath = "rainfall.txt";

        String maxCity = null;
        String maxDate = null;
        int maxRainfall = Integer.MIN_VALUE;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // Expected: date,city,rainfall
                String[] parts = line.split(",");
                if (parts.length != 3) continue;

                String date = parts[0].trim();
                String city = parts[1].trim();

                int rainfall;
                try {
                    rainfall = Integer.parseInt(parts[2].trim());
                } catch (NumberFormatException e) {
                    continue; // skip invalid rainfall values
                }

                if (rainfall > maxRainfall) {
                    maxRainfall = rainfall;
                    maxCity = city;
                    maxDate = date;
                }
            }

            if (maxCity != null) {
                System.out.println("Highest rainfall city: " + maxCity +
                        " (" + maxRainfall + ") on " + maxDate);
            } else {
                System.out.println("No valid rainfall records found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}