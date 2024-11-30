package parkinglot.parking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleType;

@Getter
@Setter
public class ExitGate {
	private String id;

	public ExitGate(String id) {
		super();
		this.id = id;
	}

	public Bill generateBill(Vehicle vehicle, Ticket ticket, String exitGateId) {
		Bill bill = new Bill();
		bill.setTicket(ticket);
		bill.setExitGateId(exitGateId);
		bill.setAmount(calculateCost(ticket,vehicle.getVehicleType()));
		Parkinglot.INSTANCE.vacateParkingSpot(ticket.getAssignParkingSpot());
		return bill;
	}
	
	public double calculateCost(Ticket ticket, VehicleType vehicleType) {
		Duration duration = Duration.between(ticket.getIssuedAt(), LocalDateTime.now());
		long seconds = duration.getSeconds();
		return seconds * 100;
	}

	public PaymentStatus completePayment(Double amount) {
		if(amount == 100.0) {
			return PaymentStatus.SUCCESSFULL;
		}
		return PaymentStatus.FAILURE;
	}
	
}
