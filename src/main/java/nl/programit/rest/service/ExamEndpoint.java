package nl.programit.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import nl.programit.domain.models.TimesModelExam;
import nl.programit.domain.Exam;
import nl.programit.persistence.AnswerListService;
import nl.programit.persistence.ExamService;
import nl.programit.persistence.QuestionService;

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
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/markedQuestions")
	public Response getMarkedQuestions(@PathParam("id") Long id) {
		List<Boolean> result = this.examService.findById(id).getMarkedQuestions();
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
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/question/{nr}")
	public Response getQuestion(@PathParam("id") Long id, @PathParam("nr") Integer nr) {
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
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/question/{nr}/correctAnswers")
	public Response getCorrectAnswerOfQuestion(@PathParam("id") Long id, @PathParam("nr") Integer nr) {
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
	 * @return 204 + JSON if there is data, otherwise 404 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postNewExam(Exam exam) {
		this.examService.save(exam);
		return Response.accepted(exam).build();
	}
}

