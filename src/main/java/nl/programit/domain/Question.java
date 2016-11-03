package nl.programit.domain;

import java.io.Serializable;

import javax.persistence.Entity;

//import java.util.ArrayList;
//import java.util.List;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;

/**
 * 
 * @author FaerieRose
 * @version v0.1
 */
@Entity
public class Question extends QuestionTemplate implements Serializable {
	
	private static final long serialVersionUID = 7115929504873439448L;

	private String question;
	private String explantionAnswer;
	private String typeOfQuestion;

/*	
	@Fetch(FetchMode.SELECT)
	private List<String> possibleAnswers = new ArrayList<>();
	
	public List<String> getPossibleAnswers() {
		return possibleAnswers;
	}
	public void setPossibleAnswers(List<String> possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}
*/
	
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