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

import nl.programit.domain.QuestionList;
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
}
