package com.agenciatorus.api.Services.DTO;

import java.time.LocalDate;

public class ReservasDto {
    private String reservaId;
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private int numberPassengers;
    private LocalDate date;
    private double pagado;
    private String tour;


     
    public ReservasDto() {
    }
    public ReservasDto(String reservaId, String name, String lastname, String phone, String email,
        int numberPassengers, LocalDate date, double pagado, String tour) {
        this.reservaId = reservaId;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.numberPassengers = numberPassengers;
        this.date = date;
        this.pagado = pagado;
        this.tour = tour;
    }
    public String getReservaId() {
        return reservaId;
    }
    public void setReservaId(String reservaId) {
        this.reservaId = reservaId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getNumberPassengers() {
        return numberPassengers;
    }
    public void setNumberPassengers(int numberPassengers) {
        this.numberPassengers = numberPassengers;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public double isPagado() {
        return pagado;
    }
    public void setPagado(double pagado) {
        this.pagado = pagado;
    }
    public String getTour() {
        return tour;
    }
    public void setTour(String tour) {
        this.tour = tour;
    }

     
}
