package com.agenciatorus.api.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="popularity")
public class Popularity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
  
    @Column(name="is_popular")
    private boolean isPopular;
    @Column(name = "ranked")
    private String rank;
    @OneToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tours tour;
    
    public Popularity() {
    }

    public Popularity(boolean isPopular, String rank) {
        this.isPopular = isPopular;
        this.rank = rank;
    }

    public Popularity(Tours tour, boolean isPopular, String rank) {
        this.tour = tour;
        this.isPopular = isPopular;
        this.rank = rank;
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
    public boolean isPopular() {
        return isPopular;
    }
    public void setPopular(boolean isPopular) {
        this.isPopular = isPopular;
    }
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }

    public Tours getTour() {
        return tour;
    }

    public void setTour(Tours tour) {
        this.tour = tour;
    }


    
}
