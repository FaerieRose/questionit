package nl.programit.domain.models;

import nl.programit.domain.Instructor;

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
