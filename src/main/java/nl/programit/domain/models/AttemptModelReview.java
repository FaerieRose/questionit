package nl.programit.domain.models;

import java.util.Date;
import java.util.List;

import nl.programit.domain.AnswerList;
import nl.programit.domain.Attempt;
import nl.programit.domain.TestTemplate;

/**
 * A model to get the data of an Attempt that is relevant for the Review page.
 * 
 * @author Bas
 * @version v0.1
 * @since 2017-1-4
 */

public class AttemptModelReview {
	private Attempt attempt;
	
	public AttemptModelReview(Attempt attempt) {
		this.attempt = attempt;
	}
	
	// Getters
	public long getId() {
		return attempt.getId();
	}
	
	public Date getEndDateTime() {
		return attempt.getEndDateTime();
	}
	
	public Date getStartDateTime() {
		return attempt.getStartDateTime();
	}
	
	public long getTestTemplateId() {
		return attempt.getTestTemplate().getId();
	}

	public String getTestTemplateName() {
		return attempt.getTestTemplate().getName();
	}	
	
	public int getTestTemplateProgrammingLanguage() {
		return attempt.getTestTemplate().getProgrammingLanguage();
	}
	
	public double getTestScore() {
		return attempt.getTestScore();
	}
	
	public List<Boolean> getQuestionScores() {
		return attempt.getQuestionScores();
	}

}
