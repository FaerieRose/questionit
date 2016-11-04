package nl.programit.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.programit.domain.QuestionList;

/**
 * CrudRepository for QuestionList
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-04
 */
@Component
public interface QuestionListRepository extends CrudRepository<QuestionList, Long> {

}
