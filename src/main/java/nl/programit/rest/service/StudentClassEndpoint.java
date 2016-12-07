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
import nl.programit.domain.StudentClass;
import nl.programit.domain.models.StudentClassModelBasic;
import nl.programit.persistence.InstructorService;
import nl.programit.persistence.StudentClassService;
import nl.programit.persistence.StudentService;


@Path("studentclasses")
@Component
public class StudentClassEndpoint {
    @Autowired
    private StudentClassService studentClassService;
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private InstructorService instructorService;

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
	public Response getStudentClassById(@PathParam("id") Long id) {
    	StudentClass studentClass = this.studentClassService.findById(id);
		if (studentClass != null) {
			return Response.ok(this.studentClassService.convertToModelBasic(studentClass)).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
    
//    @GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("{id}")
//	public Response getInstructorForClassById(@PathParam("id") Long id) {
//    	Instructor instructor = this.instructorService.findById(id);
//		if (instructor != null) {
//			return Response.ok(this.instructorService.convertToModelBasic(instructor)).build();
//		}
//		return Response.status(Status.NOT_FOUND).build();
//	}
    
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// @Path("api/instructors")
	public Response postStudentClass(StudentClass studentClass) {
		this.studentClassService.save(studentClass);
		return Response.accepted(studentClass).build();
	}

	/**
	 * DELETE one Instructor with specified id Path = 'api/instructors/{id}'
	 * 
	 * @return 200 if there was data, otherwise 404
	 */
	@DELETE
	@Path("{id}/instructor/{instructor_id}")
	public Response removeInstructor(@PathParam("id") Long studentClassId, @PathParam("instructor_id") Long instructorId, StudentClass studentClass2) {
		StudentClass studentClass = this.studentClassService.findById(studentClassId);
		Instructor instructor = this.instructorService.findById(instructorId);
		if (studentClass != null || instructor != null){
			studentClass.removeInstructor(instructor);
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@DELETE
	@Path("{id}/student/{student_id}")
	public Response removeStudentFromClass(@PathParam("id") Long studentClassId, @PathParam("student_id") Long studentId, StudentClass studentClass2) {
		System.out.println("in de removeStudentFromClass met studentClassId :" + studentClassId + " en studentId :" + studentId);
		StudentClass studentClass = this.studentClassService.findById(studentClassId);
		Student student = this.studentService.findById(studentId);
		if (studentClass != null || student != null){
			studentClass.removeStudent(student);
			this.studentClassService.save(studentClass);
			return Response.ok().build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/instructor/{instructor_id}")
	public Response postInstructorToStudentClass(@PathParam("id") Long studentClassId, @PathParam("instructor_id") Long instructorId, StudentClass studentClass2) {
		StudentClass studentClass = this.studentClassService.findById(studentClassId);
		Instructor instructor = this.instructorService.findById(instructorId);
		if (studentClass != null || instructor != null){
			studentClass.addInstructor(instructor);
			this.studentClassService.save(studentClass);
			return Response.ok().build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/student/{student_id}")
	public Response postStudentToStudentClass(@PathParam("id") Long studentClassId, @PathParam("student_id") Long studentId, StudentClass studentClass2) {
		StudentClass studentClass = this.studentClassService.findById(studentClassId);
		Student student = this.studentService.findById(studentId);
		if (studentClass != null || student != null){
			studentClass.addStudent(student);
			this.studentClassService.save(studentClass);
			return Response.ok().build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	
}






//@GET
//@Produces(MediaType.APPLICATION_JSON)
//@Path("{id}")
//public Response getStudentClassById(@PathParam("id") Long id){
//	StudentClass studentClass = this.studentClassService.findById(id);
//	return Response.ok(studentClass).build();
//	
//}