package cz.seznam.fulltext.robot.processor;

import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;
import cz.seznam.fulltext.robot.processor.api.Processor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrepProcessor implements Processor {

    public void process(String regex) throws InvalidFileFormatException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Pattern pattern = Pattern.compile(regex);
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            throw new InvalidFileFormatException(e.getMessage());
        }
    }
}
