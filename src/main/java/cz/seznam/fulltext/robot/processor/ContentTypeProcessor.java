package cz.seznam.fulltext.robot.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ContentTypeProcessor {
    public static void runContentTypeProcessor(String fileName) {
        Map<String, Integer> contentTypeCounts = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length != 3) {
                    // Invalid input line, skipping
                    continue;
                }

                String contentType = parts[1];

                // Update or initialize the count for the content type
                contentTypeCounts.put(contentType, contentTypeCounts.getOrDefault(contentType, 0) + 1);
            }

            // Sort and output the content type counts
            contentTypeCounts.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> System.out.println(entry.getKey() + "\t" + entry.getValue()));
        } catch (IOException e) {
            throw new RuntimeException("Bad file format");

        }
    }
}
