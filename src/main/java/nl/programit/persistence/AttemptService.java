package nl.programit.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.AnswerList;
import nl.programit.domain.Attempt;
import nl.programit.domain.Question;
import nl.programit.domain.TestTemplate;

/**
 * This class contains several methods to interact with AttemptRepository
 * to add, get or remove data from the database
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-07
 */
@Service
@Transactional
public class AttemptService {

	@Autowired
	AttemptRepository attemptRepository;
	
	/**
	 * Saves an Attempt to the database. If no id included, a new entry
	 * is created, otherwise an existing one is overwritten
	 * @param attempt the Attempt to be saved
	 */
	public void save(Attempt attempt) {
		this.attemptRepository.save(attempt);
	}
	
	/**
	 * Retrieves one Attempt from the database with specified id. If the id
	 * does not exist, null is returned 
	 * @param id the id of the Attempt
	 * @return requested Attempt or null if it does not exist
	 */
	public Attempt findById(long id) {
		return this.attemptRepository.findOne(id);
	}
	
	/**
	 * Retrieves all Exams stored in the database 
	 * @return all Exams
	 */
	public Iterable<Attempt> findAll() {
		return this.attemptRepository.findAll();
	}
	/**
	 * Retrieves all correct answers for a questionList
	 * @return correct answers
	 */
	public List<AnswerList> supplyCorrectAnswers(long idExam){	
		List<AnswerList> correctAnswers = new ArrayList<>(); 
		TestTemplate questionList = this.attemptRepository.findOne(idExam).getTestTemplate();
		List<Question> listQuestions = questionList.getQuestions();
		for (Question correctQuestion : listQuestions){
			correctAnswers.add(correctQuestion.getCorrectAnswers());
		}
		
		return correctAnswers;
	}
	
	/**
	 * Retrieves scoreList of question answered correctly
	 * @return scoreList 
	 */
	public List<Boolean> supplyScoreList(List<AnswerList> givenAnswers, List<AnswerList> correctAnswers){	
		List<Boolean> scoresList = new ArrayList<>();
		 for (int n = 0; n < givenAnswers.size(); n ++){
			 Boolean good = true;
			 for (int p = 0; p < givenAnswers.get(n).getAnswers().size(); p++){
				 if (givenAnswers.get(n).getAnswers().size() == correctAnswers.get(n).getAnswers().size()){
					 if (givenAnswers.get(n).getAnswers().get(p) != correctAnswers.get(n).getAnswers().get(p)){
						 good = false;
					 }
				 } else {
					 good = false;
				 }
			 }
			 scoresList.add(good);
		 }
		
		return scoresList;
	}
	
	/**
	 * Retrieves invalid answered questions 
	 * true count of amount of selected answers 
	 * number of selections is correctly
	 * @return invalid answered questions List 
	 */
	public List<Integer> supplyInvalidAnsweredQuestions(List<AnswerList> givenAnswers, List<AnswerList> correctAnswers){	
		List<Integer> result = new ArrayList<>();
		int counter = 1;
		int trueCountGiven = 0;
		int trueCountCorrect = 0;
		for (int k = 0; k < givenAnswers.size() ; k++) {
			trueCountGiven = 0;
			trueCountCorrect = 0;
			for (int i = 0; i < givenAnswers.get(k).getAnswers().size(); i++) {
			    if (givenAnswers.get(k).getAnswers().get(i) ) {
			        trueCountGiven++;
			    }
			}
		    for (int j = 0; j < correctAnswers.get(k).getAnswers().size(); j++) {
			    if (correctAnswers.get(k).getAnswers().get(j) ) {
			        trueCountCorrect++;	
			    }
			}
		    if (trueCountGiven != trueCountCorrect){
				result.add(counter);
			}
		    counter++;
		}
		return result;
	}
	
	/**
	 * Retrieves invalid answered questions 
	 * true count of amount of selected answers 
	 * number of selections is correctly
	 * coded with OCP. stream to improve performance
	 * @return invalid answered questions List 
	 */
	public List<Integer> supplyInvalidAnsweredQuestionsStream(List<AnswerList> givenAnswers, List<AnswerList> correctAnswers){	
		List<Integer> result = new ArrayList<>();
		List<Integer> r1 = givenAnswers.stream().map(AnswerList::trueCount).collect(Collectors.toList());	
		List<Integer> r2 = correctAnswers.stream().map(AnswerList::trueCount).collect(Collectors.toList());
		
		 for (int m = 0 ; m < r1.size(); m++){
			 if (r1.get(m) != r2.get(m)){
				 result.add(m+1);
			 }	 
		 }
		 return result;
	}
	
	/**
	 * Calculates percentage correct answers
	 * @return percentage
	 */
	public double calculatePercentage(List<Boolean> scoresList){
		double count = 0;
		 for (Boolean good : scoresList){
			 if (good){
				count++; 
			 }
		 }
		 double scorePercentages = count / scoresList.size();
		 return scorePercentages;
	}
	
	/**
	 * Calculates percentage correct answers
	 * coded with OCP. stream to improve performance
	 * @return percentage
	 */
	public double calculatePercentageStream(List<Boolean> scoresList){
		double count = (double) scoresList.stream().filter((x) -> x.booleanValue()).count();
		double scorePercentages = count / scoresList.size();
		return scorePercentages;
	}
	
}

