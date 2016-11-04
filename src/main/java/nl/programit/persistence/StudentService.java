package nl.programit.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.Student;
import nl.programit.persistence.StudentRepository;

@Service
@Transactional
public class StudentService {

	

		@Autowired
		private StudentRepository studentRepository;


		public Iterable<Student> findAll() {
			Iterable<Student> result = this.studentRepository.findAll();
			return result;
		}
		
		public void save(Student student) {
			this.studentRepository.save(student);
		}
}
