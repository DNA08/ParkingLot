package parkinglot.parking;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Ticket {
	private String number;
	private ParkingSpot assignParkingSpot;
	private LocalDateTime issuedAt;
	private TicketStatus status;
	private String EntryGateId;
}
