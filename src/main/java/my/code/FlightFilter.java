package my.code;

import testClasses.Flight;
import testClasses.Segment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilter implements IFilter {

    @Override
    public List<Flight> filterFlight(List<Flight> flights) {

        LocalDateTime localDateTime = LocalDateTime.now();

        /*
        1. Вылет до текущего времени
        2. Сегменты с датой прилёта раньше даты вылета
        3. Общее время, проведённое на земле превышает два часа
         */

        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .noneMatch(segment -> segment.getDepartureDate().isBefore(localDateTime)))

                .filter(flight -> flight.getSegments()
                        .stream()
                        .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))

                .filter(flight -> calculateGroundTime(flight) <= 2)
                .collect(Collectors.toList());
    }

    private long calculateGroundTime(Flight flight) {

        List<Segment> segments = flight.getSegments();

        long groundTime = 0;

        for (int i = 0; i < segments.size() - 1; i++) {
            LocalDateTime arrival = segments.get(i).getArrivalDate();
            LocalDateTime departure = segments.get(i + 1).getDepartureDate();
            groundTime += ChronoUnit.HOURS.between(arrival, departure);
        }
        return groundTime;
    }
}
