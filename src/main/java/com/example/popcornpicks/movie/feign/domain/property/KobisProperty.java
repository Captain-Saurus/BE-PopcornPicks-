package com.example.popcornpicks.movie.feign.domain.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KobisProperty {

    @Value("${kobis.key}")
    private String key;
}
