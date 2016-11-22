package nl.programit.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.Exam;

/**
 * This class contains several methods to interact with ExamRepository
 * to add, get or remove data from the database
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-07
 */
@Service
@Transactional
public class ExamService {

	@Autowired
	ExamRepository examRepository;
	
	/**
	 * Saves an Exam to the database. If no id included, a new entry
	 * is created, otherwise an existing one is overwritten
	 * @param exam the Exam to be saved
	 */
	public void save(Exam exam) {
		this.examRepository.save(exam);
	}
	
	/**
	 * Retrieves one Exam from the database with specified id. If the id
	 * does not exist, null is returned 
	 * @param id the id of the Exam
	 * @return requested Exam or null if it does not exist
	 */
	public Exam findById(long id) {
		return this.examRepository.findOne(id);
	}
	
	/**
	 * Retrieves all Exams stored in the database 
	 * @return all Exams
	 */
	public Iterable<Exam> findAll() {
		return this.examRepository.findAll();
	}
}
