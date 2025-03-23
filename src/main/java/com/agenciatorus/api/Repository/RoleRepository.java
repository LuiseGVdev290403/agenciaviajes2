package com.agenciatorus.api.Repository;

import com.agenciatorus.api.Entities.Role;
import com.agenciatorus.api.Entities.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.logging.LogManager;

public interface RoleRepository extends CrudRepository<Users, Long> {
    Optional<Role> findByName (String name);
}
