package com.example.popcornpicks.movie.feign.domain;

import com.example.popcornpicks.movie.domain.DailyBoxOfficeMovieDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Docs https://www.kobis.or.kr/kobisopenapi/homepg/apiservice/searchServiceInfo.do
 */
@Data
public class DailyBoxOfficeDto {



    @Data
    public static class Request {
        /**
         * 발급 받은 키 값
         */
        private final String key;

        /**
         * 조회하고자 하는 날짜를 yyyymmdd 형식으로 입력합니다.
         */
        private final String targetDt;

        /**
         * 결과 Row의 개수
         * default : "10", max : "10"
         */
        private final String itemPerPage = "10";
    }

    @Data
    public static class Response {

        /**
         * 박스오피스 종류
         */
        private String boxofficeType;

        /**
         * 박스오피스 조회 일자
         */
        private String showRange;

        /**
         * 상세 데이터
         */
        private List<Detail> dailyBoxOfficeList;

        public List<Detail> getTopNDailyBoxOfficeList(int n) {
            return dailyBoxOfficeList.stream()
                    .filter(vo -> Integer.parseInt(vo.getRank()) <= n)
                    .collect(Collectors.toList());
        }
    }

    @Data
    public static class Detail {

        /**
         * 순번
         */
        private String rnum;

        /**
         * 해당 일자의 박스오피스 순위
         */
        private String rank;

        /**
         * 전일대비 순위의 증감분
         */
        private String rankInten;

        /**
         * 랭킹에 신규 진입 여부 출력
         * - OLD: 기존
         * - NEW: 신규
         */
        private String rankOldAndNew;

        /**
         * 영화의 대표코드
         */
        private String movieCd;

        /**
         * 영화명 (국문)
         */
        private String movieNm;

        /**
         * 영화 개봉일
         */
        private String openDt;

        /**
         * 해당일의 매출액
         */
        private String salesAmt;

        /**
         * 해당일자 상영작의 매출 총액 대비 해당 영화의 매출 비율 출력
         */
        private String salesShare;

        /**
         * 전일 대비 매출액 증감분
         */
        private String salesInten;

        /**
         * 전일 대비 매출액 증감 비율
         */
        private String salesChange;

        /**
         * 누적 매출액
         */
        private String salesAcc;

        /**
         * 해당일의 관객수
         */
        private String audiCnt;

        /**
         * 전일 대비 관객수 증감분
         */
        private String audiInten;

        /**
         * 전일 대비 관객수 증감 비율
         */
        private String audiChange;

        /**
         * 누적 관객수
         */
        private String audiAcc;

        /**
         * 해당일자에 상영한 스크린수
         */
        private String scrnCnt;

        /**
         * 해당 일자에 상영된 횟수
         */
        private String showCnt;


        public DailyBoxOfficeMovieDto toResponse(String movieImageUrl) {
            return new DailyBoxOfficeMovieDto(
                Integer.parseInt(this.rank),
                movieImageUrl,
                this.movieNm,
                this.rankInten
            );
        }
    }

}
