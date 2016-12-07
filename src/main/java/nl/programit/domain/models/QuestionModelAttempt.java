package nl.programit.domain.models;

import java.util.List;

import nl.programit.domain.Question;

/**
 * A model for Question which only gets information necessary for the exam
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-04
 */
public class QuestionModelAttempt {
	private Question question;
	
	public QuestionModelAttempt(Question question) {
		this.question = question;
	}
	
	public long getId() {
		return this.question.getId();
	}

	public boolean isEnabled() {
		return this.question.isEnabled();
	}
	
	public String getName() {
		return this.question.getName();
	}
	
	public String getQuestion() {
		return this.question.getQuestion();
	}
	
	public List<String> getPossibleAnswers() {
		return this.question.getPossibleAnswers();
	}
	
	public String getTypeOfQuestion() {
		return this.question.getTypeOfQuestion();
	}
}
