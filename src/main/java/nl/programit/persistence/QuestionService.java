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


	public void save(Question question) {
		this.questionRepository.save(question);
	}

	public Iterable<Question> findAll() {
		Iterable<Question> result = this.questionRepository.findAll();
		return result;
	}
	
}
