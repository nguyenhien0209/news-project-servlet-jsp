package com.programmingjavaweb.builder;

public class NewsBuilder {
    private String title;
    private String code;
    private Integer view;

    public NewsBuilder(Builder builder) {
        this.title = builder.title;
        this.code = builder.code;
        this.view = builder.view;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public static class Builder {
        private String title;
        private String code;
        private Integer view;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setView(Integer view) {
            this.view = view;
            return this;
        }

        public NewsBuilder build() {
            return new NewsBuilder(this);
        }
    }
}
