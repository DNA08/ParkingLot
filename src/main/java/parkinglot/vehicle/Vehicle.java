package parkinglot.vehicle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import parkinglot.parking.Ticket;

@Getter
@Setter
@ToString
public abstract class Vehicle {
	private String numberPlate;
	private VehicleType vehicleType;
	
	public Vehicle(String numberPlate, VehicleType vehicleType) {
		super();
		this.numberPlate = numberPlate;
		this.vehicleType = vehicleType;
	}
	
	

}
