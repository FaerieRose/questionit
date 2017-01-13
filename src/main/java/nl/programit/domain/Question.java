package nl.programit.domain;

import java.io.Serializable;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing exam question the possible answers, correct answers and all
 * answers given by students
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-03
*/
@Entity
public class Question extends QuestionTemplate implements Serializable {
	
	private static final long serialVersionUID = 7115929504873439448L;

	private String question;
	private String explanationAnswer;
	private String typeOfQuestion;
	
	@OneToOne(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private AnswerList correctAnswers;

	@ElementCollection(fetch=FetchType.EAGER)
	private List<String> possibleAnswers = new ArrayList<String>();
	
	@OneToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<AnswerList> givenAnswers = new ArrayList<>();
	
	// ---------------------------------------------------
	// GETTER & SETTER for givenAnswers
	public List<AnswerList> getGivenAnswers() {
		return givenAnswers;
	}
	public void setGivenAnswers(List<AnswerList> givenAnswers) {
		this.givenAnswers = givenAnswers;
	}
	
	// ---------------------------------------------------
	// GETTER & SETTER for correctAnswers
	public AnswerList getCorrectAnswers() {
		return correctAnswers;
	}
	public void setCorrectAnswers(AnswerList correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	
	// ---------------------------------------------------
	// GETTER & SETTER for possibleAnswers
	public List<String> getPossibleAnswers() {
		return possibleAnswers;
	}
	public void setPossibleAnswers(List<String> possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}

	// ---------------------------------------------------
	// GETTER & SETTER for question
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	// ---------------------------------------------------
	// GETTER & SETTER for explanationAnswer
	public String getExplanationAnswer() {
		return explanationAnswer;
	}
	public void setExplanationAnswer(String explantionAnswer) {
		this.explanationAnswer = explantionAnswer;
	}
	
	// ---------------------------------------------------
	// GETTER & SETTER for typeOfQuestion
	public String getTypeOfQuestion() {
		return typeOfQuestion;
	}
	public void setTypeOfQuestion(String typeOfQuestion) {
		this.typeOfQuestion = typeOfQuestion;
	}

	/**
	 * Method to add an AnswerList to the givenAnswers of Question
	 * @param answerlist
	 */
	public void addGivenAnswerList(AnswerList answerList) {
		this.givenAnswers.add(answerList);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}
	
}
