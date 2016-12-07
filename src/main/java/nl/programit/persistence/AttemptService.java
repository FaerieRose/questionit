package nl.programit.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.Attempt;

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
}
