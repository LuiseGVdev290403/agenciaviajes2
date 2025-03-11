package com.agenciatorus.api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agenciatorus.api.Entities.Reviews;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long>{
    
    @Query("SELECT r.total, r.raiting FROM Reviews r WHERE r.tour.id = :id")
    List<Object[]> findReviewsByTourId(@Param("id") Long id);
}
