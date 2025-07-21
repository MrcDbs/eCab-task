package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Driver;
import entities.Location;
import entities.Ride;
import service.DriverService;
import service.MatchRideService;
import service.RiderService;

public class RideServiceTest {

    private DriverService driverService;
    private RiderService riderService;
    private MatchRideService matchingService;

    @BeforeEach
    public void init() {
        driverService = new DriverService();
        matchingService = new MatchRideService(driverService);
        riderService = new RiderService(matchingService);
    }

    @Test
    public void testDriverRegistration() {
        driverService.registerOrUpdateDriver("driver1", new Location(10, 10));
        List<Driver> availableDrivers = driverService.getAllAvailableDrivers();

        assertEquals(1, availableDrivers.size());
        assertEquals("driver1", availableDrivers.get(0).getId());
    }

    @Test
    public void testRideRequestAllocatesNearestDriver() {
        driverService.registerOrUpdateDriver("driver1", new Location(10, 10));
        driverService.registerOrUpdateDriver("driver2", new Location(50, 50));

        Ride ride = riderService.requestRide(new Location(11, 11));

        assertNotNull(ride);
        assertEquals("driver1", ride.getDriver().getId());
        assertFalse(driverService.getDriver("driver1").isAvailable());
    }

    @Test
    public void testDriverBecomesUnavailableAfterRideAssigned() {
        driverService.registerOrUpdateDriver("driver1", new Location(10, 10));

        Ride ride = riderService.requestRide(new Location(10, 10));
        assertFalse(driverService.getDriver("driver1").isAvailable());

        List<Driver> availableDrivers = driverService.getAllAvailableDrivers();
        assertEquals(0, availableDrivers.size());
    }

    @Test
    public void testCompleteRideMakesDriverAvailable() {
        driverService.registerOrUpdateDriver("driver1", new Location(10, 10));
        Ride ride = riderService.requestRide(new Location(10, 10));

        riderService.completeRide(ride.getId());

        assertTrue(driverService.getDriver("driver1").isAvailable());
    }

    @Test
    public void testNearestDriversQueryReturnsSortedList() {
        driverService.registerOrUpdateDriver("d1", new Location(0, 0));
        driverService.registerOrUpdateDriver("d2", new Location(5, 5));
        driverService.registerOrUpdateDriver("d3", new Location(10, 10));

        List<Driver> nearest = matchingService.getNearestDrivers(new Location(0, 0), 2);

        assertEquals(2, nearest.size());
        assertEquals("d1", nearest.get(0).getId()); // closest
        assertEquals("d2", nearest.get(1).getId());
    }
}
