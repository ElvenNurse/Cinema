package mate.academy.cinema.controller;

import java.security.Principal;
import java.util.stream.Collectors;
import javax.validation.Valid;

import mate.academy.cinema.dto.request.ShoppingCartRequestDto;
import mate.academy.cinema.dto.response.ShoppingCartResponseDto;
import mate.academy.cinema.dto.response.TicketResponseDto;
import mate.academy.cinema.model.MovieSession;
import mate.academy.cinema.model.ShoppingCart;
import mate.academy.cinema.model.Ticket;
import mate.academy.cinema.service.MovieSessionService;
import mate.academy.cinema.service.ShoppingCartService;
import mate.academy.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private MovieSessionService movieSessionService;
    private UserService userService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService,
                                  UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
    }

    @PostMapping("/addmoviesession")
    public void addMovieSession(@Valid @RequestBody ShoppingCartRequestDto requestDto,
                                Principal principal) {
        shoppingCartService.addSession(
                movieSessionService.getById(requestDto.getMovieSessionId()),
                userService.findByEmail(principal.getName()));
    }

    @GetMapping
    public ShoppingCartResponseDto getByUser(Principal principal) {
        return getResponseDto(shoppingCartService.getByUser(
                userService.findByEmail(principal.getName())));
    }

    private ShoppingCartResponseDto getResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();
        responseDto.setUserEmail(shoppingCart.getUser().getEmail());
        responseDto.setTickets(shoppingCart.getTickets()
                .stream()
                .map(this::getResponseDto)
                .collect(Collectors.toList()));
        return responseDto;
    }

    private TicketResponseDto getResponseDto(Ticket ticket) {
        TicketResponseDto responseDto = new TicketResponseDto();
        responseDto.setUserEmail(ticket.getUser().getEmail());
        MovieSession movieSession = ticket.getMovieSession();
        responseDto.setMovieSessionId(movieSession.getId());
        responseDto.setMovieTitle(movieSession.getMovie().getTitle());
        responseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        responseDto.setShowTime(movieSession.getShowTime().toString());
        return responseDto;
    }
}
