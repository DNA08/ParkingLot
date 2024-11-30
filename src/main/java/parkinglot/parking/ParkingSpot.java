package parkinglot.parking;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import parkinglot.vehicle.Vehicle;

@Getter
@Setter
@ToString
public class ParkingSpot {
	
	private String floorId;
	private String id;
	private boolean isFree;
	private ParkingSpotType parkingSpotType;
	private Vehicle assignedVehicleId;
	
	
	public ParkingSpot(String id, ParkingSpotType parkingSpotType) {
		this.id = id;
		this.parkingSpotType = parkingSpotType;
		this.isFree = true;
	}

	public void blockParkingSpot(Vehicle vehicle, ParkingSpot parkingSpot) {
		parkingSpot.setAssignedVehicleId(vehicle);
		parkingSpot.setFree(false);
	}

	public void vacateSpot(ParkingSpot assignParkingSpot) {
		assignParkingSpot.setAssignedVehicleId(null);
		assignParkingSpot.setFree(true);
		
	}
	
}
