package nl.programit.domain.models;

import java.util.ArrayList;
import java.util.List;

import nl.programit.domain.Instructor;
import nl.programit.domain.StudentClass;

public class InstructorModelBasic {
	private Instructor instructor;
	
	public InstructorModelBasic(Instructor instructor) {
		this.instructor = instructor;
	}

	// Exclude StudentClasses from this model to prevent recursive loop
	/*
	public List<StudentClassModelBasic> getStudentClasses() {
		List<StudentClassModelBasic> result = new ArrayList<>();
		for (StudentClass studentClass: this.instructor.getStudentClasses()) {
			result.add(new StudentClassModelBasic(studentClass));
		}
		return result;
	}
	*/
	public String getFirstName() {
		return this.instructor.getFirstName();
	}

	public String getLastName() {
		return this.instructor.getLastName();
	}

	public String getEmail() {
		return this.instructor.getEmail();
	}

	public long getId() {
		return this.instructor.getId();
	}
	
	
}
