package parkinglot.parking;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import parkinglot.vehicle.Vehicle;

@Getter
@Setter
@ToString
public class EntryGate {
	private String id;

	public EntryGate(String id) {
		super();
		this.id = id;
	}

	public Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot, EntryGate entryGate) {
		Ticket ticket = Ticket.builder().number(UUID.randomUUID().toString())
				.assignParkingSpot(parkingSpot)
				.issuedAt(LocalDateTime.now())
				.status(TicketStatus.GENERATED)
				.EntryGateId(entryGate.getId())
				.build();
		return ticket;
	}
}
