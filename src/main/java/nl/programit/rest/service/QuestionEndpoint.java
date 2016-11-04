package nl.programit.rest.service;

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
import org.springframework.stereotype.Component;

import nl.programit.domain.Question;
import nl.programit.persistence.QuestionService;

/**
 * 
 * 
 * @author FaerieRose
 * @version v0.1
 */
@Path("questions")
public class QuestionEndpoint {
	
	@Autowired
	QuestionService questionService;

	/**
	 * GET all Questions
	 * Path = 'api/questions'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listQuestions() {
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
	public Response oneQuestions(@PathParam("id") Long id) {
		Question result = this.questionService.findById(id);
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}

	/**
	 * POST one Question with specified id
	 * Path = 'api/questions/{id}'
	 * @return 200 + JSON if there is data, otherwise 404 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(Question question) {
		this.questionService.save(question);
		return Response.accepted(question).build();
	}	

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
