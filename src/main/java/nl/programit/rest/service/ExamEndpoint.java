package nl.programit.rest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

import nl.programit.domain.AnswerList;
import nl.programit.domain.Question;
import nl.programit.domain.TestTemplate;
import nl.programit.domain.Student;
import nl.programit.domain.models.QuestionModelExam;
import nl.programit.domain.models.TimesModelExam;
import nl.programit.domain.Exam;
import nl.programit.persistence.AnswerListService;
import nl.programit.persistence.ExamService;
import nl.programit.persistence.TestTemplateService;
import nl.programit.persistence.QuestionService;
import nl.programit.persistence.StudentService;

/**
 * Endpoint for serveral ReST services to GET, POST and DELETE Exams
 * 
 * @author FaerieRose S.Martens
 * @version v0.1
 * @since 2016-11-29
 */
@Path("exams")
public class ExamEndpoint {

	@Autowired
	ExamService examService;
	
	@Autowired
	TestTemplateService testTemplateService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	AnswerListService answerListService;
	
	@Autowired
	QuestionService questionService;
	
	/**
	 * GET all Exams
	 * Path = 'api/exams'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getExamsAll() {
		Iterable<Exam> result = this.examService.findAll();
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * GET one Exam with id
	 * Path = 'api/exams'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getExamById(@PathParam("id") Long id) {
		Exam result = this.examService.findById(id);
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * GET TestTemplate of one Exam with id
	 * Path = 'api/exams'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/questionLists")
	public Response getQuestionList(@PathParam("id") Long id) {
		TestTemplate result = this.examService.findById(id).getQuestionList();
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * GET givenAnswers of one Exam with id
	 * Path = 'api/exams'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/givenAnswers")
	public Response getGivenAnswers(@PathParam("id") Long id) {
		List<AnswerList> result = this.examService.findById(id).getGivenAnswers();
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * GET markedQuestions of one Exam with id
	 * Path = 'api/exams'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/markedQuestions")
	public Response getMarkedQuestions(@PathParam("id") Long id) {
		List<Integer> result = this.examService.findById(id).getMarkedQuestions();
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * GET invalid answered questions where true/false count is not the same as in the correct answers. 
	 * for a review at the end of the exam a list is requested of the invalid answered questions
	 * Path = 'api/exams'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) or 406 "Not Acceptable when number is not corresponding 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/review")	
	public Response getInvalidAnsweredQuestions(@PathParam("id") Long id) {
		if (this.examService.findById(id) == null || this.examService.findById(id).getGivenAnswers() == null ||
				this.examService.findById(id).getQuestionList() == null || 
				this.examService.findById(id).getQuestionList().getQuestions() == null ){
			return Response.noContent().build();
		}
		List<AnswerList> givenAnswers = this.examService.findById(id).getGivenAnswers();
		List<AnswerList> correctAnswers = new ArrayList<>(); 
		TestTemplate testTemplate = this.examService.findById(id).getQuestionList();
		List<Question> listQuestions = testTemplate.getQuestions();
		for (Question correctQuestion : listQuestions){
			correctAnswers.add(correctQuestion.getCorrectAnswers());
		}
		if (givenAnswers.size() != correctAnswers.size()){
			return Response.notAcceptable(null).build();
		}
	
		List<Integer> result = new ArrayList<>();
		int counter = 1;
		int trueCountGiven = 0;
		int trueCountCorrect = 0;
		for (int k = 0; k < givenAnswers.size() ; k++) {
			trueCountGiven = 0;
			trueCountCorrect = 0;
			for (int i = 0; i < givenAnswers.get(k).getAnswers().size(); i++) {
			    if (givenAnswers.get(k).getAnswers().get(i) ) {
			        trueCountGiven++;
			    }
			}
		    for (int j = 0; j < correctAnswers.get(k).getAnswers().size(); j++) {
			    if (correctAnswers.get(k).getAnswers().get(j) ) {
			        trueCountCorrect++;	
			    }
			}
		    if (trueCountGiven != trueCountCorrect){
				result.add(counter);
			}
		    counter++;
		}
		return Response.ok(result).build();	
	}
	
	
	/**
	 * GET times of one Exam with id
	 * Path = 'api/exams'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/times")
	public Response getTimes(@PathParam("id") Long id) {
		if (this.examService.findById(id) == null){
			return Response.noContent().build();
		} else {
			TimesModelExam result = new TimesModelExam();
			result.setCreationDateTime(this.examService.findById(id).getQuestionList().getCreationDateTime());
			result.setEndDateTime(this.examService.findById(id).getEndDateTime());
			result.setExamTimeInMinutes(this.examService.findById(id).getQuestionList().getExamTimeInMinutes());
			result.setStartDateTime(this.examService.findById(id).getStartDateTime());
			result.setTimeToCompletInSeconds(this.examService.findById(id).getTimeToCompleteInSeconds());
			
			return Response.ok(result).build();
		}	
	}
	
	/**
	 * GET question of one Exam with id and number
	 * Path = 'api/exams'
	 * @return 200 + JSON if there is data, or 204 (noContent), or 406 "Not Acceptable when number is not corresponding 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/question/{nr}")
	public Response getQuestion(@PathParam("id") Long id, @PathParam("nr") Integer nr) {
		if (this.examService.findById(id) == null || this.examService.findById(id).getQuestionList() == null || 
				this.examService.findById(id).getQuestionList().getQuestions() == null){
			return Response.noContent().build();
		}
		if (nr < 1 || nr > this.examService.findById(id).getQuestionList().getQuestions().size()){
			return Response.notAcceptable(null).build();
		}
		Question result = this.examService.findById(id).getQuestionList().getQuestions().get(nr-1);
		if (result != null) {
			QuestionModelExam resultModelExam = new QuestionModelExam(result);
			return Response.ok(resultModelExam).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * GET answerList from givenAnswers corresponding with number relating to index
	 *  ( number = index + 1 )
	 * Path = 'api/exams'
	 * @return 200 + JSON if there is data, or 204 (noContent), or 406 "Not Acceptable when number is not corresponding 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/answerlist/{nr}")	
	public Response getAnswerList(@PathParam("id") Long id, @PathParam("nr") Integer nr) {
		if (this.examService.findById(id) == null || this.examService.findById(id).getGivenAnswers() == null){
			return Response.noContent().build();
		}
		if (nr < 1 || nr > this.examService.findById(id).getGivenAnswers().size()){
			return Response.notAcceptable(null).build();
		}
		AnswerList result = this.examService.findById(id).getGivenAnswers().get(nr -1); 
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * GET answer of question from one Exam with id and number
	 * Path = 'api/exams'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent), or 406 "Not Acceptable when number is not corresponding 
	 * @author S.Martens 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/question/{nr}/correctAnswers")
	public Response getCorrectAnswerOfQuestion(@PathParam("id") Long id, @PathParam("nr") Integer nr) {
		if (this.examService.findById(id) == null || this.examService.findById(id).getQuestionList() == null || 
				this.examService.findById(id).getQuestionList().getQuestions() == null){
			return Response.noContent().build();
		}
		if (nr < 1 || nr > this.examService.findById(id).getQuestionList().getQuestions().size()){
			return Response.notAcceptable(null).build();
		}
		AnswerList result = this.examService.findById(id).getQuestionList().getQuestions().get(nr-1).getCorrectAnswers();
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * POST one Exam. If no id included, a new entry is created, otherwise an existing one is overwritten.
	 * Creator, correctAnswers & givenAnswers may not be included in JSON<br>
	 * Path = 'api/exams'
	 * @return 204 + JSON if there is data, otherwise 404, or 406 "Not Acceptable when id is not active 
	 * @author S.Martens
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("start/{questionlist_id}/{student_id}")
	public Response postNewExam(@PathParam("questionlist_id") Long ql_id, @PathParam("student_id") Long s_id) {
		if (this.testTemplateService.findById(ql_id) == null || this.studentService.findById(s_id) == null){
			return Response.noContent().build();
		}
		else {
			Exam exam = new Exam();
			exam.setQuestionList(this.testTemplateService.findById(ql_id));

			for (int i = 0; i < exam.getQuestionList().getQuestions().size(); i++){
				AnswerList answerList = new AnswerList();
				this.answerListService.save(answerList);
				exam.getGivenAnswers().add(answerList);
			}			
			this.examService.save(exam);
			Student student = this.studentService.findById(s_id);	
			student.getExams().add(exam);	
			this.studentService.save(student);

			return Response.accepted(exam.getId()).build();
		}	
	}
	
	/**
	 * POST to end one Exam.
	 * Creator, correctAnswers & givenAnswers may not be included in JSON<br>
	 * Path = 'api/exams'
	 * @return 204 + JSON if there is data, otherwise 404, or 406 "Not Acceptable when id is not active 
	 * @author S.Martens
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/end")		
	public Response postEndExam(@PathParam("id") Long id) {
		if (this.examService.findById(id) == null){
			return Response.noContent().build();
		}
		else if (this.examService.findById(id).getEndDateTime() != null){
			return Response.notAcceptable(null).build();
		}
		else {
			Exam exam = this.examService.findById(id);
			exam.setEndDateTime(new Date());
			exam.setTimeToCompleteInSeconds(exam.getTimeToCompleteInSeconds() + 10);
			
			List<AnswerList> givenAnswers = this.examService.findById(id).getGivenAnswers(); 
			TestTemplate testTemplate = this.examService.findById(id).getQuestionList();
			List<Question> listQuestions = testTemplate.getQuestions();
			for (int i = 0; i < givenAnswers.size(); i++ ){
				listQuestions.get(i).getGivenAnswers().add(givenAnswers.get(i));
				this.questionService.save(listQuestions.get(i));
			}
			
			this.examService.save(exam);
			
			return Response.accepted(exam.getId()).build();
		}	
	}
	 
	/**
	 * PUT for one Exam for elapsed time a ping for increment of 10sec is given
	 * Path = 'api/exams'
	 * @return 204 + JSON if there is data, otherwise 404 
	 * @author S.Martens
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/ping")
	public Response putPing(@PathParam("id") Long id) {
		if (this.examService.findById(id) == null){
			return Response.noContent().build();
		} else {
			Exam exam = this.examService.findById(id);
			exam.setTimeToCompleteInSeconds(exam.getTimeToCompleteInSeconds() + 10);
			this.examService.save(exam);
			
			return Response.accepted(exam.getTimeToCompleteInSeconds()).build();
		}
	}
	
	/**
	 * PUT for one Exam for a marked number to add to marked question list
	 * Path = 'api/exams'
	 * @return 204 + JSON if there is data, otherwise 404,  or 406 "Not Acceptable when number is not allowed
	 * @author S.Martens
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/mark/{question_nr}")
	public Response putMark(@PathParam("id") Long id, @PathParam("question_nr") Integer nr) {
		if (this.examService.findById(id) == null || this.examService.findById(id).getQuestionList() == null || 
				this.examService.findById(id).getQuestionList().getQuestions() == null || this.examService.findById(id).getMarkedQuestions() == null){
			return Response.noContent().build();
		}
		if (nr < 1 || nr > this.examService.findById(id).getQuestionList().getQuestions().size()){
			return Response.notAcceptable(null).build();
		} else {
			Exam exam = this.examService.findById(id);
			if (exam.getMarkedQuestions().contains(nr)){
				return Response.noContent().build();
			} else {
			exam.getMarkedQuestions().add(nr);
			this.examService.save(exam);
			
			return Response.accepted(exam.getMarkedQuestions()).build();
			}
		}
	}
	
	/**
	 * PUT for one Exam to un-marked a number to remove from the marked question list
	 * Path = 'api/exams'
	 * @return 204 + JSON if there is data, otherwise 404,  or 406 "Not Acceptable when number is not allowed
	 * @author S.Martens
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/unmark/{question_nr}")
	public Response putUnmark(@PathParam("id") Long id, @PathParam("question_nr") Integer nr) {
		if (this.examService.findById(id) == null || this.examService.findById(id).getQuestionList() == null || 
				this.examService.findById(id).getQuestionList().getQuestions() == null || this.examService.findById(id).getMarkedQuestions() == null){
			return Response.noContent().build();
		}
		if (nr < 1 || nr > this.examService.findById(id).getQuestionList().getQuestions().size()){
			return Response.notAcceptable(null).build();
		} 
		Exam exam = this.examService.findById(id);
		if (!exam.getMarkedQuestions().contains(nr)){
			return Response.noContent().build();
		} else {
			exam.getMarkedQuestions().remove(nr);
			this.examService.save(exam);
			
			return Response.accepted(exam.getMarkedQuestions()).build();
		}
	}
	
	/**
	 * PUT answerList from givenAnswers corresponding with number relating to index
	 *  ( number = index + 1 )
	 * Path = 'api/exams'
	 * @return 200 + JSON if there is data, or 204 (noContent), or 406 "Not Acceptable when number is not corresponding 
	 * @author S.Martens
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/answerlist/{nr}")
	public Response putAnswerList(@PathParam("id") Long id, @PathParam("nr") Integer nr, AnswerList answerListIn) {
		if (this.examService.findById(id) == null || this.examService.findById(id).getGivenAnswers() == null){
			return Response.noContent().build();
		}
		if (nr < 1 || nr > this.examService.findById(id).getGivenAnswers().size()){
			return Response.notAcceptable(null).build();
		}
		AnswerList answerListDb = this.examService.findById(id).getGivenAnswers().get(nr -1); 
		answerListDb.setAnswers(answerListIn.getAnswers());
		this.answerListService.save(answerListDb);
		
		return Response.ok(answerListDb).build();
	}
	
	/**
	 * POST one new Exam selecting a questionList
	 * Path = 'api/exams'
	 * @return 204 + JSON id exam data, otherwise 404 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postStartNewExam(Exam exam) {
		this.examService.save(exam);
		return Response.accepted(exam).build();
	}
}

