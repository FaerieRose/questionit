package nl.programit.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.programit.domain.Exam;

/**
 * CrudRepository for Exam
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-07
 */
@Component
public interface ExamRepository extends CrudRepository<Exam, Long> {

}
