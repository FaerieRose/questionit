package nl.programit.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.AnswerList;

/**
 * This class contains several methods to interact with AnswerListRepository
 * to add, get or remove data from the database
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-07
 */
@Service
@Transactional
public class AnswerListService {

	@Autowired
	private AnswerListRepository answerListRepository;
	
	/**
	 * Saves a question to the database. If no id included, a new entry
	 * is created, otherwise an existing one is overwritten
	 * @param answerList the answerList to be saved
	 */
	public void save(AnswerList answerList) {
		this.answerListRepository.save(answerList);
	}
	
	/**
	 * Retrieves one AnswerList from the database with specified id. If the id
	 * does not exist, null is returned 
	 * @param id the id of the AnswerList
	 * @return requested AnswerList or null if it does not exist
	 */
	public AnswerList findById(long id) {
		return this.answerListRepository.findOne(id);
	}
}
