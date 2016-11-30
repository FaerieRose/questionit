package nl.programit.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

import nl.programit.domain.Exam;
import nl.programit.persistence.ExamService;

/**
 * Endpoint for serveral ReST services to GET, POST and DELETE Exams
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-08
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
	 * GET all Exams
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
