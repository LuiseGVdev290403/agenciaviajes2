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
@Table(name="Reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="total")
    private double total;
    @Column(name="rating")
    private double raiting;
    
    @OneToOne
    @JoinColumn(name="tour_id", nullable = false)
    private Tours tour;
    
    public Reviews() {
    }

    public Reviews(Tours tour, double total, double raiting) {
        this.tour = tour;
        this.total = total;
        this.raiting = raiting;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getRaiting() {
        return raiting;
    }

    public void setRaiting(double raiting) {
        this.raiting = raiting;
    }

    public Tours getTour() {
        return tour;
    }

    public void setTour(Tours tour) {
        this.tour = tour;
    }
}
