package mate.academy.cinema.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import mate.academy.cinema.dao.OrderDao;
import mate.academy.cinema.model.Order;
import mate.academy.cinema.model.ShoppingCart;
import mate.academy.cinema.model.User;
import mate.academy.cinema.service.OrderService;
import mate.academy.cinema.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, ShoppingCartService shoppingCartService) {
        this.orderDao = orderDao;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Order completeOrder(User user) {
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        Order order = new Order();
        order.setUser(shoppingCart.getUser());
        order.setOrderDate(LocalDateTime.now());
        order.setTickets(shoppingCart.getTickets());
        shoppingCartService.clearShoppingCart(shoppingCart);
        return orderDao.add(order);
    }

    @Override
    public Order getById(Long id) {
        return orderDao.getById(id);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getUserOrders(user);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }
}
