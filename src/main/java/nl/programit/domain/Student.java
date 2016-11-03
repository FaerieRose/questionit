package nl.programit.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Student extends Person implements Serializable{
	
	private static final long serialVersionUID = 5536315112148141660L;


	
//	@OneToMany(fetch=FetchType.EAGER, mappedBy="student", cascade = CascadeType.ALL)
//	private List<Exam> exams;


}
