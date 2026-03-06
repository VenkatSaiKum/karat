package com.wipro.karat_coding_problems;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/*You are working at a large e-commerce site and we have some comma separated value (CSV) data containing product purchase data. This data is contained in a file at https://public.karat.io/content/products.txt
The data is chronological and contains: date,purchaser,product,price

* Date is in the format YYYY-MM-DD
* Purchaser is the user ID of the account making the purchase
* Product is the name of the product being purchased
* Price is the amount paid for the product in dollars

Example:
* 2023-11-12,98,Swiffer,$15.99
* 2023-11-12,11,Trimmer,$24.9
  * User 98 bought a Swiffer for $15.99
-----------------------------
We would like to find the user who spent the most money, and how many products they bought. Write code that reads the file, and prints the ID of the user who spent the most money, and how many products they purchased.

Expected Result:
518 - 18 (User 518 bought 18 products)*/


public class TopSpender {
    public static void main(String[] args) {
        String fileUrl = "https://public.karat.io/content/products.txt";

        // Maps to track total spending and product count per user
        Map<String, Double> userSpending = new HashMap<>();
        Map<String, Integer> userProductCount = new HashMap<>();

        try {
            URL url = new URL(fileUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) continue;

                String userId = parts[1].trim();
                String priceStr = parts[3].trim().replace("$", "");
                double price = Double.parseDouble(priceStr);

                // Update spending
                userSpending.put(userId, userSpending.getOrDefault(userId, 0.0) + price);

                // Update product count
                userProductCount.put(userId, userProductCount.getOrDefault(userId, 0) + 1);
            }
            br.close();

            // Find the user with max spending
            String topUser = null;
            double maxSpending = 0.0;

            for (Map.Entry<String, Double> entry : userSpending.entrySet()) {
                if (entry.getValue() > maxSpending) {
                    maxSpending = entry.getValue();
                    topUser = entry.getKey();
                }
            }

            if (topUser != null) {
                int productCount = userProductCount.get(topUser);
                System.out.println(topUser + " - " + productCount);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
