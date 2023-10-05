package cz.seznam.fulltext.robot.processor.implementation;

import cz.seznam.fulltext.robot.entity.URLData;
import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;
import cz.seznam.fulltext.robot.processor.api.Processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ContentTypeProcessor implements Processor {

    public void process() throws InvalidFileFormatException {
        Map<String, Integer> contentTypeCounts = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null) {
                processLine(line, contentTypeCounts);
            }

            // Sort and output the content type counts
            contentTypeCounts.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> System.out.println(entry.getKey() + "\t" + entry.getValue()));
        } catch (IOException e) {
            throw new InvalidFileFormatException(e.getMessage());
        }
    }

    private void processLine(String line, Map<String, Integer> contentTypeCounts) throws InvalidFileFormatException {
        try {
            URLData urlData = new URLData(line);
            String contentType = urlData.getContentType();

            // Update or initialize the count for the content type
            contentTypeCounts.put(contentType, contentTypeCounts.getOrDefault(contentType, 0) + 1);
        } catch (Exception e) {
            throw new InvalidFileFormatException(e.getMessage());
        }
    }
}
