package com.example.popcornpicks.movie.feign.domain;

import com.example.popcornpicks.movie.domain.MovieDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Docs https://www.kobis.or.kr/kobisopenapi/homepg/apiservice/searchServiceInfo.do
 */
@Data
public class Movie {

    @Data
    public static class Request{
        /**
         * 발급 받은 키 값
         */
        private final String key;

        /**
         * 현재 페이지를 지정합니다.(default : “1”)
         */
        private final String curPage;

        /**
         * 결과 Row의 개수
         * default : "10"
         */
        private final String itemPerPage;
    }

    @Data
    public static class Response {
        private int totCnt;
        private String source;

        /**
         * 영화 상세 데이터
         */
        private List<Detail> movieList;

        public List<MovieDto> makeMovieDto() {
            return movieList.stream()
                .map(Detail::toResponse)
                .collect(Collectors.toList());
        }
    }

    @Data
    public static class Detail{
        /**
         * 영화코드
         */
        private String movieCd;

        /**
         * 영화명
         */
        private String movieNm;

        /**
         * 영화명(영문)
         */
        private String movieNmEn;

        /**
         * 제작연도
         */
        private String prdtYear;

        /**
         * 개봉일
         */
        private String openDt;

        /**
         * 영화유형
         */
        private String typeNm;

        /**
         * 제작상태
         */
        private String prdStatNm;

        /**
         * 제작국가(전체)
         */
        private String nationAlt;

        /**
         * 영화장르(전체)
         */
        private String genreAlt;

        /**
         * 대표 제작국가명
         */
        private String repNationNm;

        /**
         * 대표 장르명
         */
        private String repGenreNm;

        // + 감독, 회사

        public MovieDto toResponse(){
            return new MovieDto(this.movieCd, this.movieNm, this.openDt);
        }
    }

}
