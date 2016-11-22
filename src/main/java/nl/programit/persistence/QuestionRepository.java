package nl.programit.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.programit.domain.Question;;

/**
 * CrudRepository for Question
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-03
 */
@Component
public interface QuestionRepository extends CrudRepository<Question, Long> {

}
