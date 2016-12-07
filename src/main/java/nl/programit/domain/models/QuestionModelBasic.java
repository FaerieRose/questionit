package nl.programit.domain.models;

import java.util.List;

import nl.programit.domain.AnswerList;
import nl.programit.domain.Instructor;
import nl.programit.domain.Question;

/**
 * A model for Question which gets all the same fields except Instructor is replaced by InstructorModelName 
 * to prevent loops when getting a Question 
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-16
 */
public class QuestionModelBasic {
	private Question question;
	
	public QuestionModelBasic(Question question) {
		this.question = question;
	}

	public long getId() {
		return this.question.getId();
	}

	public String getName() {
		return this.question.getName();
	}

	public String getQuestion() {
		return this.question.getQuestion();
	}

	public String getExplanationAnswer() {
		return this.question.getExplanationAnswer();
	}

	public String getTypeOfQuestion() {
		return this.question.getTypeOfQuestion();
	}

	public InstructorModelName getCreator() {
		Instructor creator = this.question.getCreator();
		if (creator != null) {
			InstructorModelName modelName = new InstructorModelName(creator);
			return modelName;
		}
		return null;
	}
	
	public List<String> getPossibleAnswers() {
		return this.question.getPossibleAnswers();
	}
	
	public int getProgrammingLanguage() {
		return this.question.getProgrammingLanguage();
	}
	
	public int getForExam() {
		return this.question.getForExam();
	}
	
	public AnswerList getCorrectAnswers() {
		return this.question.getCorrectAnswers();
	}
}
