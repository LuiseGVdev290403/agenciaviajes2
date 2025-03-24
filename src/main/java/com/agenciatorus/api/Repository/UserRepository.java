package com.agenciatorus.api.Repository;

import com.agenciatorus.api.Entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends CrudRepository<Users, Long> {
}
