package nl.programit.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.programit.domain.Student;

@Component
public interface StudentRepository extends CrudRepository<Student, Long>{

}
