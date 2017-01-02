package nl.programit.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
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

import nl.programit.domain.Student;
import nl.programit.domain.models.StudentModelBasic;
import nl.programit.persistence.StudentService;

@Path("students")
@Component
public class StudentEndpoint {
	@Autowired
	private StudentService studentService;

	// response for Get request for the complete Klant class
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		//System.out.println("================================= public Response list()");
		Iterable<Student> students = this.studentService.findAll();
		List<StudentModelBasic> sbmList = new ArrayList<>();
		for (Student s : students) {
			sbmList.add(new StudentModelBasic(s));
		}
		return Response.ok(sbmList).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getStudentById(@PathParam("id") Long id) {
		Student student = this.studentService.findById(id);
		return Response.ok(this.studentService.convertToModelBasic(student)).build();
	}

	// response for POST request for complete Klant class
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postStudent(Student student) {
		this.studentService.save(student);
		return Response.accepted(student).build();
	}

	/**
	 * DELETE one Student with specified id Path = 'api/student/{id}'
	 * 
	 * @return 200 if there was data, otherwise 404
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/removestudent/{student_id}")
	public Response removeStudent(@PathParam("student_id") Long studentId) {
		Student student = this.studentService.findById(studentId);
		if (student != null) {
			this.studentService.deleteById(studentId);
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	


}
