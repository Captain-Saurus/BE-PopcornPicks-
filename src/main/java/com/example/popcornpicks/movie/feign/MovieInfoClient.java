package com.example.popcornpicks.movie.feign;

import com.example.popcornpicks.movie.feign.domain.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "movieInfoClient", url = "${kobis.api.url}")
public interface MovieInfoClient {
    @GetMapping(path ="/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    KobisCommonResponse<DailyBoxOffice.Response> getDailyBoxOfficeData(@SpringQueryMap DailyBoxOffice.Request request);

    @GetMapping(path ="/webservice/rest/movie/searchMovieList.json")
    KobisCommonResponse2 getMovieList(@SpringQueryMap Movie.Request request);

    @GetMapping(path ="/webservice/rest/movie/searchMovieInfo.json")
    KobisCommonResponse3 getMovieInfoList(@SpringQueryMap MovieInfo.Request request);

}
