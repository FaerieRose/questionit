package nl.programit.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.programit.domain.Question;;

/**
 * 
 * @author FaerieRose
 * @version v0.1
 */
@Component
public interface QuestionRepository  extends CrudRepository<Question, Long> {

}
