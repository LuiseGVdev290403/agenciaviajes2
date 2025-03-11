package com.agenciatorus.api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agenciatorus.api.Entities.Popularity;

@Repository
public interface PopularityRepository extends JpaRepository<Popularity, Long>{
    @Query("SELECT p.isPopular, p.rank FROM Popularity p WHERE p.tour.id = :id")
    List<Object[]> findPopularityByTourId(@Param("id") Long id);    
} 
