package mate.academy.cinema.dto.request;

import java.time.LocalDateTime;

import mate.academy.cinema.model.MovieSession;
import mate.academy.cinema.service.CinemaHallService;
import mate.academy.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionRequestMapper {
    private MovieService movieService;
    private CinemaHallService cinemaHallService;

    @Autowired
    public MovieSessionRequestMapper(MovieService movieService,
                                     CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSession getFromDto(MovieSessionRequestDto requestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.getById(requestDto.getMovieId()));
        movieSession.setCinemaHall(
                cinemaHallService.getById(requestDto.getCinemaHallId()));
        movieSession.setShowTime(LocalDateTime.parse(requestDto.getShowTime()));
        return movieSession;
    }
}
