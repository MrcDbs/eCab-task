package entities;

public class Rider {
	private final String id;
	private Location lastPickUp;
	public Rider(String id, Location lastPickUp) {
		super();
		this.id = id;
		this.lastPickUp = lastPickUp;
	}
	public Location getLastPickUp() {
		return lastPickUp;
	}
	public void setLastPickUp(Location lastPickUp) {
		this.lastPickUp = lastPickUp;
	}
	public String getId() {
		return id;
	}
	
	

}
