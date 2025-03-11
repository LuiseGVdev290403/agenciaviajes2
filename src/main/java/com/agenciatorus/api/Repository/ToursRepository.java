package com.agenciatorus.api.Repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agenciatorus.api.Entities.Tours;

@Repository
public interface ToursRepository extends JpaRepository<Tours, Long>{
    @Query("SELECT t FROM Tours t WHERE t.nombre = :title")
    Optional<Tours> findByTitle(@Param("title") String title);
}
