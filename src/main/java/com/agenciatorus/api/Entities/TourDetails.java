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
@Table(name="tourdetails")
public class TourDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
   
    @Column(name="max_people")
    private int maxPeople;
    @Column(name="duration_days")
    private double durationDays;
    @Column(name="price_amount")
    private double priceAmount;
    @Column(name="price_currency")
    private String priceCurrency;
    @Column(name="category")
    private String category;
    
    @OneToOne
    @JoinColumn(name="tour_id", referencedColumnName = "id", nullable = false)
    private Tours tour;


    public TourDetails() {
    }

    public TourDetails(int maxPeople, double durationDays, double priceAmount, String priceCurrency, String category) {

        this.maxPeople = maxPeople;
        this.durationDays = durationDays;
        this.priceAmount = priceAmount;
        this.priceCurrency = priceCurrency;
        this.category = category;
    }

    public TourDetails(Tours tour, int max_people, double durationDays, double priceAmount, String priceCurrency,
                       String category) {
        this.tour = tour;
        this.maxPeople = max_people;
        this.durationDays = durationDays;
        this.priceAmount = priceAmount;
        this.priceCurrency = priceCurrency;
        this.category = category;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Tours getTour() {
        return tour;
    }

    public void setTour(Tours tour) {
        this.tour = tour;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public int getMax_people() {
        return maxPeople;
    }
    public void setMax_people(int max_people) {
        this.maxPeople = max_people;
    }
    public double getDurationDays() {
        return durationDays;
    }
    public void setDurationDays(double durationDays) {
        this.durationDays = durationDays;
    }
    public double getPriceAmount() {
        return priceAmount;
    }
    public void setPriceAmount(double priceAmount) {
        this.priceAmount = priceAmount;
    }
    public String getPriceCurrency() {
        return priceCurrency;
    }
    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    
    
}
