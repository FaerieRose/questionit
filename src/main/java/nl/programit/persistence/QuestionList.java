package nl.programit.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import nl.programit.domain.Question;
import nl.programit.domain.QuestionTemplate;


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
	
	@OneToOne(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Question questions;

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
	public Question getQuestions() {
		return questions;
	}
	public void setQuestions(Question questions) {
		this.questions = questions;
	}
}
