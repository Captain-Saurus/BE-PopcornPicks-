package com.example.popcornpicks.movie.feign.domain;

import lombok.Data;

import java.util.List;

@Data
public class KmdbDto {
    @Data
    public static class Response {
        public List<Detail> Data;

        public String extractPostUrl() {
            String[] splitPostArray = Data.get(0).getResult().get(0).getPosters().split("\\|");
            return splitPostArray[0];
        }
    }

    @Data
    public static class Detail {
        public List<Result> Result;
    }

    @Data
    public static class Result {
        public String posters;
    }

    @Data
    public static class Request {
        private final String collection = "kmdb_new2";
        private final String serviceKey = "8VWF4U07N5R1DB11Q12V";
        private final String detail;
        private final String title;
    }

}
