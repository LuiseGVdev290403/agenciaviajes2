package com.agenciatorus.api.Services.DTO;

public class DetailsDto {
        private Double priceAmount;
    private String priceCurrency;
    private int durationDays;
    private int maxPeople;
    private String category;

    public DetailsDto() {}

    public DetailsDto(Double priceAmount, String priceCurrency, int durationDays, int maxPeople, String category) {
        this.priceAmount = priceAmount;
        this.priceCurrency = priceCurrency;
        this.durationDays = durationDays;
        this.maxPeople = maxPeople;
        this.category = category;
    }

    // Getters y Setters
    public Double getPriceAmount() { return priceAmount; }
    public void setPriceAmount(Double priceAmount) { this.priceAmount = priceAmount; }

    public String getPriceCurrency() { return priceCurrency; }
    public void setPriceCurrency(String priceCurrency) { this.priceCurrency = priceCurrency; }

    public int getDurationDays() { return durationDays; }
    public void setDurationDays(int durationDays) { this.durationDays = durationDays; }

    public int getMaxPeople() { return maxPeople; }
    public void setMaxPeople(int maxPeople) { this.maxPeople = maxPeople; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
