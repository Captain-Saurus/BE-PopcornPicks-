package com.example.popcornpicks.movie.service;

import com.example.popcornpicks.common.enums.HttpStatusCode;
import com.example.popcornpicks.common.exception.CommonException;
import com.example.popcornpicks.movie.domain.DailyBoxOfficeMovieDto;
import com.example.popcornpicks.movie.feign.domain.KobisCommonResponse;
import com.example.popcornpicks.movie.feign.domain.DailyBoxOffice;
import com.example.popcornpicks.movie.feign.MovieInfoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieInfoClient movieInfoClient;

    @Value("${kobis.key}")
    private String kobisKey;

    public List<DailyBoxOfficeMovieDto> getDailyBoxOfficeList() {
        DailyBoxOffice.Request request = new DailyBoxOffice.Request(
                kobisKey,
                LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        );

        DailyBoxOffice.Response response = this.requestDailyBoxOffice(request);
        return response.makeDailyBoxOfficeMovieDto();
    }

    private DailyBoxOffice.Response requestDailyBoxOffice(DailyBoxOffice.Request request) {
        KobisCommonResponse<DailyBoxOffice.Response> response = movieInfoClient.getDailyBoxOfficeData(request);

        if (response.isSuccess() == false) {
            throw new CommonException(HttpStatusCode.INTERNAL_SERVER_ERROR
                    , response.getFaltInfo().getMessage());
        }

        return response.getBoxOfficeResult();
    }

}
