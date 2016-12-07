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


import nl.programit.domain.Instructor;
import nl.programit.domain.Question;
import nl.programit.domain.TestTemplate;
import nl.programit.persistence.InstructorService;
import nl.programit.persistence.QuestionService;
import nl.programit.persistence.TestTemplateService;

/**
 * Endpoint for serveral ReST services to GET, POST and DELETE TestTemplate
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-04
 */
@Path("testtemplates")
public class TestTemplateEndpoint {

	@Autowired
	TestTemplateService testTemplateService;

	@Autowired
	QuestionService questionService;

	@Autowired
	InstructorService instructorService;
	
	/**
	 * GET all Questions<br>
	 * Path = 'api/testtemplates'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTestTemplateAll() {
		Iterable<TestTemplate> result = this.testTemplateService.findAll();
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}	
	
	/**
	 * GET one TestTemplate with specified id<br>
	 * Path = 'api/testtemplates/{id}'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")	
	public Response getTestTemplateById(@PathParam("id") Long id) {
		TestTemplate result = this.testTemplateService.findById(id);
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}

	/**
	 * POST one TestTemplate. If no id included, a new entry is created, otherwise an existing one
	 * is overwritten. Questions and Creator must be excluded from JSON<br>
	 * Path = 'api/testtemplates'
	 * @return 200 + JSON if there is data, otherwise 404 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postTestTemplate(TestTemplate testTemplate) {
		this.testTemplateService.save(testTemplate);
		return Response.accepted(testTemplate).build();
	}
	
	/**
	 * POST a new Question. The Question is created and attached to the TestTemplate with the specified id.<br>
	 * Path = 'api/testtemplates/{id}/question'
	 * @return 200 + JSON if there is data, otherwise 204 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/question")
	public Response addNewQuestionToTestTemplate(@PathParam("id") Long id, Question question) {
		TestTemplate testTemplate = this.testTemplateService.findById(id);
		if (testTemplate != null) {
			this.questionService.save(question);
			testTemplate.addQuestion(question);
			this.testTemplateService.save(testTemplate);
	        return Response.accepted(question).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * POST an existing Instructor id. If the Instructor exists it is attached to the TestTemplate with the specified id.<br>
	 * Path = 'api/testtemplates/{id}/instructor/{instructor_id}'
	 * @return 200 + JSON if there is data, otherwise 204 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/instructor/{instructor_id}")
	public Response addExistingInstructorToTestTemplate(@PathParam("id") Long id, @PathParam("instructor_id") Long instructor_id) {
		TestTemplate testTemplate = this.testTemplateService.findById(id);
		if (testTemplate != null) {
			Instructor instructor = this.instructorService.findById(instructor_id);
			if (instructor != null) {
				testTemplate.setCreator(instructor);
				this.testTemplateService.save(testTemplate);
		        return Response.accepted(instructor).build();
			}
		}
		return Response.noContent().build();
	}
	
	/**
	 * POST a existing Question id. If the Question exists it is attached to the TestTemplate with the specified id.<br>
	 * Path = 'api/testtemplates/{id}/question/{question_id}'
	 * @return 200 + JSON if there is data, otherwise 204 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/question/{question_id}")
	public Response addExistingQuestionToTestTemplate(@PathParam("id") Long id, @PathParam("question_id") Long question_id) {
		TestTemplate testTemplate = this.testTemplateService.findById(id);
		if (testTemplate != null) {
			Question question = this.questionService.findById(question_id);
			if (question != null) {
				testTemplate.addQuestion(question);
				this.testTemplateService.save(testTemplate);
		        return Response.accepted(question).build();
			}
		}
		return Response.noContent().build();
	}

}