package com.agenciatorus.api.Services;

import com.agenciatorus.api.Entities.Role;
import com.agenciatorus.api.Entities.Users;
import com.agenciatorus.api.Repository.RoleRepository;
import com.agenciatorus.api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");//de la entidad de bd
        List<Role> roles = new ArrayList<>();

        optionalRoleUser.ifPresent(role -> roles.add(role)); //agregando roles en la lista

        //depende del json resivido
        if(user.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(role -> roles.add(role));
        }

        user.setRoleList(roles);

        // devuelve la contraseña incriptada -> guarda encriptida la contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));


        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {

        return userRepository.existsByUsername(username);
    }
}
