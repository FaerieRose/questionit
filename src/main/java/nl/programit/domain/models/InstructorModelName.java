package nl.programit.domain.models;

import nl.programit.domain.Instructor;

/**
 * A simple version of the Instructor with only get methods for id and name, where name is a composition of
 * firstName and lastName
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-16
 */
public class InstructorModelName {
	private Instructor instructor;
	
	public InstructorModelName(Instructor instructor) {
		this.instructor = instructor;
	}
	
	public long getId() {
		return this.instructor.getId();
	}
	
	public String getName() {
		return this.instructor.getFirstName() + " " + this.instructor.getLastName();
	}
}
