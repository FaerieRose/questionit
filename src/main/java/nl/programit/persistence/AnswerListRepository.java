package nl.programit.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.programit.domain.AnswerList;

/**
 * CrudRepository for AnswerList
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-04
 */
@Component
public interface AnswerListRepository extends CrudRepository<AnswerList, Long> {

}
