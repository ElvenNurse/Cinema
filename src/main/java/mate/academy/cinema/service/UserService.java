package mate.academy.cinema.service;

import mate.academy.cinema.model.User;

public interface UserService {
    User add(User user);

    User findByEmail(String email);
}
