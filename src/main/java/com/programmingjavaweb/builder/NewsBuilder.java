package com.programmingjavaweb.builder;

public class NewsBuilder {
    private String title;
    private String categoryCode;
    private Integer view;

    public NewsBuilder(Builder builder) {
        this.title = builder.title;
        this.categoryCode = builder.categoryCode;
        this.view = builder.view;
    }

    public static class Builder {
        private String title;
        private String categoryCode;
        private Integer view;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCategoryCode(String categoryCode) {
            this.categoryCode = categoryCode;
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
