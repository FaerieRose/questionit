package nl.programit.rest.service;

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
import nl.programit.domain.Instructor;
import nl.programit.domain.Question;
import nl.programit.domain.models.QuestionModelExam;
import nl.programit.persistence.AnswerListService;
import nl.programit.persistence.InstructorService;
import nl.programit.persistence.QuestionService;

/**
 * Endpoint for serveral ReST services to GET, POST and DELETE Questions
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-03
 */
@Path("questions")
public class QuestionEndpoint {
	
	@Autowired
	QuestionService questionService;

	@Autowired
	InstructorService instructorService;

	@Autowired
	AnswerListService answerListService;
	
	/**
	 * GET all Questions
	 * Path = 'api/questions'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestionsAll() {
		Iterable<Question> result = this.questionService.findAll();
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}

	/**
	 * GET one Question with specified id
	 * Path = 'api/questions/{id}'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getQuestionById(@PathParam("id") Long id) {
		Question result = this.questionService.findById(id);
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}

	/**
	 * GET one Question with specified id
	 * Path = 'api/questions/{id}'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("exam/{id}")
	public Response getQuestionForExam(@PathParam("id") Long id) {
		Question question = this.questionService.findById(id);
		if (question != null) {
			QuestionModelExam result = new QuestionModelExam(question);
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}

	/**
	 * POST one Question. If no id included, a new entry is created, otherwise an existing one is overwritten.
	 * Creator, correctAnswers & givenAnswers may not be included in JSON<br>
	 * Path = 'api/questions'
	 * @return 204 + JSON if there is data, otherwise 404 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postNewQuestion(Question question) {
		this.questionService.save(question);
//		return Response.accepted(question).build();
		System.out.println("====== POST: Question: " + question.getName());
		return Response.accepted(question).build();
	}	
	
	/**
	 * POST an existing Instructor id. If the Instructor exists it is attached to 
	 * the Question with the specified id.
	 * Path = 'api/questions/{id}/instructor/{instructor_id}'
	 * @return 200 + JSON if there is data, otherwise 204 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/instructor/{instructor_id}")
	public Response addExistingInstructorToQuestion(@PathParam("id") Long id, @PathParam("instructor_id") Long instructor_id) {
		Question question = this.questionService.findById(id);
		if (question != null) {
			Instructor instructor = this.instructorService.findById(instructor_id);
			if (instructor != null) {
				question.setCreator(instructor);
				this.questionService.save(question);
		        return Response.accepted(instructor).build();
			}
		}
		return Response.noContent().build();
	}	
	
	/**
	 * POST an existing givenAnswerList id. If the AnswerList exists it is attached to 
	 * the Question with the specified id.
	 * Path = 'api/questions/{id}/given-answers/{answerlist_id}'
	 * @return 200 + JSON if there is data, otherwise 204 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/given-answers/{answerlist_id}")
	public Response addExistingGivenAnswerListToQuestion(@PathParam("id") Long id, @PathParam("answerlist_id") Long answerlist_id) {
		System.out.println("called: addExistingGivenAnswerListToQuestion with id " + id);
		Question question = this.questionService.findById(id);
		if (question != null) {
			AnswerList answerlist = this.answerListService.findById(answerlist_id);
			if (answerlist != null) {
				question.addGivenAnswerList(answerlist);
				this.questionService.save(question);
		        return Response.accepted(answerlist).build();
			}
		}
		return Response.noContent().build();
	}	
	
	/**
	 * POST a new correctAnswerList. The correctAnswerList is created and attached to the Question with the specified id.<br>
	 * Path = 'api/questions/{id}/correct-answers'
	 * @return 200 + JSON if there is data, otherwise 204 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/correct-answers")
	public Response addNewCorrectAnswerListToQuestion(@PathParam("id") Long id, AnswerList answerList) {
		Question question = this.questionService.findById(id);
		if (question != null) {
			this.answerListService.save(answerList);
			question.setCorrectAnswers(answerList);
			this.questionService.save(question);
	        return Response.accepted(answerList).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * DELETE one Question with specified id
	 * Path = 'api/questions/{id}'
	 * @return 200 + JSON if there is data, otherwise 404 
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response removeQuestion(@PathParam("id") Long id) {
		Question question = this.questionService.findById(id);
		if (question != null) {
			Question result = this.questionService.deleteById(id);
			return Response.ok(result).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	
}
