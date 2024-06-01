package app;

import domain.Flight;
import domain.Passenger;
import repository.FlightRepository;
import repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class Application implements CommandLineRunner {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightRepository flightRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Passenger passenger1 = new Passenger("passenger1");
        Passenger passenger2 = new Passenger("passenger2");
        Flight flight1 = new Flight("101", "NY", "CID", LocalDate.of(2024, 04, 22));
        Flight flight2 = new Flight("102", "CID", "CCG", LocalDate.of(2024, 03, 12));
        Flight flight3 = new Flight("104", "DAS", "DRA", LocalDate.of(2024, 05, 12));
        passenger1.setFlights(List.of(flight1, flight2));
        passenger2.setFlights(List.of(flight3));
        flightRepository.saveAll(List.of(flight1, flight2, flight3));
        passengerRepository.saveAll(List.of(passenger1, passenger2));

    }
}
