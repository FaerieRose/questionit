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
	
	public Iterable<QuestionList> findAll() {
		Iterable<QuestionList> result = this.questionListRepository.findAll();
		return result;
	}
}
