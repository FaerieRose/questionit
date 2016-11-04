package nl.programit.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-03
*/
@Entity
public class Question extends QuestionTemplate implements Serializable {
	
	private static final long serialVersionUID = 7115929504873439448L;

	private String question;
	private String explantionAnswer;
	private String typeOfQuestion;
	
	@OneToOne(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private AnswerList correctAnswers;
	public AnswerList getCorrectAnswers() {
		return correctAnswers;
	}
	public void setCorrectAnswers(AnswerList correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	
	@ElementCollection(fetch=FetchType.EAGER)
	private List<String> possibleAnswers = new ArrayList<String>();
	
	
	public List<String> getPossibleAnswers() {
		return possibleAnswers;
	}
	public void setPossibleAnswers(List<String> possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}

	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getExplantionAnswer() {
		return explantionAnswer;
	}
	public void setExplantionAnswer(String explantionAnswer) {
		this.explantionAnswer = explantionAnswer;
	}
	
	public String getTypeOfQuestion() {
		return typeOfQuestion;
	}
	public void setTypeOfQuestion(String typeOfQuestion) {
		this.typeOfQuestion = typeOfQuestion;
	}
	
	
	
}
