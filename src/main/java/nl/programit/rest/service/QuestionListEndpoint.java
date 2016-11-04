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


import nl.programit.domain.Question;
import nl.programit.domain.QuestionList;
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
	
	/**
	 * GET all Questions
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
	 * GET one QuestionList with specified id
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
	 * POST one QuestionList. If no id included, a new entry
	 * is created, otherwise an existing one is overwritten
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
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/question")
	public Response addQuestionToQuestionList(@PathParam("id") Long id, Question question) {
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
	
}
