package nl.programit.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * Attempt with a list of questions and an exam time in minutes
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-04
*/
@Entity
public class TestTemplate extends QuestionTemplate implements Serializable {

	private static final long serialVersionUID = 3352150692313343918L;
	
	private int attemptTimeInMinutes;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<Question> questions = new ArrayList<>();

	// ---------------------------------------------------
	// GETTER & SETTER for examTimeInMinutes
	public int getAttemptTimeInMinutes() {
		return attemptTimeInMinutes;
	}
	public void setAttemptTimeInMinutes(int attemptTimeInMinutes) {
		this.attemptTimeInMinutes = attemptTimeInMinutes;
	}

	// ---------------------------------------------------
	// GETTER & SETTER for questions
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	// ---------------------------------------------------
	/**
	 * Method to add a Question to the TestTemplate
	 * @param question
	 */
	public void addQuestion(Question question) {
		System.out.println("we komen in de addquestion met vraag " + question.getName() + "  en met ID :" + question.getId());
		questions.add(question);
		System.out.println("vraag zou moeten zijn toegeveogd aan de list");
	}
}
