package nl.programit.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class StudentClass {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<Instructor> instructors = new ArrayList<>();
	
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<Student> students;
	
	public void addInstructor(Instructor instructor) {
		if (!instructors.contains(instructor)) {
			instructors.add(instructor);
		}
	}
	
	public void addStudent(Student student) {
		if (!students.contains(student)) {
		students.add(student);
		}
	}
	
	public void removeStudent(Student student) {
		if (!students.contains(student)) {
		students.remove(student);
		}
	}
	public void removeInstructor(Instructor instructor) {
		if (!instructors.contains(instructor)) {
			instructors.remove(instructor);
		}
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}	
}
