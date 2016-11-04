package nl.programit.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.Question;

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

	/**
	 * Saves a question to the database. If no id included, a new entry
	 * is created, otherwise an existing one is overwritten
	 * @param question the question to be saved
	 */
	public void save(Question question) {
		this.questionRepository.save(question);
	}

	/**
	 * Retrieves all questions stored in the database 
	 * @return all questions
	 */
	public Iterable<Question> findAll() {
		Iterable<Question> result = this.questionRepository.findAll();
		return result;
	}
	
	/**
	 * Retrieves one question from the database with specified id. If the id
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
		this.questionRepository.delete(id);
		return result;
	}
	
}
