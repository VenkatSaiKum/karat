package com.wipro.karat_coding_problems;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) throws IOException {
		 
        String url = "https://public.karat.io/content/products.txt";
        
        Path target = Path.of("downloads/products.txt");
        
        Files.createDirectories(target.getParent());
 
        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }
 
        System.out.println("Downloaded to: " + target.toAbsolutePath());
    }
}
