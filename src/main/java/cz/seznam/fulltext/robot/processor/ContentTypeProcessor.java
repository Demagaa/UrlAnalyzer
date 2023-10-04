package cz.seznam.fulltext.robot.processor;

import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;
import cz.seznam.fulltext.robot.processor.api.Processor;

import java.util.HashMap;
import java.util.Map;

public class ContentTypeProcessor implements Processor {

    public void process(String regex) {
        Map<String, Integer> contentTypeCounts = new HashMap<>();

        String line = "";
        while (line.isEmpty()) {
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

    }
}
