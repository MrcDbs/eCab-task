package entities;

public class Ride {
	
	 private final String id;
	    private Driver driver;
	    private Location pickupLocation;
	    private String status;
	    private Rider rider;
	    
	    
	    
		public Ride(String id, Driver driver, Location pickupLocation, String status) {
			super();
			this.id = id;
			this.driver = driver;
			this.pickupLocation = pickupLocation;
			this.status = status;
		}
		public Driver getDriver() {
			return driver;
		}
		public void setDriver(Driver driver) {
			this.driver = driver;
		}
		public Location getPickupLocation() {
			return pickupLocation;
		}
		public void setPickupLocation(Location pickupLocation) {
			this.pickupLocation = pickupLocation;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getId() {
			return id;
		}
		public Rider getRider() {
			return rider;
		}
		public void setRider(Rider rider) {
			this.rider = rider;
		}
	    

		
}
