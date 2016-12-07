package nl.programit.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.programit.domain.Attempt;

/**
 * CrudRepository for Attempt
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-07
 */
@Component
public interface AttemptRepository extends CrudRepository<Attempt, Long> {

}
