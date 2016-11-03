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

@Path("questions")
public class QuestionEndpoint {
	
	@Autowired
	QuestionService questionService;

	

	//	GET	Questions
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listQuestions() {
		Iterable<Question> result = this.questionService.findAll();
		return Response.ok(result).build();
	}


	//	POST Question
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(Question question) {
		this.questionService.save(question);
		return Response.accepted(question).build();
	}	
	
	
}
