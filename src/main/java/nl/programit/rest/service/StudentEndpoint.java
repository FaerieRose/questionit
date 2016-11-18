package nl.programit.rest.service;

import java.util.ArrayList;
import java.util.List;

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



import nl.programit.domain.Student;	
import nl.programit.persistence.StudentService;


@Path("students")
public class StudentEndpoint {

    @Autowired
    private StudentService studentService;
    

    // response for Get request for the complete Klant class
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
		System.out.println("================================= public Response list()");
        Iterable<Student> students = this.studentService.findAll();
        System.out.println(students.toString());
        return Response.ok(students).build();
    }
    // response for POST request for complete Klant class
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postStudent(Student student) {
        this.studentService.save(student);
        return Response.accepted(student).build();
    }
    

    
}
