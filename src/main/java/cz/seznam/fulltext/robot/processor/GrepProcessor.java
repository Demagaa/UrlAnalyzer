package cz.seznam.fulltext.robot.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrepProcessor {
    public static void runGrepProcessor(String fileName, String regex) {
        Pattern pattern = Pattern.compile(regex);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Bad file format");
        }
    }
}
