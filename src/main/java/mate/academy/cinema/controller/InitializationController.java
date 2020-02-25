package mate.academy.cinema.controller;

import mate.academy.cinema.dao.RoleDao;
import mate.academy.cinema.model.Role;
import mate.academy.cinema.model.User;
import mate.academy.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitializationController {
    private RoleDao roleDao;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public InitializationController(RoleDao roleDao, UserService userService,
                                    PasswordEncoder passwordEncoder) {
        this.roleDao = roleDao;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Role userRole = new Role();
        userRole.setRoleName("USER");
        roleDao.add(userRole);

        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        roleDao.add(adminRole);

        User user = new User();
        user.setEmail("user@ukr.net");
        user.setPassword(passwordEncoder.encode("1234"));
        user.addRole(userRole);
        userService.add(user);

        User admin = new User();
        admin.setEmail("admin@ukr.net");
        admin.setPassword(passwordEncoder.encode("4321"));
        admin.addRole(adminRole);
        userService.add(admin);
    }
}
