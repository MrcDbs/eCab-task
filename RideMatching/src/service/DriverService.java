package service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import entities.Driver;
import entities.Location;

public class DriverService {

    // THREAD-SAFE MAP IMPLEMENTATION
    private final Map<String, Driver> drivers = new ConcurrentHashMap<>();

    public void registerOrUpdateDriver(String driverId, Location location) {
        drivers.compute(driverId, (id, existing) -> {
            if (existing == null) {
                return new Driver(driverId, location);
            } else {
                existing.setLocation(location);
                return existing;
            }
        });
    }

    public List<Driver> getAllAvailableDrivers() {
        return drivers.values().stream()
                .filter(Driver::isAvailable)
                .collect(Collectors.toList());
    }


    public Driver getDriver(String driverId) {
        return drivers.get(driverId);
    }

}

