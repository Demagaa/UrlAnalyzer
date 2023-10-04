package cz.seznam.fulltext.robot.entity;

class URLData {
    String url;
    String contentType;
    int clickCount;

    URLData(String url, String contentType, int clickCount) {
        this.url = url;
        this.contentType = contentType;
        this.clickCount = clickCount;
    }
}
