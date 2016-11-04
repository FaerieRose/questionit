package nl.programit.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.programit.domain.StudentClass;

@Component
public interface StudentClassRepository extends CrudRepository<StudentClass, Long> {

}
