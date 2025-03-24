package com.agenciatorus.api.Segurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    /*
    *paso 1: incriptar contraseña con springSecurity-> passwordEncoder @Bean
    */
    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
    /*
    * paso 2: configuramos "dashboard/user" libre sin autenticacion
    * csrf-> se desabilitada  el tokeb csrf para hebitar hackeo en los formulario
    * sessionmangement -> para que el token se genere al enviar
    * solo permite registrarse pero con el rol de user(como cualquier aplicacion )
    * */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authz) -> authz
                .requestMatchers(HttpMethod.GET,"/dashboard/user").permitAll()
                        .requestMatchers(HttpMethod.POST,"/dashboard/user/register").permitAll()
                .anyRequest().authenticated())
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
