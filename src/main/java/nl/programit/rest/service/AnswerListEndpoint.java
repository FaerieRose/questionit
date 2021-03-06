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
import nl.programit.persistence.AnswerListService;

/**
 * Endpoint for serveral ReST services to GET, POST and DELETE AnswerLists
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-21
 */
@Path("answers")
public class AnswerListEndpoint {

	@Autowired
	AnswerListService answerListService;
	
	/**
	 * GET one AnswerList with specified id
	 * Path = 'api/answers/{id}'
	 * @return 200 + JSON if there is data, otherwise 404 (Not Found)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getAnserListById(@PathParam("id") Long id) {
		AnswerList answerList = this.answerListService.findById(id);
		if (answerList != null) {
			return Response.ok(answerList).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	
	/**
	 * POST an AnswerList. If it does not contain existing AnswerList id, a new one AnswerList is created, otherwise the existing one is overwritten<br>
	 * Path = 'api/answers'
	 * @param answerList 
	 * @return 200 + ID if post succeeded, otherwise 500
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postAnswerList(AnswerList answerList) {
		AnswerList result = this.answerListService.save(answerList);
		if (result != null) {
			return Response.ok(Long.toString(result.getId())).build();
		} 
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();		
	}

	/**
	 * DELETE one AnswerList with specified id
	 * Path = 'api/answers/{id}'
	 * @return 200 if there is data, otherwise 404 (Not Found)
	 */
	@DELETE
	@Path("{id}")
	public Response deleteAnswerList(@PathParam("id") Long id) {
		if (this.answerListService.deleteAnswerListById(id)) {
			return Response.ok().build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
}
