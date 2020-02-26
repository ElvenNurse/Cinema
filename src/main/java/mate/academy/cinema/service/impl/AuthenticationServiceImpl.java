package mate.academy.cinema.service.impl;

import mate.academy.cinema.dao.RoleDao;
import mate.academy.cinema.exception.AuthenticationException;
import mate.academy.cinema.model.User;
import mate.academy.cinema.service.AuthenticationService;
import mate.academy.cinema.service.ShoppingCartService;
import mate.academy.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private ShoppingCartService shoppingCartService;
    private UserService userService;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceImpl(ShoppingCartService shoppingCartService,
                                     UserService userService, RoleDao roleDao,
                                     PasswordEncoder passwordEncoder) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user != null && user.getPassword().equals(passwordEncoder.encode(password))) {
            return user;
        }
        throw new AuthenticationException("Incorrect username or password");
    }

    @Override
    public User register(String email, String password) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.addRole(roleDao.getRoleByName("USER"));
        User userFromDb = userService.add(newUser);
        shoppingCartService.registerNewShoppingCart(userFromDb);
        return userFromDb;
    }
}
