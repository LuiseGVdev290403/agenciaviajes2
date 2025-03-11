package com.agenciatorus.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agenciatorus.api.Entities.Reservas;

@Repository
public interface ReservaRepository extends JpaRepository<Reservas, String> {
    
}
