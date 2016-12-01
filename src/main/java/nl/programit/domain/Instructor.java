package nl.programit.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Instructor extends Person implements Serializable {

	private static final long serialVersionUID = -4795224222157957673L;
	
	@ManyToMany(fetch=FetchType.EAGER, mappedBy = "instructors", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	private List<StudentClass> studentClasses = new ArrayList<>();

	public List<StudentClass> getStudentClasses() {
		return studentClasses;
	}

	public void setStudentClasses(List<StudentClass> studentClasses) {
		this.studentClasses = studentClasses;
	}

	
}
