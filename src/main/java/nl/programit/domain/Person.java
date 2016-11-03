package nl.programit.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// fields
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
