package cz.seznam.fulltext.robot.entity;

public class URLData {
    private String url;
    private String contentType;
    private int clickCount;

    URLData(String url, String contentType, int clickCount) {
        this.url = url;
        this.contentType = contentType;
        this.clickCount = clickCount;
    }

    public URLData(String inputLine) {
        String[] parts = inputLine.split("\t");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid input line: " + inputLine);
        }

        this.url = parts[0];
        this.contentType = parts[1];
        this.clickCount = Integer.parseInt(parts[2]);
    }

    public String getUrl() {
        return url;
    }

    public String getContentType() {
        return contentType;
    }

    public int getClickCount() {
        return clickCount;
    }

    @Override
    public String toString() {
        return url + "\t" + contentType + "\t" + clickCount;
    }
}
