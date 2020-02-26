package mate.academy.cinema.controller;

import mate.academy.cinema.model.Role;
import mate.academy.cinema.model.User;
import mate.academy.cinema.service.RoleService;
import mate.academy.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InitializationController {
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public InitializationController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Role userRole = new Role();
        userRole.setRoleName("USER");
        roleService.add(userRole);

        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        roleService.add(adminRole);

        User user = new User();
        user.setEmail("user@ukr.net");
        user.setPassword("1234");
        user.addRole(userRole);
        userService.add(user);

        User admin = new User();
        admin.setEmail("admin@ukr.net");
        admin.setPassword("4321");
        admin.addRole(adminRole);
        userService.add(admin);
    }
}
