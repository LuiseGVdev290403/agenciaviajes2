package com.agenciatorus.api.Segurity;

import com.agenciatorus.api.Segurity.filter.JwtAuthenticacionFilter;
import com.agenciatorus.api.Segurity.filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


@Configuration
public class SpringSecurityConfig {
    /*
    * paso 3 */
    @Autowired
    private AuthenticationConfiguration authenticationManagerConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationManagerConfiguration.getAuthenticationManager();
    }

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
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/tourid/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/dashboard/user/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dashboard").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST,"/dashboard/user").hasRole("ADMIN")//solo usuarios con admin puede agregar usuarios solo ADMIN el prefijo no
                        .requestMatchers(HttpMethod.POST,"/dashboard/agregar").hasRole("ADMIN") // solo admin puede agregar tour
                        .requestMatchers(HttpMethod.POST,"/dashboard/agregarreserva").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilter(new JwtAuthenticacionFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
    /**
     * Permite al cliente acceder a los endpoinst para autenticarse*/
    @Bean
    CorsConfigurationSource corsConfigurationSource (){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean(){
        FilterRegistrationBean<CorsFilter> corsBean =
                new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsBean;
    }
}

