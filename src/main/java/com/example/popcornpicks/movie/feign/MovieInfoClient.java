package com.example.popcornpicks.movie.feign;

import com.example.popcornpicks.movie.feign.domain.KobisCommonResponse;
import com.example.popcornpicks.movie.feign.domain.DailyBoxOfficeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "movieInfoClient", url = "${kobis.api.url}")
public interface MovieInfoClient {
    @GetMapping(path ="/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    KobisCommonResponse<DailyBoxOfficeDto.Response> getDailyBoxOfficeData(@SpringQueryMap DailyBoxOfficeDto.Request request);
}
