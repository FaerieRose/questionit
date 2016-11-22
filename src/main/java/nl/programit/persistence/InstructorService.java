package nl.programit.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.Instructor;


@Service
@Transactional
public class InstructorService {
	
	@Autowired
	private InstructorRepository instructorRepository;
	
	public Iterable<Instructor> findAll() {
		Iterable<Instructor> result = this.instructorRepository.findAll();
		return result;
	}
	
	public void save(Instructor instructor) {
		this.instructorRepository.save(instructor);
	}

	/**
	 * Retrieves one Question from the database with specified id. If the id
	 * does not exist, null is returned 
	 * @param id the id of the Question
	 * @return requested Question or null if it does not exist
	 */
	public Instructor findById(long id) {
		return this.instructorRepository.findOne(id);
	}	
}
