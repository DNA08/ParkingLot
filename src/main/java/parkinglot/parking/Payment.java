package parkinglot.parking;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Payment {
	
	private String id;
	private Bill bill;
	private PaymentStatus paymentStatus;
	
	public Payment(Bill bill) {
		id = UUID.randomUUID().toString();
		this.bill = bill;
		this.paymentStatus = paymentStatus.INITIATED;
	}

}
