package parkinglot.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import parkinglot.common.Address;
import parkinglot.vehicle.Vehicle;

@Getter
@Setter
public class Parkinglot {
	private String id;
	private Address address;
	private List<ParkingFloor> parkingFloors;
	private List<EntryGate> entryGates;
	private List<ExitGate> exitGates;
	
	public static Parkinglot INSTANCE = getParkinglot();
	
	private static Parkinglot getParkinglot() {
		return new Parkinglot();
	}
	
	private Parkinglot() {
		this.id = UUID.randomUUID().toString();
		this.parkingFloors = new ArrayList<>();
		this.entryGates = new ArrayList<>();
		this.exitGates = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Parkinglot [id=" + id + ", address=" + address + ", parkingFloors=" + parkingFloors + ", entryGates="
				+ entryGates + ", exitGates=" + exitGates + "]";
	}
	
	public ParkingSpot findParkingSpot(Vehicle vehicle) {
		if(INSTANCE.isFull()) {
			throw new RuntimeException("Parking is full for "+vehicle.getVehicleType());
		}
		for(ParkingFloor parkingFloor : INSTANCE.getParkingFloors()) {
			ParkingSpot parkingSpot = parkingFloor.getParkingSpot(parkingFloor,vehicle);
			if(null != parkingSpot) {
				parkingSpot.setFloorId(parkingFloor.getId());
				return parkingSpot;
			}
		}
		throw new RuntimeException("Parking is full for particular vehicle type "+vehicle.getVehicleType()); 
	}

	private boolean isFull() {
		for(ParkingFloor parkingFloor : INSTANCE.getParkingFloors()) {
			if(!parkingFloor.isFull(parkingFloor)) {
				return false;
			}
		}
		return true;
	}

	public Ticket generateParkingTicket(Vehicle vehicle, String entryGateId) {
		ParkingSpot parkingSpot = this.findParkingSpot(vehicle);
		if(null != parkingSpot) {
			for(EntryGate entryGate : INSTANCE.getEntryGates()) {
				if(entryGate.getId().equalsIgnoreCase(entryGateId)) {
					return entryGate.generateTicket(vehicle,parkingSpot,entryGate);
				}
			}
			 
		}
		return null;
	}

	public Bill generateBill(Vehicle vehicle, String exitGateId, Ticket carTicket) {
		for(ExitGate exitGate : exitGates) {
			if(exitGate.getId().equalsIgnoreCase(exitGateId)) {
				
				return exitGate.generateBill(vehicle, carTicket,exitGateId);
			}
		}
		return null;
	}

	public void vacateParkingSpot(ParkingSpot assignParkingSpot) {
		for(ParkingFloor parkingFloor : INSTANCE.getParkingFloors()) {
			if(parkingFloor.getId().equalsIgnoreCase(assignParkingSpot.getFloorId())) {
				parkingFloor.vacateParkingSpotFromFloor(parkingFloor,assignParkingSpot);
			}
		}
	}

	public PaymentStatus acceptPayment(Payment payment) {
		for(ExitGate exitGate : INSTANCE.getExitGates()) {
			if(exitGate.getId().equals(payment.getBill().getExitGateId())) {
				return exitGate.completePayment(payment.getBill().getAmount());
			}
		}
		return null;
	}
	
	
	

}
