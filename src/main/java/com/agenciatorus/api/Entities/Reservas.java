package com.agenciatorus.api.Entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reservas")
public class Reservas {
    @Id
    @Column(name = "reserva_id")
    private String reservaId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "number_passengers", nullable = false)
    private int numberPassengers;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "pagar", nullable = false)
    private double pagado;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tours tour;

    // Constructores
    public Reservas() {}

    public Reservas(String reservaId, String name, String lastname, String phone, String email, int numberPassengers,
                    LocalDate date, double pagado, Tours tour) {
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

    // Getters y Setters
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

    public Tours getTour() {
        return tour;
    }

    public void setTour(Tours tour) {
        this.tour = tour;
    }
}
