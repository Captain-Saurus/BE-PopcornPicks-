package com.example.popcornpicks.movie.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MovieInfoDto {
    /**
     * 영화코드
     */
    private String movieCd;

    /**
     * 영화명(원문)
     */
    private String movieNmOg;

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
}
