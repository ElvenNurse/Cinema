package mate.academy.cinema.dao;

import mate.academy.cinema.model.User;

public interface UserDao {
    User add(User user);

    User getById(Long id);

    User findByEmail(String email);
}
