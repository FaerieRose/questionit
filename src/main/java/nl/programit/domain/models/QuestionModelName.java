package nl.programit.domain.models;

import java.util.List;

import nl.programit.domain.Question;

/**
 * A model for Question which only gets information necessary for the name
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-23
 */
public class QuestionModelName {
	private Question question;
	
	public QuestionModelName(Question question) {
		this.question = question;
	}
	
	public long getId() {
		return this.question.getId();
	}
	
	public String getName() {
		return this.question.getName();
	}

	public String getTypeOfQuestion() {
		return this.question.getTypeOfQuestion();
	}	
}
