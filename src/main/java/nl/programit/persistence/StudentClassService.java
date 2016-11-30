package nl.programit.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.StudentClass;
import nl.programit.domain.models.StudentClassModelBasic;

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
	public StudentClassModelBasic convertToModelBasic(StudentClass studentClass) {
		StudentClassModelBasic result = new StudentClassModelBasic(studentClass);
		return result;
	}

	public StudentClass deleteById(Long id) {
		StudentClass result = this.findById(id);
		this.studentClassRepository.delete(id);
		return result;
		
	}
	
	
}
