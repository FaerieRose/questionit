package nl.programit.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nl.programit.QuestionitApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QuestionitApplication.class)
@TestPropertySource(locations="classpath:application-unittest.properties")
public class MeanBeanTest {

//	@Test
//	public void testAnswerList() {
//		new BeanTester().testBean(AnswerList.class);
//	}
	
//	@Test
//	public void testEnumExams() {
//		new BeanTester().testBean(EnumExams.class);
//	}
	
//	@Test
//	public void testEnumLanguages() {
//		new BeanTester().testBean(EnumLanguages.class);
//	}
	
	@Test
	public void testExam() {
		new BeanTester().testBean(Exam.class);
	}
	
	@Test
	public void testInstructor() {
		new BeanTester().testBean(Instructor.class);
	}
	
	@Test
	public void testPerson() {
		new BeanTester().testBean(Person.class);
	}
	
//	@Test
//	public void testQuestion() {
//		new BeanTester().testBean(Question.class);
//	}
	
	@Test
	public void testQuestionList() {
		new BeanTester().testBean(QuestionList.class);
	}
	
//	@Test
//	public void testQuestionTemplate() {
//		new BeanTester().testBean(QuestionTemplate.class);
//	}
	
	@Test
	public void testStudent() {
		new BeanTester().testBean(Student.class);
	}
	
	@Test
	public void testStudentClass() {
		new BeanTester().testBean(StudentClass.class);
	}
	
}
