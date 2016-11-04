package nl.programit.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person implements Serializable {

	private static final long serialVersionUID = -4795224222157957673L;
	
	private List<StudentClass> studentClasses = new ArrayList<>();

	public List<StudentClass> getStudentClasses() {
		return studentClasses;
	}

	public void setStudentClasses(List<StudentClass> studentClasses) {
		this.studentClasses = studentClasses;
	}

}
