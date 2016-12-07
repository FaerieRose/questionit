package nl.programit.domain.models;

import org.junit.Test;
//import org.junit.Test;
import org.junit.runner.RunWith;
//import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nl.programit.QuestionitApplication;

/**
 * A list of MeanBean test that are done on the classes in the domain.models 
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

//	@Test							
//	public void testInstructorModelName() {
//		new BeanTester().testBean(InstructorModelName.class);
//	}
	
//	@Test							
//	public void testQuestionModelBasic() {
//		new BeanTester().testBean(QuestionModelBasic.class);
//	}
	
//	@Test							
//	public void testQuestionModelExam() {
//		new BeanTester().testBean(QuestionModelAttempt.class);
//	}
	
//	@Test							
//	public void testQuestionModelExamReview() {
//		new BeanTester().testBean(QuestionModelExamReview.class);
//	}
	
//	@Test							
//	public void testQuestionModelName() {
//		new BeanTester().testBean(QuestionModelName.class);
//	}
	
	@Test
	public void testFoo(){
		
	}
	
}