package my.code;

import testClasses.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();


        flights.forEach(System.out::println);

        System.out.println("------------------------------------------");

        IFilter filter = new FlightFilter();
        List<Flight> filteredFlights = filter.filterFlight(flights);

        System.out.println("Filtered Flights:"); // Выводим отфильтрованные перелеты
        filteredFlights.forEach(System.out::println);
    }
}
