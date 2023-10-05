package cz.seznam.fulltext.robot.processor.implementation;

import cz.seznam.fulltext.robot.entity.URLData;
import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;
import cz.seznam.fulltext.robot.processor.api.Processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrepProcessor implements Processor {
    private final Pattern pattern;

    public GrepProcessor(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public void process() throws InvalidFileFormatException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null) {
                proessLine(line, pattern);
            }
        } catch (IOException e) {
            throw new InvalidFileFormatException(e.getMessage());
        }
    }



    private static void proessLine(String line, Pattern pattern) {
        try {
            URLData urlData = new URLData(line);

            // Apply the regular expression to the URL
            Matcher matcher = pattern.matcher(urlData.getUrl());
            if (matcher.find()) {
                System.out.println(urlData);
            }
        } catch (Exception e) {
            // Invalid input line, skipping
            System.err.println(e.getMessage());
        }
    }

}
