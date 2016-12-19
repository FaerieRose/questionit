package nl.programit.domain.models;

import java.util.Date;
import java.util.List;

import nl.programit.domain.AnswerList;
import nl.programit.domain.Attempt;
import nl.programit.domain.TestTemplate;

/**
 * A model to get the meta data of a Attempt but for TestTemplate only the id
 * 
 * @author S. Martens
 * @version v0.1
 * @since 2016-12-19
 */

public class AttemptModelBasic {
	private Attempt attempt;
	
	public AttemptModelBasic(Attempt attempt) {
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
	
	public int getTimeToCompleteInSeconds() {
		return attempt.getTimeToCompleteInSeconds();
	}
	
	public long getTestTemplate() {
		return attempt.getTestTemplate().getId();
	}
	
	public List<Integer> getMarkedQuestions() {
		return attempt.getMarkedQuestions();
	}
	
	public List<AnswerList> getGivenAnswers() {
		return attempt.getGivenAnswers();
	}
	
	
	
}
