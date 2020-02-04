package mate.academy.cinema.dao;

import java.util.List;

import mate.academy.cinema.model.CinemaHall;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
