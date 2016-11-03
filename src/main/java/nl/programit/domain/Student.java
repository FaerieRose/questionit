package nl.programit.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Student extends Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
//	@OneToMany(fetch=FetchType.EAGER, mappedBy="student", cascade = CascadeType.ALL)
//	private List<Exam> exams;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
