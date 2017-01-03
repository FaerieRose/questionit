package nl.programit.domain.models;

import nl.programit.domain.Student;

/**
 * Wrapper class for Student. Excludes attempts and password.
 * 
 * @author Created by Bas Smulders
 * @version v0.1
 * @since 2017-01-02
*/
public class StudentModelBasic {
	private Student student;
	
	public StudentModelBasic(Student student) {
		this.student = student;
	}
	
	public long getId() {
		return this.student.getId();
	}

	public String getFirstName() {
		return this.student.getFirstName();
	}

	public String getLastName() {
		return this.student.getLastName();
	}

	public String getEmail() {
		return this.student.getEmail();
	}
	
	public boolean isValid() {
		return this.student.isValid();
	}
}

