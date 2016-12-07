package nl.programit.domain.models;

import nl.programit.domain.models.QuestionModelAttempt;
import nl.programit.domain.AnswerList;
import nl.programit.domain.Question;

/**
 * A model for Question that only gets the information necessary to review a Question
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-21
 */
public class QuestionModelExamReview extends QuestionModelAttempt {
	private Question question;

	public QuestionModelExamReview(Question question) {
		super(question);
		this.question = question;
	}
	
	public AnswerList getCorrectAnswers() {
		return this.question.getCorrectAnswers();
	}
	
	public String getExplanationAnswer() {
		return this.question.getExplantionAnswer();
	}
}
