package nl.programit.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.Question;

/**
 * 
 * @author FaerieRose
 * @version v0.1
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
	
}
