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
import javax.ws.rs.core.Response;

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
public class Attempt {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Date startDateTime = new Date();
	private Date endDateTime;
	private int timeToCompleteInSeconds;
	
	@OneToOne(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private TestTemplate testTemplate;
	
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
	// GETTER & SETTER for testTemplate
	public TestTemplate getTestTemplate() {
		return testTemplate;
	}
	public void setTestTemplate(TestTemplate testTemplate) {
		this.testTemplate = testTemplate;
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
	

	/**
	 * author Bas
	 * Based on code from Stefan Martens
	 * Calculates the overall score of the test take in the current attempt. 
	 * @return correct answers: a list of AnswerLists
	 */
	public double getTestScore(){
		List<AnswerList> correctAnswers = this.getAllCorrectAnswers();
		if (this.givenAnswers.size() != correctAnswers.size()){
			return -1;
		} else {
			List<Boolean> scoresList = supplyScoreList(this.givenAnswers, correctAnswers);
			return this.calculatePercentage(scoresList);
		}	
	}
	
	/**
	 * author Bas
	 * Based on code from Stefan Martens
	 * Retrieves all correct answers for all questions in the question list (a.k.a. test template) used in this attempt
	 * @return correct answers: a list of AnswerLists
	 */
	private List<AnswerList> getAllCorrectAnswers(){	
		List<AnswerList> correctAnswers = new ArrayList<>(); 
		//TestTemplate questionList = this.attemptRepository.findOne(idExam).getTestTemplate();
		//List<Question> listQuestions = this.testTemplate.getQuestions();
		for (Question question : this.testTemplate.getQuestions()) {
			correctAnswers.add(question.getCorrectAnswers());
		}
		return correctAnswers;
	}
	
	/**
	 * author Bas
	 * Based on code from Stefan Martens
	 * Retrieves a list of booleans which tell of each question in the list whether it was answered correctly.
	 * Questions are multiple choice and can have one or more correct answers. Each question can have up to 10 possible answers. Each of those 10 possible answers is marked correct (true) or incorrect (false).
	 * @return scoreList 
	 */
	private List<Boolean> supplyScoreList(List<AnswerList> givenAnswers, List<AnswerList> correctAnswers){	
		List<Boolean> scoresList = new ArrayList<>();
		for (int questionIndex = 0; questionIndex < givenAnswers.size(); questionIndex++){ // givenAnswers.size() is the number of questions in the test template.
			Boolean questionCorrectlyAnswered = true;
			for (int answerIndex = 0; answerIndex < givenAnswers.get(questionIndex).getAnswers().size(); answerIndex++){
				// Check for each answer if the given answer (true/false) is the same as the correct one.
				if (givenAnswers.get(questionIndex).getAnswers().get(answerIndex) != correctAnswers.get(questionIndex).getAnswers().get(answerIndex)){
					// As soon as one of the possible answers is wrongly selected the whole question is marked as incorrectly answered.
					questionCorrectlyAnswered = false;
					break; // No need to check further, so break out of for loop.
				}
			}
			scoresList.add(questionCorrectlyAnswered);
		}
		
		return scoresList;
		
		// Below is the original code from Stefan.
		// This contains a check to see if the number of given answers is the same as the number of correct answers. In the current design
		// that is however always the case (both are always 10), so I have removed that check from the code.
		
//		List<Boolean> scoresList = new ArrayList<>();
//		 for (int questionIndex = 0; questionIndex < givenAnswers.size(); questionIndex++){
//			 Boolean good = true;
//			 for (int answerIndex = 0; answerIndex < givenAnswers.get(questionIndex).getAnswers().size(); answerIndex++){
//				 if (givenAnswers.get(questionIndex).getAnswers().size() == correctAnswers.get(questionIndex).getAnswers().size()){
//					 if (givenAnswers.get(questionIndex).getAnswers().get(answerIndex) != correctAnswers.get(questionIndex).getAnswers().get(answerIndex)){
//						 good = false;
//					 }
//				 } else {
//					 good = false;
//				 }
//			 }
//			 scoresList.add(good);
//		 }
//		
//		return scoresList;

	}
	
	/**
	 * author Stefan Martens
	 * Calculates percentage correct answers
	 * coded with OCP. stream to improve performance
	 * @return percentage
	 */
	private double calculatePercentage(List<Boolean> scoresList){
		double count = (double) scoresList.stream().filter((x) -> x.booleanValue()).count(); // Count number of correctly answered questions (nr of 'true' in given list).
		double scorePercentage = count / scoresList.size();
		return scorePercentage;
	}
	
}
