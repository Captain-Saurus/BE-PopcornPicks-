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

        /**
         * 감독
         */
        private List<Director> directors;

        /**
         * 배우
         */
        private List<Actor> actors;

        /**
         * 상영형태 구분
         */
        private List<ShowType> showTypes;

        /**
         * 심의정보
         */
        private List<Audit> audits;

        /**
         * 참여 영화사
         */
        private List<Company> companys;

        /**
         * 스텝
         */
        private List<Staff> staffs;
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

    @Data
    private static class Director{
        /**
         * 감독명
         */
        private String peopleNm;

        /**
         * 감독명(영문)
         */
        private String peopleNmEn;
    }

    @Data
    private static class Actor{
        /**
         * 배우명
         */
        private String peopleNm;

        /**
         * 배우명(영문)
         */
        private String peopleNmEn;

        /**
         * 배역명
         */
        private String cast;

        /**
         * 배역명(영문)
         */
        private String castEn;
    }

    @Data
    private static class ShowType{
        /**
         * 상영형태 구분
         */
        private String showTypeGroupNm;

        /**
         * 상영형태명
         */
        private String showTypeNm;
    }

    @Data
    private static class Audit{
        /**
         * 심의번호
         */
        private String auditNo;

        /**
         * 관람등급 명칭
         */
        private String watchGradeNm;
    }

    @Data
    private static class Company{
        /**
         * 참여 영화사 코드
         */
        private String companyCd;

        /**
         * 참여 영화사명
         */
        private String companyNm;

        /**
         * 참여 영화사명(영문)
         */
        private String companyNmEn;

        /**
         * 참여 영화사 분야명
         */
        private String companyPartNm;
    }

    @Data
    private static class Staff{
        /**
         * 스텝명
         */
        private String peopleNm;

        /**
         * 스텝명(영문)
         */
        private String peopleNmEn;

        /**
         * 스텝역할명
         */
        private String staffRoleNm;
    }
}
