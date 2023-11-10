package com.example.popcornpicks.movie.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "kmdbClient", url = "${kmdb.url}")
public interface KmdbClient {

    @GetMapping
    String getMovieDetailForPoster(@RequestParam("ServiceKey") String serviceKey,
                                   @RequestParam("collection") String collection,
                                   @RequestParam("detail") String detail,
                                   @RequestParam("title") String title,
                                   @RequestParam("releaseDts") String releaseDts);
}
