package com.agenciatorus.api.Services.DTO;

import java.util.HashMap;
import java.util.Map;

public class TourDto {
    private String title;
    private String country;
    private String location;
    private String image;
    private String description;
    private String longDescription;
    
    private DetailsDto details;  // Cambiado para que sea un objeto en vez de un método

    private boolean isPopular;
    private String rank;

    private int totalReviews;
    private double rating;

    private String[] highlights;

    public TourDto() {}

    public TourDto(String title, String country, String location, String image, String description,
                   String longDescription, DetailsDto details, boolean isPopular, String rank,
                   int totalReviews, double rating, String[] highlights) {
        this.title = title;
        this.country = country;
        this.location = location;
        this.image = image;
        this.description = description;
        this.longDescription = longDescription;
        this.details = details;
        this.isPopular = isPopular;
        this.rank = rank;
        this.totalReviews = totalReviews;
        this.rating = rating;
        this.highlights = highlights;
    }

    // Getters y Setters

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLongDescription() { return longDescription; }
    public void setLongDescription(String longDescription) { this.longDescription = longDescription; }

    public DetailsDto getDetails() { return details; }
    public void setDetails(DetailsDto details) { this.details = details; }

    public boolean isPopular() { return isPopular; }
    public void setPopular(boolean popular) { isPopular = popular; }

    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }

    public int getTotalReviews() { return totalReviews; }
    public void setTotalReviews(int totalReviews) { this.totalReviews = totalReviews; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String[] getHighlights() { return highlights; }
    public void setHighlights(String[] highlights) { this.highlights = highlights; }
}
