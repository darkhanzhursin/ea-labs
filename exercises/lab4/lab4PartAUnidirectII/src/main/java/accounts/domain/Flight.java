package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String flightNumber;
    private String flightFrom;
    private String flightTo;
    private LocalDate flighDate;

    public Flight() {}

    public Flight(String flightNumber, String from, String flightTo, LocalDate date) {
        this.flightNumber = flightNumber;
        this.flightFrom = from;
        this.flightTo = flightTo;
        this.flighDate = date;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightFrom() {
        return flightFrom;
    }

    public void setFlightFrom(String from) {
        this.flightFrom = from;
    }

    public String getFlightTo() {
        return flightTo;
    }

    public void setFlightTo(String to) {
        this.flightTo = to;
    }

    public LocalDate getFlighDate() {
        return flighDate;
    }

    public void setFlighDate(LocalDate date) {
        this.flighDate = date;
    }
}
