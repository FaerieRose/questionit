package nl.programit.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.Instructor;
import nl.programit.domain.Student;
import nl.programit.persistence.StudentRepository;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Iterable<Student> findAll() {
		System.out.println("================================= public Iterable<Student> findAll()");
		Iterable<Student> result = this.studentRepository.findAll();
		return result;
	}

	public void save(Student student) {
		this.studentRepository.save(student);
	}

	public Student findById(Long id) {

		return this.studentRepository.findOne(id);
	}

	public Student deleteById(Long id) {
		// Find the correct student
		Student result = this.findById(id);
		//Delete the student
		this.studentRepository.delete(id);
		return result;
	}
}
