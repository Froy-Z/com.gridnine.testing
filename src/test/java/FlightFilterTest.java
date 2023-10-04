import my.code.FlightFilter;
import my.code.IFilter;
import org.junit.Test;
import testClasses.Flight;
import testClasses.FlightBuilder;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FlightFilterTest {

    @Test
    public void testFlightFilter() {
        List<Flight> flights = FlightBuilder.createFlights();

        IFilter filter = new FlightFilter();

        List<Flight> filteredFlights = filter.filterFlight(flights);

        assertTrue(filteredFlights.stream().allMatch(flight ->
                flight.getSegments().stream()
                        .noneMatch(segment ->
                                ChronoUnit.HOURS.between(segment.getArrivalDate(), segment.getDepartureDate()) > 2)));

        assertFalse(filteredFlights.stream().allMatch(flight ->
                flight.getSegments().size() >= 2));

    }
}

