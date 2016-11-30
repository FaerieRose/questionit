package nl.programit.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.springframework.beans.factory.annotation.Autowired;

import nl.programit.domain.AnswerList;
import nl.programit.domain.Question;
import nl.programit.domain.QuestionList;
import nl.programit.domain.Student;
import nl.programit.domain.models.TimesModelExam;
import nl.programit.domain.Exam;
import nl.programit.persistence.AnswerListService;
import nl.programit.persistence.ExamService;
import nl.programit.persistence.QuestionListService;
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
	QuestionListService questionListService;
	
	@Autowired
	StudentService studentService;
	
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
	 * GET QuestionList of one Exam with id
	 * Path = 'api/exams'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/questionLists")
	public Response getQuestionList(@PathParam("id") Long id) {
		QuestionList result = this.examService.findById(id).getQuestionList();
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
		if (this.questionListService.findById(ql_id) == null || this.studentService.findById(s_id) == null){
			return Response.notAcceptable(null).build();
		}
		else{
			Exam exam = new Exam();
			exam.setQuestionList(this.questionListService.findById(ql_id));
			this.examService.save(exam);
			Student student = this.studentService.findById(s_id);	
			student.getExams().add(exam);	
			this.studentService.save(student);
			
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

