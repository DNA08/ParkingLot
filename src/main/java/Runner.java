
import parkinglot.common.Address;
import parkinglot.common.Admin;
import parkinglot.parking.Bill;
import parkinglot.parking.ParkingSpotType;
import parkinglot.parking.Parkinglot;
import parkinglot.parking.Payment;
import parkinglot.parking.PaymentStatus;
import parkinglot.parking.Ticket;
import parkinglot.vehicle.Car;
import parkinglot.vehicle.ElectricCar;
import parkinglot.vehicle.HeavyVehicle;
import parkinglot.vehicle.Vehicle;

public class Runner {

	public static void main(String[] args) throws Exception {
		
		Parkinglot parkingLot = Parkinglot.INSTANCE;
		
		Address address = Address.builder().addressLine("C-1032 PatelNagar Bareilly").city("Bareilly").country("India").pincode("243001")
						.build();
		parkingLot.setAddress(address);
		
		Admin admin = new Admin();
		admin.addFloors("1f");
		admin.addFloors("2f");
		admin.addParkingSpot("1f", "1a",ParkingSpotType.FOUR_WHEELER);admin.addParkingSpot("1f", "2a",ParkingSpotType.HEAVY_VEHICLE);
		admin.addParkingSpot("1f", "3a",ParkingSpotType.TWO_WHEELER);admin.addParkingSpot("1f", "4a",ParkingSpotType.ELECTRIC_VEHICLE);
		admin.addParkingSpot("2f", "1a",ParkingSpotType.FOUR_WHEELER);admin.addParkingSpot("2f", "2a",ParkingSpotType.HEAVY_VEHICLE);
		admin.addParkingSpot("2f", "3a",ParkingSpotType.TWO_WHEELER);admin.addParkingSpot("2f", "4a",ParkingSpotType.ELECTRIC_VEHICLE);
		admin.addEntryGate("Entry 1");	admin.addEntryGate("Entry 2");
		admin.addExitGate("Exit 1");	admin.addExitGate("Exit 2");
		System.out.println(parkingLot);
		
		Vehicle car = new Car("UP25EA2553");
		Ticket carTicket = parkingLot.generateParkingTicket(car,"Entry 1");
		
		Vehicle eCar = new ElectricCar("UP25EE2222");
		Ticket eCarTicket = parkingLot.generateParkingTicket(eCar,"Entry 2");
		
		Vehicle car2 = new Car("UP25EA2555");
		Ticket car2Ticket = parkingLot.generateParkingTicket(car2,"Entry 2");
		
		Vehicle truck = new HeavyVehicle("UP24EA9999");
		Ticket truckParkingTicket = parkingLot.generateParkingTicket(truck,"Entry 2");
		
		Thread.sleep(1000);
		Bill carBill = parkingLot.generateBill(car,"Exit 1",carTicket);
		System.out.println(carBill);
		System.out.println(carTicket.getAssignParkingSpot());
		
		Payment payment = new Payment(carBill);
		System.out.println(payment);
		PaymentStatus paymentStatus = parkingLot.acceptPayment(payment);
		System.out.println(paymentStatus);
		
		Thread.sleep(100);
	}

}
