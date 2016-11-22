package nl.programit.persistence;



import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.AnswerList;
import nl.programit.domain.Instructor;
import nl.programit.domain.Question;
import nl.programit.domain.models.QuestionModelBasic;


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
	 * Retrieves one Instructor from the database with specified id. If the id
	 * does not exist, null is returned
	 * 
	 * @param id
	 *            the id of the Instructor
	 * @return requested Instructor or null if it does not exist
	 */
	public Instructor findById(long id) {
		return this.instructorRepository.findOne(id);
	}

	public Instructor deleteById(Long id) {
		// Find the correct instructor
		Instructor result = this.findById(id);
		//Delete the Instructor
		this.instructorRepository.delete(id);
		return result;
	}
	}


