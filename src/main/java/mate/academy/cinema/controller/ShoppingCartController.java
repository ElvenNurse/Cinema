package mate.academy.cinema.controller;

import java.util.stream.Collectors;

import mate.academy.cinema.dto.request.ShoppingCartRequestDto;
import mate.academy.cinema.dto.response.ShoppingCartResponseDto;
import mate.academy.cinema.dto.response.TicketResponseMapper;
import mate.academy.cinema.model.ShoppingCart;
import mate.academy.cinema.service.MovieSessionService;
import mate.academy.cinema.service.ShoppingCartService;
import mate.academy.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private MovieSessionService movieSessionService;
    private UserService userService;
    private TicketResponseMapper ticketResponseMapper;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService,
                                  UserService userService,
                                  TicketResponseMapper ticketResponseMapper) {
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
        this.ticketResponseMapper = ticketResponseMapper;
    }

    @PostMapping("/addmoviesession")
    public void addMovieSession(@RequestBody ShoppingCartRequestDto requestDto,
                                 @RequestParam Long userId) {
        shoppingCartService.addSession(
                movieSessionService.getById(requestDto.getMovieSessionId()),
                userService.getById(userId));
    }

    @GetMapping("/byuser")
    public ShoppingCartResponseDto getByUser(@RequestParam Long userId) {
        return getResponseDto(shoppingCartService.getByUser(userService.getById(userId)));
    }

    private ShoppingCartResponseDto getResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();
        responseDto.setUserEmail(shoppingCart.getUser().getEmail());
        responseDto.setTickets(shoppingCart.getTickets()
                .stream()
                .map(ticketResponseMapper::getResponseDto)
                .collect(Collectors.toList()));
        return responseDto;
    }
}
