package com.agenciatorus.api.Services;

import com.agenciatorus.api.Entities.Users;
import com.agenciatorus.api.Repository.RoleRepository;
import com.agenciatorus.api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Users> findAll() {
        return (List<Users>) userRepository.findAll();
    }

    @Override
    @Transactional
    public Users save(Users user) {

        return userRepository.save(user);
    }
}
