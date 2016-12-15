package nl.programit.rest.service;

import java.util.ArrayList;
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
import org.springframework.stereotype.Component;

import nl.programit.domain.Instructor;
import nl.programit.domain.Student;
import nl.programit.domain.models.InstructorModelBasic;
import nl.programit.persistence.InstructorService;


@Path("instructors")
@Component
public class InstructorEndpoint {
	@Autowired
	private InstructorService instructorService;

	// response for Get request for the complete Klant class
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		Iterable<Instructor> instructors = this.instructorService.findAll();
		if (instructors != null) {
			List<InstructorModelBasic> result = new ArrayList<>();
			for (Instructor instructor: instructors) {
				result.add(this.instructorService.convertToModelBasic(instructor));
			}
			return Response.ok(result).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getInstructorById(@PathParam("id") Long id) {
		Instructor instructor = this.instructorService.findById(id);
		if (instructor != null) {
			return Response.ok(this.instructorService.convertToModelBasic(instructor)).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// @Path("api/instructors")
	public Response postInstuctor(Instructor instructor) {
		this.instructorService.save(instructor);
		return Response.accepted(instructor).build();
	}

	/**
	 * DELETE one Instructor with specified id Path = 'api/instructors/{id}'
	 * 
	 * @return 200 if there was data, otherwise 404
	 */

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/removeinstructor/{instructor_id}")
	public Response removeStudent(@PathParam("instructor_id") Long instructorId) {
		Instructor instructor = this.instructorService.findById(instructorId);
		if (instructor != null) {
			this.instructorService.deleteById(instructorId);
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
