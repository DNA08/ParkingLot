package parkinglot.common;

import java.util.Collection;
import java.util.Optional;

import parkinglot.exception.ParkingFloorAlreadyPresentException;
import parkinglot.exception.ParkingFloorDoesntExist;
import parkinglot.parking.EntryGate;
import parkinglot.parking.ExitGate;
import parkinglot.parking.ParkingFloor;
import parkinglot.parking.ParkingSpot;
import parkinglot.parking.ParkingSpotType;
import parkinglot.parking.Parkinglot;

public class Admin extends Account{
	
	Parkinglot parkinglot = Parkinglot.INSTANCE;
	
	public void addFloors(String floorId) throws ParkingFloorAlreadyPresentException {
		Optional<ParkingFloor> parkingFloor = parkinglot.getParkingFloors().stream().
				filter(floor -> floor.getId().equals(floorId)).findFirst();
		if(parkingFloor.isEmpty()) {
			parkinglot.getParkingFloors().add(new ParkingFloor(floorId));
		}else {
			throw new ParkingFloorAlreadyPresentException("Parking Floor already exists");
		}
	}
	
	public void addParkingSpot(String floorId, String parkingSpotId,ParkingSpotType parkingSpotType) throws ParkingFloorDoesntExist {
		Optional<ParkingFloor> parkingFloorOpt =  parkinglot.getParkingFloors().stream().
				filter( parkingFloor -> parkingFloor.getId().equalsIgnoreCase(floorId)).findFirst();
		if(parkingFloorOpt.isEmpty()) {
			throw new ParkingFloorDoesntExist("Parking Spot Can't be added on the given Parking floor id "+ floorId);
		}
		Optional<ParkingSpot> parkingSpot = parkingFloorOpt.get().getParkingSpots().get(parkingSpotType).stream()
				.filter(ps -> ps.getId().equalsIgnoreCase(parkingSpotId)).findFirst();
		if(parkingSpot.isPresent()) {
			throw new RuntimeException("ParkingSpot Already exist");
		}
		parkingFloorOpt.get().getParkingSpots().get(parkingSpotType).add(new ParkingSpot(parkingSpotId, parkingSpotType));
	}
	
	public void addEntryGate(String entryGateID) {
		parkinglot.getEntryGates().add(new EntryGate(entryGateID));
	}
	
	public void addExitGate(String exitGateId) {
		parkinglot.getExitGates().add(new ExitGate(exitGateId));
		
	}
	

}
