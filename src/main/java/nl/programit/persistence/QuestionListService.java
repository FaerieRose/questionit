package nl.programit.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.QuestionList;

/**
 * This class contains several methods to interact with QuestionListRepository
 * to add, get or remove data from the database
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-04
 */
@Service
@Transactional
public class QuestionListService {

	@Autowired
	private QuestionListRepository questionListRepository;


	/**
	 * Saves a questionlist to the database. If no id included, a new entry
	 * is created, otherwise an existing one is overwritten
	 * @param questionList the QuestionList to be saved
	 */
	public void save(QuestionList questionList) {
		this.questionListRepository.save(questionList);
	}
	
	/**
	 * Retrieves all questionLists stored in the database 
	 * @return all questionLists
	 */	
	public Iterable<QuestionList> findAll() {
		Iterable<QuestionList> result = this.questionListRepository.findAll();
		return result;
	}
	
	/**
	 * Retrieves one Questionlist from the database with specified id. If the id
	 * does not exist, null is returned 
	 * @param id the id of the QuestionList
	 * @return requested QuestionList or null if it does not exist
	 */
	public QuestionList findById(long id) {
		QuestionList result = this.questionListRepository.findOne(id);
		return result;
	}
}
