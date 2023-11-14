package com.example.popcornpicks.movie.feign.domain.property;

import lombok.Data;

import java.util.List;

public class YoutubeSearchDto {

    @Data
    public static class Request {
        private final String part;
        private final int maxResults;
        private final String q;
        private final String type;
        private final String key;

        public Request(YoutubeProperty property, int maxResults, String q) {
            this.part = property.getPart();
            this.maxResults = maxResults;
            this.q = q.concat(" 메인 예고편");
            this.type = property.getType();
            this.key = property.getKey();
        }
    }

    @Data
    public static class Response {
        private List<Item> items;

        public String extractVideoId() {
            return items.get(0).id.videoId;
        }
    }

    @Data
    public static class Item {
        private Id id;
    }

    @Data
    public static class Id {
        private String videoId;
    }
}
