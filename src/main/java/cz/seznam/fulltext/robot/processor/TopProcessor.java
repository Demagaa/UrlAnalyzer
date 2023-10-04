package cz.seznam.fulltext.robot.processor;

import cz.seznam.fulltext.robot.processor.api.Processor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TopProcessor implements Processor {

    Map<String, Integer> urlClickCounts = new HashMap<>();
    String[] topTenUrls = new String[11];

    public void process(String regex) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length != 3) {
                    System.out.println("Invalid input line, skipping");
                    continue;
                }

                String url = parts[0];
                int clickCount = Integer.parseInt(parts[2]);
                if (checkIfInTopTen(clickCount)) {
                    insertInTopTenUrls(clickCount, url);
                    urlClickCounts.put(url, urlClickCounts.getOrDefault(url, 0) + clickCount);
                }
            }

            // Sort and output the top URLs by click count
            urlClickCounts.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(10) // Limit to the top 10 URLs
                    .forEach(entry -> System.out.println(entry.getKey() + "\t" + entry.getValue()));
        } catch (Exception e) {
            e.printStackTrace();
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
