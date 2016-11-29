package nl.programit.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.programit.domain.StudentClass;
import nl.programit.domain.models.StudentClassModelBasic;
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
//        Iterable<StudentClass> studentClasses = this.studentClassService.findAll();        
//        return Response.ok(studentClasses).build();
//    }
		Iterable<StudentClass> studentClasses = this.studentClassService.findAll();
		if (studentClasses != null) {
			List<StudentClassModelBasic> result = new ArrayList<>();
			for (StudentClass studentClass: studentClasses) {
				result.add(this.studentClassService.convertToModelBasic(studentClass));
			}
			return Response.ok(result).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getStudentClassById(@PathParam("id") Long id){
    	StudentClass studentClass = this.studentClassService.findById(id);
    	return Response.ok(studentClass).build();
    	
    }
}
