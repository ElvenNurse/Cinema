package mate.academy.cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;

import mate.academy.cinema.exception.AuthenticationException;
import mate.academy.cinema.lib.Injector;
import mate.academy.cinema.model.CinemaHall;
import mate.academy.cinema.model.Movie;
import mate.academy.cinema.model.MovieSession;
import mate.academy.cinema.service.AuthenticationService;
import mate.academy.cinema.service.CinemaHallService;
import mate.academy.cinema.service.MovieService;
import mate.academy.cinema.service.MovieSessionService;
import mate.academy.cinema.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    private static Injector injector = Injector.getInstance("mate.academy.cinema");

    public static void main(String[] args) {
        LOGGER.debug("Start Movie test");

        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        movieService.getAll().forEach(LOGGER::debug);

        Movie movieFF = new Movie();
        movieFF.setTitle("Fast and Furious");
        movieService.add(movieFF);

        Movie movieLotR = new Movie();
        movieLotR.setTitle("Lord of the Rings");
        movieService.add(movieLotR);

        Movie movieSW = new Movie();
        movieSW.setTitle("Star Wars");
        movieService.add(movieSW);

        movieService.getAll().forEach(LOGGER::debug);

        LOGGER.debug("Start Cinema Hall test");

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);

        cinemaHallService.getAll().forEach(LOGGER::debug);

        CinemaHall hallRed = new CinemaHall();
        hallRed.setCapacity(100);
        cinemaHallService.add(hallRed);

        CinemaHall hallGreen = new CinemaHall();
        hallGreen.setCapacity(200);
        cinemaHallService.add(hallGreen);

        CinemaHall hallBlue = new CinemaHall();
        hallBlue.setCapacity(300);
        cinemaHallService.add(hallBlue);

        cinemaHallService.getAll().forEach(LOGGER::debug);

        LOGGER.debug("Start Movie Session test");

        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);

        movieSessionService.findAvailableSessions(movieFF.getId(), LocalDate.now())
                .forEach(LOGGER::debug);

        MovieSession redSessionFF = new MovieSession();
        redSessionFF.setMovie(movieFF);
        redSessionFF.setCinemaHall(hallRed);
        redSessionFF.setShowTime(LocalDateTime.now());
        movieSessionService.add(redSessionFF);

        MovieSession greenSessionFF = new MovieSession();
        greenSessionFF.setMovie(movieFF);
        greenSessionFF.setCinemaHall(hallGreen);
        greenSessionFF.setShowTime(LocalDateTime.now().plusDays(1));
        movieSessionService.add(greenSessionFF);

        MovieSession redSessionSW = new MovieSession();
        redSessionSW.setMovie(movieSW);
        redSessionSW.setCinemaHall(hallRed);
        redSessionSW.setShowTime(LocalDateTime.now().plusHours(2));
        movieSessionService.add(redSessionSW);

        MovieSession blueSessionLotR = new MovieSession();
        blueSessionLotR.setMovie(movieLotR);
        blueSessionLotR.setCinemaHall(hallBlue);
        blueSessionLotR.setShowTime(LocalDateTime.now());
        movieSessionService.add(blueSessionLotR);

        movieSessionService.findAvailableSessions(movieFF.getId(), LocalDate.now())
                .forEach(LOGGER::debug);
        LOGGER.debug("Expecting " + redSessionFF);
        movieSessionService.findAvailableSessions(movieSW.getId(), LocalDate.now())
                .forEach(LOGGER::debug);
        LOGGER.debug("Expecting " + redSessionSW);
        movieSessionService.findAvailableSessions(movieLotR.getId(), LocalDate.now())
                .forEach(LOGGER::debug);
        LOGGER.debug("Expecting " + blueSessionLotR);

        LOGGER.debug("Start User test");

        String email = "voland92@ukr.net";
        String password = "123";

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);

        /*try {
            LOGGER.debug(authenticationService.login(email, password));
        } catch (AuthenticationException e) {
            LOGGER.debug("Empty user error");
        }*/

        authenticationService.register(email, password);

        try {
            LOGGER.debug(authenticationService.login(email, password));
        } catch (AuthenticationException e) {
            LOGGER.debug("Created user error");
        }

        UserService userService = (UserService) injector.getInstance(UserService.class);

        LOGGER.debug(userService.findByEmail("voland92@ukr.net"));
    }
}
