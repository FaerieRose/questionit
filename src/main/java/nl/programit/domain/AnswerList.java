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
	private List<Boolean> answers = new ArrayList<Boolean>();
	
	// Constructor where an array of 10 answers is created
	public AnswerList() {
		for (int i = 0 ; i < 10 ; i++) {
			answers.add(false);
		}
	}
	
	// ---------------------------------------------------
	// GETTER & SETTER for id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	// ---------------------------------------------------
	// GETTER & SETTER for answers
	// The setter copies the answers into the existing array. If the posted array is larger than 10, only the first 10 are copied
	public List<Boolean> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Boolean> answers) {
		int size = answers.size();
		if (size > 10) size = 10;
		for (int i = 0 ; i < 10 ; i++) {
			if (i < size) this.answers.set(i, answers.get(i));
			else          this.answers.set(i, false);
		}
	}
	public void setAnswer(boolean answer, int index) {
		this.answers.set(index, answer);
	}
}
