package mate.academy.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;

import mate.academy.cinema.dto.request.MovieRequestDto;
import mate.academy.cinema.dto.response.MovieResponseDto;
import mate.academy.cinema.model.Movie;
import mate.academy.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public void addMovie(@RequestBody MovieRequestDto requestDto) {
        movieService.add(getMovieFromRequest(requestDto));
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(this::getResponseDto)
                .collect(Collectors.toList());
    }

    private Movie getMovieFromRequest(MovieRequestDto requestDto) {
        Movie movie = new Movie();
        movie.setTitle(requestDto.getTitle());
        movie.setDescription(requestDto.getDescription());
        return movie;
    }

    private MovieResponseDto getResponseDto(Movie movie) {
        MovieResponseDto responseDto = new MovieResponseDto();
        responseDto.setTitle(movie.getTitle());
        responseDto.setDescription(movie.getDescription());
        return responseDto;
    }
}
