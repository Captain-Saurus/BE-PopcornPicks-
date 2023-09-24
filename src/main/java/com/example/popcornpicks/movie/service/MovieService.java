package com.example.popcornpicks.movie.service;

import com.example.popcornpicks.movie.domain.KobisCommonResponse;
import com.example.popcornpicks.movie.domain.DailyBoxOffice;
import com.example.popcornpicks.movie.feign.MovieInfoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieInfoClient movieInfoClient;

    @Value("${kobis.key}")
    private String kobisKey;

    public KobisCommonResponse<DailyBoxOffice.Response> getDailyBoxOfficeList() {
        DailyBoxOffice.Request request = new DailyBoxOffice.Request(
                kobisKey,
                LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        );
        return movieInfoClient.getDailyBoxOfficeData(request);
    }

}
