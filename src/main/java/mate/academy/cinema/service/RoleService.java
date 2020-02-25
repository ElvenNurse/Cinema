package mate.academy.cinema.service;

import mate.academy.cinema.model.Role;

public interface RoleService {
    Role add(Role role);

    Role getRoleByName(String roleName);
}
