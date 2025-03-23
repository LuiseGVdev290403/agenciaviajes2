package com.agenciatorus.api.Services;

import com.agenciatorus.api.Entities.Users;

import java.util.List;

public interface UserServices {
    List<Users> findAll();
    Users save(Users user);
}
