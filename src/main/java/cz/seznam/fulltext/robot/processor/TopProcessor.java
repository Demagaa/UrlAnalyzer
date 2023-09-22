package cz.seznam.fulltext.robot.processor;

import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TopProcessor {
    private TopProcessor() {
        throw new IllegalStateException("Utility class");
    }

    public static void runTopProcessor(String fileName) throws InvalidFileFormatException {
        Map<String, Integer> urlClickCounts = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length != 3) {
                    System.out.println("Invalid input line, skipping");
                    continue;
                }

                String url = parts[0];
                int clickCount = Integer.parseInt(parts[2]);

                // Update or initialize the click count for the URL
                urlClickCounts.put(url, urlClickCounts.getOrDefault(url, 0) + clickCount);
            }

            // Sort and output the top URLs by click count
            urlClickCounts.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(10) // Limit to the top 10 URLs
                    .forEach(entry -> System.out.println(entry.getKey() + "\t" + entry.getValue()));
        } catch (IOException e) {
            throw new InvalidFileFormatException("Bad file format");
        }
    }
}
