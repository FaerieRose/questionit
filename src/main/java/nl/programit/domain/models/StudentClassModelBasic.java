package nl.programit.domain.models;

import java.util.ArrayList;
import java.util.List;

import nl.programit.domain.Instructor;
import nl.programit.domain.Student;
import nl.programit.domain.StudentClass;

public class StudentClassModelBasic {
	private StudentClass studentClass;

	public StudentClassModelBasic(StudentClass studentClass) {
		this.studentClass = studentClass;
	}
	
	
	public long getId() {
		return this.studentClass.getId();
	}

	public String getName() {
		return this.studentClass.getName();
	}

	public List<InstructorModelName> getInstructors() {
		List<InstructorModelName> result = new ArrayList<>();
		for (Instructor instructor: this.studentClass.getInstructors()) {
			result.add(new InstructorModelName(instructor));
		}
		return result;
	}

	public List<Student> getStudents() {
		return this.studentClass.getStudents();
	}

}
