package cz.seznam.fulltext.robot.processor;

import cz.seznam.fulltext.robot.processor.api.Processor;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrepProcessor implements Processor {

    public void process(String regex) {
        Scanner scanner = new Scanner(System.in);

        Pattern pattern = Pattern.compile(regex);
        String line = scanner.nextLine();
        while (!line.isEmpty()) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                System.out.println(line);
            }
        }
    }
}
