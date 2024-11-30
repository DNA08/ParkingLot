package parkinglot.parking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.Getter;
import lombok.Setter;
import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleType;

public class ParkingFloor {
	@Getter
	@Setter
	private String id;

	@Getter
	private Map<ParkingSpotType, List<ParkingSpot>> parkingSpots = new HashMap<>();

	private Map<String, ParkingSpot> usedParkingSpot = new HashMap<>();

	public ParkingFloor(String id) {
		this.id = id;
		parkingSpots.put(ParkingSpotType.FOUR_WHEELER, new CopyOnWriteArrayList<ParkingSpot>());
		parkingSpots.put(ParkingSpotType.HEAVY_VEHICLE, new CopyOnWriteArrayList<ParkingSpot>());
		parkingSpots.put(ParkingSpotType.TWO_WHEELER, new CopyOnWriteArrayList<ParkingSpot>());
		parkingSpots.put(ParkingSpotType.ELECTRIC_VEHICLE, new CopyOnWriteArrayList<ParkingSpot>());
	}

	@Override
	public String toString() {
		return "ParkingFloor [id=" + id + ", parkingSpots=" + parkingSpots + ", usedParkingSpot=" + usedParkingSpot
				+ "]";
	}

	public boolean isFull(ParkingFloor parkingFloor) {
		int totalParkingSpot = findTotalParkingSpotOnFloor(parkingFloor);
		return totalParkingSpot == this.usedParkingSpot.size();
	}

	private int findTotalParkingSpotOnFloor(ParkingFloor parkingFloor) {
		int sum = 0;
		for (ParkingSpotType ps : parkingFloor.getParkingSpots().keySet()) {
			sum += parkingFloor.getParkingSpots().get(ps).size();
		}
		return sum;
	}

	public ParkingSpot getParkingSpot(ParkingFloor parkingFloor, Vehicle vehicle) {
		Optional<ParkingSpot> ps = parkingFloor.getParkingSpots().get(this.findSpotTypeForVehicle(vehicle)).stream()
				.filter(parkingSpot -> parkingSpot.isFree()).findFirst();
		if (ps.isPresent()) {
			ParkingSpot parkingSpot = ps.get();
			usedParkingSpot.put(parkingFloor.getId() + "_" + parkingSpot.getId(), parkingSpot);
			parkingSpot.blockParkingSpot(vehicle, parkingSpot);
			return parkingSpot;
		}
		return null;
	}

	public void vacateParkingSpotFromFloor(ParkingFloor parkingFloor, ParkingSpot assignParkingSpot) {
		String usedParkingSpotId = parkingFloor.getId() + "_" + assignParkingSpot.getId();
		usedParkingSpot.remove(usedParkingSpotId);
		assignParkingSpot.vacateSpot(assignParkingSpot);
	}

	public ParkingSpotType findSpotTypeForVehicle(Vehicle vehicle) {
		VehicleType vehicleType = vehicle.getVehicleType();
		ParkingSpotType psType;
		if (VehicleType.CAR.equals(vehicleType)) {
			psType = ParkingSpotType.FOUR_WHEELER;
		} else if (VehicleType.BIKE.equals(vehicleType)) {
			psType = ParkingSpotType.TWO_WHEELER;
		} else if (VehicleType.HEAVY_VEHICLE.equals(vehicleType)) {
			psType = ParkingSpotType.HEAVY_VEHICLE;
		} else {
			psType = ParkingSpotType.ELECTRIC_VEHICLE;
		}
		return psType;
	}

}
