package nl.programit.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Class for storing exam results during the exam and when the exam is completed
 * 
 * @author Created by Felix van Loenen, edited by FaerieRose
 * @version v0.1
 * @since 2016-11-03
*/
@Entity
public class Exam {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Date startDateTime = new Date();
	private Date endDateTime;
	private int timeToCompleteInSeconds;
	
	@OneToOne(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private QuestionList questionList;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private List<Integer> markedQuestions = new ArrayList<Integer>();
	
	@OneToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<AnswerList> givenAnswers = new ArrayList<>();

	// ---------------------------------------------------
	// GETTER & SETTER for id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	// ---------------------------------------------------
	// GETTER & SETTER for endDateTime
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	// ---------------------------------------------------
	// GETTER & SETTER for startDateTime
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	// ---------------------------------------------------
	// GETTER & SETTER for timeToCompleteInSeconds
	public int getTimeToCompleteInSeconds() {
		return timeToCompleteInSeconds;
	}
	public void setTimeToCompleteInSeconds(int timeToCompleteInSeconds) {
		this.timeToCompleteInSeconds = timeToCompleteInSeconds;
	}

	// ---------------------------------------------------
	// GETTER & SETTER for questionList
	public QuestionList getQuestionList() {
		return questionList;
	}
	public void setQuestionList(QuestionList questionList) {
		this.questionList = questionList;
	}

	// ---------------------------------------------------
	// GETTER & SETTER for markedQuestions
	public List<Integer> getMarkedQuestions() {
		return markedQuestions;
	}
	public void setMarkedQuestions(List<Integer> markedQuestions) {
		this.markedQuestions = markedQuestions;
	}

	// ---------------------------------------------------
	// GETTER & SETTER for givenAnswers
	public List<AnswerList> getGivenAnswers() {
		return givenAnswers;
	}
	public void setGivenAnswers(List<AnswerList> givenAnswers) {
		this.givenAnswers = givenAnswers;
	}
	
}
