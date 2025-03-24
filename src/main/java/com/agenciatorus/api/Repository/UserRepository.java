package com.agenciatorus.api.Repository;

import com.agenciatorus.api.Entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<Users, Long> {

    boolean existsByUsername(String username);
    Optional<Users> findByUsername(String username);
}
