package com.agenciatorus.api.Services;

import com.agenciatorus.api.Entities.Users;
import com.agenciatorus.api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Para buscar username en la bd pal login
@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> usersOptional = repository.findByUsername(username);

        if(usersOptional.isEmpty()){
            throw  new UsernameNotFoundException(String.format("username %s no existe en el sistema", username));
        }

        Users users = usersOptional.orElseThrow();

        List<GrantedAuthority> authorities = users.getRoleList().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());


        return new org.springframework.security.core.userdetails.User(users.getUsername(),
                users.getPassword(),
                users.isEnable(),
                true,
                true,
                true,
                authorities);
    }
}
