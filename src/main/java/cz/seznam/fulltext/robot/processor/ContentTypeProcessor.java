package cz.seznam.fulltext.robot.processor;

import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;
import cz.seznam.fulltext.robot.processor.api.Processor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ContentTypeProcessor implements Processor {

    public void process() throws InvalidFileFormatException {
        Map<String, Integer> contentTypeCounts = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null) {
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
        } catch (Exception e) {
            throw new InvalidFileFormatException(e.getMessage());
        }
    }
}
