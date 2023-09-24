package com.example.popcornpicks.movie.domain;

import lombok.Data;

import java.util.List;
@Data
public class DailyBoxOfficeDto {

    @Data
    public static class Request {
        private final String key;
        private final String targetDt;
        private final String itemPerPage = "10";
    }

    @Data
    public static class Response {
        private String boxofficeType;
        private String showRange;
        private List<Detail> dailyBoxOfficeList;
    }

    @Data
    public static class Detail {
        private String rnum;
        private String rank;
        private String rankInten;
        private String rankOldAndNew;
        private String movieCd;
        private String movieNm;
        private String openDt;
        private String salesAmt;
        private String salesShare;
        private String salesInten;
        private String salesChange;
        private String salesAcc;
        private String audiCnt;
        private String audiInten;
        private String audiChange;
        private String audiAcc;
        private String scrnCnt;
        private String showCnt;
    }

}
