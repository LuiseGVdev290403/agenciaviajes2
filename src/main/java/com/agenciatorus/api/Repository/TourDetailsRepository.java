package com.agenciatorus.api.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agenciatorus.api.Entities.TourDetails;


@Repository
public interface TourDetailsRepository  extends JpaRepository<TourDetails, Long>{

    @Query("SELECT td FROM TourDetails td WHERE td.tour.id = :id")
    Optional<TourDetails> findDetailsByTourId(@Param("id") Long id);




}
