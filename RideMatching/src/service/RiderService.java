package service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import entities.Driver;
import entities.Location;
import entities.Ride;

public class RiderService {
	
    private final ConcurrentMap<String, Ride> activeRides = new ConcurrentHashMap<>();
    private MatchRideService matchingService;
    
    
    
    public RiderService(MatchRideService matchingService) {
		super();
		this.matchingService = matchingService;
	}

	public Ride requestRide(Location pickupLocation) {
        Driver nearestDriver = matchingService.findNearestAvailableDriver(pickupLocation);
        if (nearestDriver == null) {
        	return null;
        }
        nearestDriver.setAvailable(false);
        Ride ride = new Ride(UUID.randomUUID().toString(), nearestDriver, pickupLocation, "CREATED");
        activeRides.put(ride.getId(), ride);
        return ride;
    }

    public void completeRide(String rideId) {
        Ride ride = activeRides.remove(rideId);
        if (ride != null) {
            ride.getDriver().setAvailable(true);
        }
    }

}
