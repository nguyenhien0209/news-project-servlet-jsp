package com.programmingjavaweb.builder;

public class NewsBuilder {
    private String title;
    private String categoryCode;
    private Integer view;

    public static class Builder {
        private String title;
        private String categoryCode;
        private Integer view;
    }
}
