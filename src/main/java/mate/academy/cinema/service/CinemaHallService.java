package mate.academy.cinema.service;

import java.util.List;
import mate.academy.cinema.model.CinemaHall;

public interface CinemaHallService {
    CinemaHall add(CinemaHall cinemaHall);

    CinemaHall getById(Long id);
    
    List<CinemaHall> getAll();
}
