package mate.academy.cinema.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import mate.academy.cinema.dto.request.MovieSessionRequestDto;
import mate.academy.cinema.dto.response.MovieSessionResponseDto;
import mate.academy.cinema.model.MovieSession;
import mate.academy.cinema.service.CinemaHallService;
import mate.academy.cinema.service.MovieService;
import mate.academy.cinema.service.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController {
    private MovieSessionService movieSessionService;
    private MovieService movieService;
    private CinemaHallService cinemaHallService;

    @Autowired
    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieService movieService,
                                  CinemaHallService cinemaHallService) {
        this.movieSessionService = movieSessionService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping
    public void addMovieSession(@Valid @RequestBody MovieSessionRequestDto requestDto) {
        movieSessionService.add(this.getFromDto(requestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAll(
            @RequestParam Long movieId, @RequestParam String date) {
        return movieSessionService.findAvailableSessions(movieId, LocalDate.parse(date))
                .stream()
                .map(this::getResponseDto)
                .collect(Collectors.toList());
    }

    private MovieSession getFromDto(MovieSessionRequestDto requestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.getById(requestDto.getMovieId()));
        movieSession.setCinemaHall(
                cinemaHallService.getById(requestDto.getCinemaHallId()));
        movieSession.setShowTime(LocalDateTime.parse(requestDto.getShowTime()));
        return movieSession;
    }

    private MovieSessionResponseDto getResponseDto(MovieSession movieSession) {
        MovieSessionResponseDto responseDto = new MovieSessionResponseDto();
        responseDto.setMovieTitle(movieSession.getMovie().getTitle());
        responseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        responseDto.setShowTime(movieSession.getShowTime().toString());
        return responseDto;
    }
}
