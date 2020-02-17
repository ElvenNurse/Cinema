package mate.academy.cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;

import mate.academy.cinema.config.AppConfig;
import mate.academy.cinema.exception.AuthenticationException;
import mate.academy.cinema.model.CinemaHall;
import mate.academy.cinema.model.Movie;
import mate.academy.cinema.model.MovieSession;
import mate.academy.cinema.model.User;
import mate.academy.cinema.service.AuthenticationService;
import mate.academy.cinema.service.CinemaHallService;
import mate.academy.cinema.service.MovieService;
import mate.academy.cinema.service.MovieSessionService;
import mate.academy.cinema.service.OrderService;
import mate.academy.cinema.service.ShoppingCartService;
import mate.academy.cinema.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        LOGGER.debug("Start Movie test");

        MovieService movieService = context.getBean(MovieService.class);

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

        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);

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
                context.getBean(MovieSessionService.class);

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
                context.getBean(AuthenticationService.class);

        LOGGER.debug(authenticationService.register(email, password));

        try {
            LOGGER.debug(authenticationService.login(email, password));
        } catch (AuthenticationException e) {
            LOGGER.debug("Created user login error");
        }

        UserService userService = context.getBean(UserService.class);

        User user = userService.findByEmail("voland92@ukr.net");
        LOGGER.debug(user);

        LOGGER.debug("Shopping Cart test");

        ShoppingCartService shoppingCartService =
                context.getBean(ShoppingCartService.class);

        LOGGER.debug(shoppingCartService.getByUser(user));

        shoppingCartService.addSession(redSessionFF, user);
        LOGGER.debug(shoppingCartService.getByUser(user));

        shoppingCartService.addSession(blueSessionLotR, user);
        LOGGER.debug(shoppingCartService.getByUser(user));

        LOGGER.debug("Order test");

        OrderService orderService = context.getBean(OrderService.class);

        orderService.getOrderHistory(user).forEach(LOGGER::debug);

        orderService.completeOrder(user);
        orderService.getOrderHistory(user).forEach(LOGGER::debug);

        shoppingCartService.addSession(redSessionSW, user);
        shoppingCartService.addSession(greenSessionFF, user);

        orderService.completeOrder(user);
        orderService.getOrderHistory(user).forEach(LOGGER::debug);
    }
}
