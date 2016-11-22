package nl.programit.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.programit.domain.Instructor;

@Component
public interface InstructorRepository extends CrudRepository<Instructor, Long> {

}
