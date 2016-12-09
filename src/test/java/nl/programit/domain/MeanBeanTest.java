package nl.programit.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nl.programit.QuestionitApplication;

/**
 * A list of MeanBean test that are done on the classes in the domain 
 * to test the constructors getter and setters with bean dependency 
 * 
 * @author S.Martens
 * @version v0.1
 * @since 2016-11-29
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = QuestionitApplication.class)
@TestPropertySource(locations="classpath:application-unittest.properties")
public class MeanBeanTest {

//	@Test							// get(i) String can not cast to Boolean
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
		new BeanTester().testBean(Attempt.class);
	}
	
	@Test
	public void testInstructor() {
		new BeanTester().testBean(Instructor.class);
	}
	
	@Test
	public void testPerson() {
		new BeanTester().testBean(Person.class);
	}
	
//	@Test							// get(i) String can not cast to Boolean
//	public void testQuestion() {
//		new BeanTester().testBean(Question.class);
//	}
	
	@Test
	public void testQuestionList() {
		new BeanTester().testBean(TestTemplate.class);
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
