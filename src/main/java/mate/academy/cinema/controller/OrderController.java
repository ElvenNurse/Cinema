package mate.academy.cinema.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.cinema.dto.response.OrderResponseDto;
import mate.academy.cinema.dto.response.TicketResponseDto;
import mate.academy.cinema.model.MovieSession;
import mate.academy.cinema.model.Order;
import mate.academy.cinema.model.Ticket;
import mate.academy.cinema.service.OrderService;
import mate.academy.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private UserService userService;

    @Autowired
    public OrderController(OrderService orderService,
                           UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/complete")
    public void completeOrder(Principal principal) {
        orderService.completeOrder(userService.findByEmail(principal.getName()));
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(Principal principal) {
        return orderService.getOrderHistory(userService.findByEmail(principal.getName()))
                .stream()
                .map(this::getResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<OrderResponseDto> getAll() {
        return orderService.getAll()
                .stream()
                .map(this::getResponseDto)
                .collect(Collectors.toList());
    }

    private OrderResponseDto getResponseDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setOrderDate(order.getOrderDate().toString());
        responseDto.setTickets(order.getTickets()
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
