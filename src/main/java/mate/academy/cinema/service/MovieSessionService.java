package mate.academy.cinema.service;

import java.time.LocalDate;
import java.util.List;
import mate.academy.cinema.model.MovieSession;

public interface MovieSessionService {
    MovieSession add(MovieSession session);

    MovieSession getById(Long id);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
