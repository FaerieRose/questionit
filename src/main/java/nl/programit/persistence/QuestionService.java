package nl.programit.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.Question;
import nl.programit.domain.models.QuestionModelBasic;
import nl.programit.domain.models.QuestionModelName;
import nl.programit.domain.models.QuestionModelAttempt;
import nl.programit.domain.models.QuestionModelExamReview;
import nl.programit.domain.AnswerList;

/**
 * This class contains several methods to interact with QuestionRepository
 * to add, get or remove data from the database
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-03
 */
@Service
@Transactional
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerListRepository answerListRepository;

	/**
	 * Saves a Question to the database. If no id included, a new entry
	 * is created, otherwise an existing one is overwritten
	 * @param question the Question to be saved
	 */
	public Question save(Question question) {
// ======================================================================		
// The code below has been commented out to prevent a new question from
//		being created if an existing question is to be saved
// ======================================================================		
//		AnswerList answerList = question.getCorrectAnswers();
//		if (answerList == null) {
//			answerList = new AnswerList();
//			this.answerListRepository.save(answerList);
//		}
//		
//		Question qstn = this.questionRepository.findOne(question.getId());
//		if (qstn != null) {
//			qstn.setObsolete(true);
//			this.questionRepository.save(qstn);
//			question.setId(0L);
//		}
//		question.setObsolete(false);
//		question.setCorrectAnswers(answerList);
		return this.questionRepository.save(question); 
	}
	

	/**
	 * Retrieves all Questions stored in the database 
	 * @return all Questions
	 */
	public Iterable<Question> findAll() {
		Iterable<Question> result = this.questionRepository.findAll();
		return result;
	}
	
	/**
	 * Retrieves one Question from the database with specified id. If the id
	 * does not exist, null is returned 
	 * @param id the id of the Question
	 * @return requested Question or null if it does not exist
	 */
	public Question findById(long id) {
		return this.questionRepository.findOne(id);
	}
	
	/**
	 * Delete the Question with the requested id
	 * @param id This is the identifier of Question in the Database
	 * @return the Question of the removed item or NULL if nothing removed
	 */
	public Question deleteById(long id) {
		Question result = this.findById(id);
		// Delete the correct answers
		AnswerList correctAnswers = result.getCorrectAnswers();
		if (correctAnswers != null) this.answerListRepository.delete(correctAnswers);
		// Delete the given answers
		List<AnswerList> arrayAnswerList = result.getGivenAnswers();
		if (arrayAnswerList != null) {
			for (AnswerList a : arrayAnswerList) {
				this.answerListRepository.delete(a);
			}
		}
		// Delete the Question
		this.questionRepository.delete(id);
		return result;
	}
	
	/**
	 * Converts a Question to QuestionModelBasic to prevent loops and restrict data traffic
	 * @param question the Question to be converted
	 * @return QuestionModelBasic version of the Question
	 */
	public QuestionModelBasic convertToModelBasic(Question question) {
		QuestionModelBasic result = new QuestionModelBasic(question);
		return result;
	}
	
	/**
	 * Converts a Question to QuestionModelAttempt to send only data needed to make exam
	 * @param question the Question to be converted
	 * @return QuestionModelAttempt version of the Question
	 */
	public QuestionModelAttempt convertToModelExam(Question question) {
		QuestionModelAttempt result = new QuestionModelAttempt(question);
		return result;
	}
	
	/**
	 * Converts a Question to QuestionModelExamReview to send only data needed to review exam
	 * @param question the Question to be converted
	 * @return QuestionModelExamReview version of the Question
	 */
	public QuestionModelExamReview convertToModelExamReview(Question question) {
		QuestionModelExamReview result = new QuestionModelExamReview(question);
		return result;
	}
	
	
	/**
	 * Converts a Question to QuestionModelName to send only id and name
	 * @param question the Question to be converted
	 * @return QuestionModelName version of the Question
	 */
	public QuestionModelName convertToModelName(Question question) {
		QuestionModelName result = new QuestionModelName(question);
		return result;
	}	
}
