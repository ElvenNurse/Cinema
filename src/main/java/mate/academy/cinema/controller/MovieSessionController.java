package mate.academy.cinema.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import mate.academy.cinema.dto.request.MovieSessionRequestDto;
import mate.academy.cinema.dto.request.MovieSessionRequestMapper;
import mate.academy.cinema.dto.response.MovieSessionResponseDto;
import mate.academy.cinema.dto.response.MovieSessionResponseMapper;
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
    private MovieSessionResponseMapper responseMapper;
    private MovieSessionRequestMapper requestMapper;

    @Autowired
    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionResponseMapper responseMapper,
                                  MovieSessionRequestMapper requestMapper) {
        this.movieSessionService = movieSessionService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @PostMapping
    public void addMovieSession(
            @RequestBody MovieSessionRequestDto requestDto) {
        movieSessionService.add(requestMapper.getFromDto(requestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAll(
            @RequestParam Long movieId, @RequestParam String date) {
        return movieSessionService.findAvailableSessions(movieId, LocalDate.parse(date))
                .stream()
                .map(responseMapper::getResponseDto)
                .collect(Collectors.toList());
    }
}
