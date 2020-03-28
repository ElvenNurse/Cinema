package mate.academy.cinema.dao;

import java.util.List;
import mate.academy.cinema.model.Order;
import mate.academy.cinema.model.User;

public interface OrderDao {
    Order add(Order order);

    Order getById(Long id);

    List<Order> getUserOrders(User user);

    List<Order> getAll();
}
