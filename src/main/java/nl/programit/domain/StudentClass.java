package nl.programit.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
//import javax.persistence.FetchType;
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
	
	@ManyToMany //Use default LAZY fetch type to prevent unnecessary database access.
	@Fetch(FetchMode.SELECT)
	private List<Instructor> instructors = new ArrayList<>();
	
	@ManyToMany //Use default LAZY fetch type to prevent unnecessary database access.
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
		System.out.println("--------------------------------------------------------------------in de removestudent");
		if (students.contains(student)) {
			System.out.println("lengte van de list : " + this.students.size());
			System.out.println("-in de removestudent Student gevonden" + student.getFirstName());
		this.students.remove(student);
		System.out.println("lengte van de list : " + this.students.size());
	
		
		}
	}
	public void removeInstructor(Instructor instructor) {
		System.out.println("--------------------------------------------------------------------in de removeInstructor");
		if (instructors.contains(instructor)) {
			System.out.println("lengte van de list : " + this.instructors.size());
			System.out.println("-in de removeInstructor Instructor gevonden" + instructor.getFirstName());
		this.instructors.remove(instructor);
		System.out.println("lengte van de list : " + this.instructors.size());
	
		
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
