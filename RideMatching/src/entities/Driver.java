package entities;

public class Driver {

	 private final String id;
	 private volatile boolean available;
	 private volatile Location location;

    public Driver(String id, Location location) {
        this.id = id;
        this.location = location;
        this.available = true;
    }

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getId() {
		return id;
	}
    
    
}
