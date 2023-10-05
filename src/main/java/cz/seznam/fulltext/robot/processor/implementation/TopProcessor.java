package cz.seznam.fulltext.robot.processor.implementation;

import cz.seznam.fulltext.robot.entity.URLData;
import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;
import cz.seznam.fulltext.robot.processor.api.Processor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TopProcessor implements Processor {

    Map<String, Integer> urlClickCounts = new HashMap<>();
    String[] topTenUrls = new String[11];

    public void process() throws InvalidFileFormatException {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    URLData urlData = new URLData(line);

                    String url = urlData.getUrl();
                    int clickCount = urlData.getClickCount();
                    if (checkIfInTopTen(clickCount)) {
                        insertInTopTenUrls(clickCount, url);
                        urlClickCounts.put(url, urlClickCounts.getOrDefault(url, 0) + clickCount);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input line, skipping: " + line);
                }
            }

            // Sort and output the top URLs by click count
            urlClickCounts.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(10) // Limit to the top 10 URLs
                    .forEach(entry -> System.out.println(entry.getKey() + "\t" + entry.getValue()));
        } catch (Exception e) {
            throw new InvalidFileFormatException(e.getMessage());
        }
    }


    private boolean checkIfInTopTen(int clickCount) {
        return (getCountByTopIndex(0) == null || getCountByTopIndex(0) <= clickCount);
    }

    private void insertInTopTenUrls(int clickCount, String url) {
        for (int i = topTenUrls.length - 1; i >= 0; i--) {
            Integer count = getCountByTopIndex(i);
            if ((count == null) || (count < clickCount)) {
                insertInTopTenUrlsOnIndex(i, url);
                break;
            }
        }
    }

    private void insertInTopTenUrlsOnIndex(int i, String url) {
        moveRecordsByOneFrom(i);
        topTenUrls[i] = url;
    }

    private void moveRecordsByOneFrom(int i) {
        for (int j = 0; j < i; j++) {
            topTenUrls[j] = topTenUrls[j + 1];
        }
    }

    private Integer getCountByTopIndex(int index) {
        return urlClickCounts.get(topTenUrls[index]);
    }

}
