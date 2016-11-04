package nl.programit.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A list of answers that are stored in a List of Characters. This can be a list
 * of correct answers or a list of answers given by a student. Every answer
 * may only be 1 letter. 
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-04
 */
@Entity
public class AnswerList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ElementCollection(fetch=FetchType.EAGER)
	List<Character> answers = new ArrayList<>();
	
	// GETTER & SETTER for Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	// GETTER & SETTER for answers
	public List<Character> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Character> answers) {
		this.answers = answers;
	}


}
