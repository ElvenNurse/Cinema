package mate.academy.cinema.service;

import java.util.List;

import mate.academy.cinema.model.Movie;

public interface MovieService {
    Movie add(Movie movie);
    
    List<Movie> getAll();
}
