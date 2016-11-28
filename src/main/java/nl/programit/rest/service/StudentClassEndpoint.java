package nl.programit.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.programit.domain.StudentClass;
import nl.programit.persistence.StudentClassService;

@Path("/studentclasses")
@Component
public class StudentClassEndpoint {
    @Autowired
    private StudentClassService studentClassService;
    

    // response for Get request for the complete Klant class
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        Iterable<StudentClass> studentClasses = this.studentClassService.findAll();        
        return Response.ok(studentClasses).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getStudentClassById(@PathParam("id") Long id){
    	StudentClass studentClass = this.studentClassService.findById(id);
    	return Response.ok(studentClass).build();
    	
    }
}
