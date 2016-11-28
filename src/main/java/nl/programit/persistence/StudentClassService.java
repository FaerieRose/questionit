package nl.programit.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.StudentClass;

@Service
@Transactional
public class StudentClassService {

	@Autowired
	private StudentClassRepository studentClassRepository;
	
	public Iterable<StudentClass> findAll() {
		Iterable<StudentClass> result = this.studentClassRepository.findAll();
		return result;
	}
	
	public void save(StudentClass studentClass) {
		this.studentClassRepository.save(studentClass);
	}

	public StudentClass findById(Long id) {
		return this.studentClassRepository.findOne(id);

	}
	
	
}
