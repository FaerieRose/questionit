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


import nl.programit.domain.Instructor;
import nl.programit.domain.Question;
import nl.programit.domain.QuestionList;
import nl.programit.persistence.InstructorService;
import nl.programit.persistence.QuestionService;
import nl.programit.persistence.QuestionListService;

/**
 * Endpoint for serveral ReST services to GET, POST and DELETE QuestionList
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-04
 */
@Path("questionlists")
public class QuestionListEndpoint {

	@Autowired
	QuestionListService questionListService;

	@Autowired
	QuestionService questionService;

	@Autowired
	InstructorService instructorService;
	
	/**
	 * GET all Questions<br>
	 * Path = 'api/questionlists'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listQuestionLists() {
		Iterable<QuestionList> result = this.questionListService.findAll();
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}	
	
	/**
	 * GET one QuestionList with specified id<br>
	 * Path = 'api/questionlists/{id}'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")	
	public Response oneQuestionList(@PathParam("id") Long id) {
		QuestionList result = this.questionListService.findById(id);
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}

	/**
	 * POST one QuestionList. If no id included, a new entry is created, otherwise an existing one
	 * is overwritten. Questions and Creator must be excluded from JSON<br>
	 * Path = 'api/questionlists'
	 * @return 200 + JSON if there is data, otherwise 404 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postQuestionList(QuestionList questionList) {
		this.questionListService.save(questionList);
		return Response.accepted(questionList).build();
	}
	
	/**
	 * POST a new Question. The Question is created and attached to the QuestionList with the specified id.<br>
	 * Path = 'api/questionlists/{id}/question'
	 * @return 200 + JSON if there is data, otherwise 204 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/question")
	public Response addNewQuestionToQuestionList(@PathParam("id") Long id, Question question) {
		QuestionList questionList = this.questionListService.findById(id);
		if (questionList != null) {
			this.questionService.save(question);
			questionList.addQuestion(question);
			this.questionListService.save(questionList);
	        return Response.accepted(question).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * POST an existing Instructor id. If the Instructor exists it is attached to the QuestionList with the specified id.<br>
	 * Path = 'api/questionlists/{id}/instructor/{instructor_id}'
	 * @return 200 + JSON if there is data, otherwise 204 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/instructor/{instructor_id}")
	public Response addExistingInstructorToQuestionList(@PathParam("id") Long id, @PathParam("instructor_id") Long instructor_id) {
		QuestionList questionList = this.questionListService.findById(id);
		if (questionList != null) {
			Instructor instructor = this.instructorService.findById(instructor_id);
			if (instructor != null) {
				questionList.setCreator(instructor);
				this.questionListService.save(questionList);
		        return Response.accepted(instructor).build();
			}
		}
		return Response.noContent().build();
	}
	
	/**
	 * POST a existing Question id. If the Question exists it is attached to the QuestionList with the specified id.<br>
	 * Path = 'api/questionlists/{id}/question/{question_id}'
	 * @return 200 + JSON if there is data, otherwise 204 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/question/{question_id}")
	public Response addExistingQuestionToQuestionList(@PathParam("id") Long id, @PathParam("question_id") Long question_id) {
		QuestionList questionList = this.questionListService.findById(id);
		if (questionList != null) {
			Question question = this.questionService.findById(question_id);
			if (question != null) {
				questionList.addQuestion(question);
				this.questionListService.save(questionList);
		        return Response.accepted(question).build();
			}
		}
		return Response.noContent().build();
	}

}
