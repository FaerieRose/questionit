package nl.programit.domain.models;

import nl.programit.domain.Student;

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
	
	public String getPassword() {
		return this.student.getPassword();
	}
	
	public boolean isValid() {
		return this.student.isValid();
	}
}
