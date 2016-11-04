package nl.programit.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.programit.domain.Instructor;
import nl.programit.domain.Student;
import nl.programit.persistence.InstructorService;

@Path("/instructors")
@Component
public class InstructorEndpoint {
    @Autowired
    private InstructorService instructorService;
    

    // response for Get request for the complete Klant class
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        Iterable<Instructor> instructors = this.instructorService.findAll();        
        return Response.ok(instructors).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postStudent(Instructor instructor) {
        this.instructorService.save(instructor);
        return Response.accepted(instructor).build();
    }



}
