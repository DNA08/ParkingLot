package parkinglot.common;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
	private long id;
	private String email;
	private String userName;
	private String password;
	private Date lastAccessed;
	private Contact contact;

}
