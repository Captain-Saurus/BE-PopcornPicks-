package com.example.popcornpicks.movie.feign;

import com.example.popcornpicks.movie.feign.domain.KobisCommonResponse;
import com.example.popcornpicks.movie.feign.domain.DailyBoxOffice;
import com.example.popcornpicks.movie.feign.domain.KobisCommonResponse2;
import com.example.popcornpicks.movie.feign.domain.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "movieInfoClient", url = "${kobis.api.url}")
public interface MovieInfoClient {
    @GetMapping(path ="/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    KobisCommonResponse<DailyBoxOffice.Response> getDailyBoxOfficeData(@SpringQueryMap DailyBoxOffice.Request request);

    @GetMapping(path ="/webservice/rest/movie/searchMovieList.json")
    KobisCommonResponse2 getMovie(@SpringQueryMap Movie.Request request);

}
