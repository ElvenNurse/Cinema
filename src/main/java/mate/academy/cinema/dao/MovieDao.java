package mate.academy.cinema.dao;

import java.util.List;

import mate.academy.cinema.model.Movie;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
