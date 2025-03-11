package com.agenciatorus.api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agenciatorus.api.Entities.Highlights;

public interface HighlightsRepository extends JpaRepository<Highlights, Long> {

    @Query("SELECT h.highlight FROM Highlights h WHERE h.tour.id = :id")
    List<String> findDetailsByHighlightId(@Param("id") Long id);
    

}
