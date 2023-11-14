package com.example.popcornpicks.movie.feign;

import com.example.popcornpicks.movie.feign.domain.property.YoutubeSearchDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "youtubeClient", url = "${youtube.url}")
public interface YoutubeClient {

    @GetMapping("/youtube/v3/search")
    YoutubeSearchDto.Response getTrailerMovieId(@SpringQueryMap YoutubeSearchDto.Request request);
}
