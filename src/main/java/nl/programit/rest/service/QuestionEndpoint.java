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

	// ---------------------------------------------------
	//	GET	All Questions
	//  Path = 'api/questions'
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listQuestions() {
		Iterable<Question> result = this.questionService.findAll();
		return Response.ok(result).build();
	}

	// ---------------------------------------------------
	//	GET	One Questions
	//  Path = 'api/questions/{id}'
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response oneQuestions(@PathParam("id") Long id) {
		Question result = this.questionService.findById(id);
		return Response.ok(result).build();
	}

	// ---------------------------------------------------
	//	POST Question
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(Question question) {
		this.questionService.save(question);
		return Response.accepted(question).build();
	}	
	
	
}
