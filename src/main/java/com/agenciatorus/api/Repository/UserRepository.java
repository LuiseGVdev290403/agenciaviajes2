package com.agenciatorus.api.Repository;

import com.agenciatorus.api.Entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
}
