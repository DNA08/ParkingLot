package parkinglot.factory;

import parkinglot.parking.ParkingSpotType;
import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleType;

public class ParkingSpotFactory {

	public ParkingSpotType findSpotTypeForVehicle(Vehicle vehicle) {
		VehicleType vehicleType = vehicle.getVehicleType();
		ParkingSpotType psType;
		if(VehicleType.CAR.equals(vehicleType)) {
			psType = ParkingSpotType.FOUR_WHEELER;
		}else if(VehicleType.BIKE.equals(vehicleType)) {
			psType = ParkingSpotType.TWO_WHEELER;
		}else if(VehicleType.HEAVY_VEHICLE.equals(vehicleType)){
			psType = ParkingSpotType.HEAVY_VEHICLE;
		}else {
			psType = ParkingSpotType.ELECTRIC_VEHICLE;
		}
		return psType;
		
	}
	
	

}
