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
	 * @return created AnswerList or null in case of failure
	 */
	public AnswerList save(AnswerList answerList) {
		return this.answerListRepository.save(answerList);
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
	
	public void setAnswer(long id, int position, boolean answer) {
		AnswerList answerList = this.answerListRepository.findOne(id);
		answerList.setAnswer(answer, position);
		this.answerListRepository.save(answerList);
	}
	
	/**
	 * Deletes one AnswerLest from the database with specified id if it exists.
	 * @param id the id of the AnswerList
	 * @return true if the AnswerList existed, false if it did not
	 */
	public boolean deleteAnswerListById(long id) {
		AnswerList answerList = this.findById(id);
		if (answerList != null) {
			this.answerListRepository.delete(answerList);
			return true;
		}
		return false;
	}
}
