package com.example.popcornpicks.movie.feign.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KmdbProperty {

    @Value("${kmdb.collection}")
    private String collection;

    @Value("${kmdb.serviceKey}")
    private String serviceKey;
}
