package com.example.popcornpicks.movie.feign.domain.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class YoutubeProperty {

    @Value("${youtube.key}")
    private String key;

    @Value("${youtube.part}")
    private String part;

    @Value("${youtube.type}")
    private String type;
}
