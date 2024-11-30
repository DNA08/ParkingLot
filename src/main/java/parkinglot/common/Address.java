package parkinglot.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Address {
	private String addressLine;
	private String street;
	private String city;
	private String pincode;
	private String country;
	@Override
	public String toString() {
		return "Address [addressLine=" + addressLine + ", street=" + street + ", city=" + city + ", pincode=" + pincode
				+ ", country=" + country + "]";
	}
	
}
