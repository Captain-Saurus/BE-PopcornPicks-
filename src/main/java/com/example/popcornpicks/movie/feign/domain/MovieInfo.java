package com.example.popcornpicks.movie.feign.domain;

import lombok.Data;

import java.util.List;

/**
 * @Docs https://www.kobis.or.kr/kobisopenapi/homepg/apiservice/searchServiceInfo.do
 */

@Data
public class MovieInfo {

    @Data
    public static class Request{
        /**
         * 발급 받은 키 값
         */
        private final String key;

        /**
         * 영화코드
         */
        private final String movieCd;
    }

    @Data
    public static class Response{
        private Detail movieInfo;
    }

    @Data
    public static class Detail{
        /**
         * 영화코드
         */
        private String movieCd;

        /**
         * 영화명(국문)
         */
        private String movieNm;

        /**
         * 영화명(영문)
         */
        private String movieNmEn;

        /**
         * 영화명(원문)
         */
        private String movieNmOg;

        /**
         * 제작연도
         */
        private String prdtYear;

        /**
         * 상영시간
         */
        private String showTm;

        /**
         * 개봉연도
         */
        private String openDt;

        /**
         * 제작상태명
         */
        private String prdtStatNm;

        /**
         * 영화유형명
         */
        private String typeNm;

        /**
         * 제작 국가
         */
        private List<Nation> nations;

        /**
         * 장르
         */
        private List<Genre> genres;

        // 감독, 배우 ..
    }

    @Data
    private static class Nation{
        /**
         * 제작 국가명
         */
        private String nationNm;
    }

    @Data
    private static class Genre{
        /**
         * 장르명
         */
        private String genreNm;
    }
}
