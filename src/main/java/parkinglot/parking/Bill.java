package parkinglot.parking;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Bill {
	
	private Ticket ticket;
	private String exitGateId;
	private Double amount;

}
