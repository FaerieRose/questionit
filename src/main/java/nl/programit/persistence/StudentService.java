package nl.programit.persistence;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.Student;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Iterable<Student> findAll() {
//		System.out.println("================================= public Iterable<Student> findAll()");
		Iterable<Student> result = this.studentRepository.findAll();
		return result;
	}

	public void save(Student student) {
		this.studentRepository.save(student);
	}

	public Student findById(long id) {
//		System.out.println("================================= public Iterable<Student> findById()");
		return this.studentRepository.findOne(id);
	}

	public Student findByIdInclAttempts(long id) {
//		System.out.println("================================= public Iterable<Student> findByIdInclAttempts()");
		Student s = this.studentRepository.findOne(id);
		if (s != null) Hibernate.initialize(s.getAttempts());
		return s;
	}
	
	public Student deleteById(long id) {
		// Find the correct student
		Student result = this.findById(id);
		// Delete the student
		this.studentRepository.delete(id);
		return result;
	}
}
