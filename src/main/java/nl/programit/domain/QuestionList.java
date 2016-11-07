package nl.programit.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * Exam with a list of questions and an exam time in minutes
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-04
*/
@Entity
public class QuestionList extends QuestionTemplate implements Serializable {

	private static final long serialVersionUID = 3352150692313343918L;
	
	private int examTimeInMinutes;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<Question> questions = new ArrayList<>();

	// ---------------------------------------------------
	// GETTER & SETTER for examTimeInMinutes
	public int getExamTimeInMinutes() {
		return examTimeInMinutes;
	}
	public void setExamTimeInMinutes(int examTimeInMinutes) {
		this.examTimeInMinutes = examTimeInMinutes;
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
	 * Method to add a Question to the Questionlist
	 * @param question
	 */
	public void addQuestion(Question question) {
		questions.add(question);
	}
	
	// ---------------------------------------------------
	/**
	 * Method to add a Instructor to the Questionlist
	 * @param question
	 */
	public void addInstructor(Instructor instructor) {
		this.setCreator(instructor);
	}
}
