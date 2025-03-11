package com.agenciatorus.api.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="highlights")
public class Highlights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="highlight")
    private String highlight;

    @ManyToOne
    @JoinColumn(name="tour_id", nullable = false)
    private Tours tour;

    public Highlights() {
    }
    public Highlights(Tours tour, String highlight) {
        this.tour = tour;
        this.highlight = highlight;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Tours getTourId() {
        return tour;
    }
    public void setTourId(Tours tour) {
        this.tour = tour;
    }
    public String getHighlight() {
        return highlight;
    }
    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }
    public Tours getTour() {
        return tour;
    }
    public void setTour(Tours tour) {
        this.tour = tour;
    }

    
}
