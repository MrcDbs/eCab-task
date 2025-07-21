package service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import entities.Driver;
import entities.Location;

public class MatchRideService {

    private DriverService driverService;
    
    
    public MatchRideService(DriverService driverService) {
		super();
		this.driverService = driverService;
	}

	public Driver findNearestAvailableDriver(Location location) {
        return driverService.getAllAvailableDrivers().stream()
            .min(Comparator.comparingDouble(d -> MatchRideService.euclideanCalculator(d.getLocation(), location)))
            .orElse(null);
    }

    public List<Driver> getNearestDrivers(Location location, int x) {
        return driverService.getAllAvailableDrivers().stream()
            .sorted(Comparator.comparingDouble(d -> MatchRideService.euclideanCalculator(d.getLocation(), location)))
            .limit(x)
            .collect(Collectors.toList());
    }
    
    
    private static double euclideanCalculator(Location a, Location b) {
         return Math.sqrt(
             Math.pow(a.getX() - b.getX(), 2) +
             Math.pow(a.getY() - b.getY(), 2)
         );
    }
    
}
