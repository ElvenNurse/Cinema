package mate.academy.cinema.dao;

import java.time.LocalDate;
import java.util.List;

import mate.academy.cinema.model.MovieSession;

public interface MovieSessionDao {
    MovieSession add(MovieSession session);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
