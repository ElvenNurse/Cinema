package mate.academy.cinema.service.impl;

import java.util.List;

import mate.academy.cinema.dao.CinemaHallDao;
import mate.academy.cinema.lib.Inject;
import mate.academy.cinema.model.CinemaHall;
import mate.academy.cinema.service.CinemaHallService;

public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private static CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
