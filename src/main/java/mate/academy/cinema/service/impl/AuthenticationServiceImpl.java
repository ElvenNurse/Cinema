package mate.academy.cinema.service.impl;

import mate.academy.cinema.exception.AuthenticationException;
import mate.academy.cinema.lib.Inject;
import mate.academy.cinema.lib.Service;
import mate.academy.cinema.model.User;
import mate.academy.cinema.service.AuthenticationService;
import mate.academy.cinema.service.ShoppingCartService;
import mate.academy.cinema.service.UserService;
import mate.academy.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private static ShoppingCartService shoppingCartService;
    @Inject
    private static UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user != null) {
            String hashPassword = HashUtil.hashPassword(password, user.getSalt());
            if (hashPassword.equals(user.getPassword())) {
                return user;
            }
        }
        throw new AuthenticationException("Incorrect username or password");
    }

    @Override
    public User register(String email, String password) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        User userFromDb = userService.add(newUser);
        shoppingCartService.registerNewShoppingCart(userFromDb);
        return userFromDb;
    }
}
