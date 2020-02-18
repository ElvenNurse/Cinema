package mate.academy.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;

import mate.academy.cinema.dto.request.OrderRequestDto;
import mate.academy.cinema.dto.response.OrderResponseDto;
import mate.academy.cinema.dto.response.TicketResponseMapper;
import mate.academy.cinema.model.Order;
import mate.academy.cinema.service.OrderService;
import mate.academy.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private TicketResponseMapper ticketResponseMapper;

    @Autowired
    public OrderController(OrderService orderService,
                           UserService userService, TicketResponseMapper ticketResponseMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.ticketResponseMapper = ticketResponseMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestBody OrderRequestDto requestDto) {
        orderService.completeOrder(userService.getById(requestDto.getUserId()));
    }

    @GetMapping
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
                .map(ticketResponseMapper::getResponseDto)
                .collect(Collectors.toList()));
        return responseDto;
    }
}
