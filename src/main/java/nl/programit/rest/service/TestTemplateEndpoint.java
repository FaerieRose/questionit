package nl.programit.rest.service;
import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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
import nl.programit.domain.models.TestTemplateModelBasic;
import nl.programit.persistence.InstructorService;
import nl.programit.persistence.QuestionService;
import nl.programit.persistence.TestTemplateService;

/**
 * Endpoint for several ReST services to GET, POST and DELETE TestTemplate
 * 
 * @author FaerieRose , S.Martens, Dave Schellekens
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
	
//	/**
//	 * GET all TestTemplate
//	 * Path = 'api/testtemplates'
//	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
//	 * @author S.Martens
//	 */
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getTestTemplateAll() {
//		Iterable<TestTemplate> result = this.testTemplateService.findAll();
//		if (result == null){
//			return Response.noContent().build();
//		} else {
//			List<TestTemplateModelBasic> templateList = new ArrayList<>();
//			for (TestTemplate testTemplate : result){
//				templateList.add( new TestTemplateModelBasic(testTemplate));
//			}
//			return Response.ok(templateList).build();
//		}
//	}	
	
//	/**
//	 * GET one TestTemplate with specified id
//	 * Path = 'api/testtemplates/{id}'
//	 * @return 200 + JSON if there is data, otherwise 204 (noContent)
//	 * @author S.Martens
//	 */
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("{id}")	
//	public Response getTestTemplateById(@PathParam("id") Long id) {
//		TestTemplate result = this.testTemplateService.findById(id);
//		if (result == null) {
//			return Response.noContent().build();
//		} else {
//			TestTemplateModelBasic template = new TestTemplateModelBasic(result);
//			return Response.ok(template).build();
//		}
//	}

	/**
	 * GET the meta data (id, nr of questions, allowed time, etc) of all TestTemplates in db<br>
	 * Path = 'api/testtemplates/basic'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent)
	 * @author Bas
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("basic")		
	public Response getTestTemplatesBasic() {
		ArrayList<TestTemplateModelBasic> ttmbList = new ArrayList<TestTemplateModelBasic>(); 
		Iterable<TestTemplate> result = this.testTemplateService.findAll();
		if (result != null) {
			for(TestTemplate tt: result) {
				ttmbList.add(testTemplateService.convertToTestTemplateModelBasic(tt));
			}
			return Response.ok(ttmbList).build();
		} else {
			return Response.noContent().build();
		}
	}

	/**
	 * GET the meta data (id, nr of questions, allowed time) of the TestTemplate with specified id<br>
	 * Path = 'api/testtemplates/{id}/meta'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent)
	 * @author Bas
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/basic")	
	public Response getTestTemplateMetaById(@PathParam("id") Long id) {
		TestTemplate result = this.testTemplateService.findById(id);
		if (result != null) {
			return Response.ok(testTemplateService.convertToTestTemplateModelBasic(result)).build();
		} else {
			return Response.noContent().build();
		}
	}

// made obsolete by putTestTemplateWithQuestions
//
//	/**
//	 * POST one TestTemplate. If no id included, a new entry is created, otherwise an existing one
//	 * is overwritten. Questions and Creator must be excluded from JSON<br>
//	 * Path = 'api/testtemplates'
//	 * @return 200 + JSON if there is data, otherwise 404 
//	 */
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response postTestTemplate(TestTemplate testTemplate) {
//		//System.out.println("in the postTestTemplate with testTemplate : "+testTemplate);
//		this.testTemplateService.save(testTemplate);
//		return Response.accepted(testTemplate).build();
//	}
	
	
	/**
	 * PUT a TestTemplate.
	 * - If supplied tt.id exists, it is overwritten, together with its list of question(IDs). Be careful, as this will remove questions
	 * from the tt in the DB that are not in the supplied tt. This is the correct way of adding/removing questions from the tt in DB. 
	 * 
	 * - If tt.id is not included or null in the supplied JSON object, a new tt will be created.
	 * 
	 * @param testTemplate Testtemplate to be created/overwritten. 
	 * @return result and Id of saved testTemplate. For an existing tt, this should be the same as the tt.id passed in.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putTestTemplateWithQuestionIds(TestTemplate testTemplate) {
		System.out.println("in the putTestTemplate with testTemplate.id : " + testTemplate.getId());
		//Gaat er vanuit dat er een testtemplate met questions aangeleverd wordt. questions hebben alleen field "id" gevuld.
		//testing if (testTemplate != null) here is redundant. Will have been catched before it reaches here and result in response 500
						
		//TODO: ttservice.save geeft niets terug!?! hoe errorhandling?
		this.testTemplateService.save(testTemplate);
		
		//TODO still think this is weird. How am I supposed to know which code (200,201,202,...?) to return?
		//Is this supposed to be done manually maybe?
		return Response.ok(testTemplate.getId()).build();
	}
	
	
//	not functioning properly, and made obsolete by new API call putTestTemplateWithQuestionIds
//	
//	/**
//	 * POST a new Question. The Question is created and attached to the TestTemplate with the specified id.<br>
//	 * Path = 'api/testtemplates/{id}/question'
//	 * @return 200 + JSON if there is data, otherwise 204 
//	 */
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("{testtemplate_id}/addquestion/{question_id}")
//	public Response addQuestionToTestTemplate(@PathParam("testtemplate_id") Long testTemplateId, @PathParam("question_id") Long questionId) {
//		Question question = this.questionService.findById(questionId);
//		TestTemplate testTemplate = this.testTemplateService.findById(testTemplateId);
//		if (testTemplate != null) {
//			this.questionService.save(question);
//			testTemplate.addQuestion(question);
//			this.testTemplateService.save(testTemplate);
//	        return Response.accepted().build();
//		} else {
//			return Response.noContent().build();
//		}
//	}
	
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
	
//	/**
//	 * POST a existing Question id. If the Question exists it is attached to the TestTemplate with the specified id.<br>
//	 * Path = 'api/testtemplates/{id}/question/{question_id}'
//	 * @return 200 + JSON if there is data, otherwise 204 
//	 */
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("{testtemplate_id}/question/{question_id}")
//	public Response addExistingQuestionToTestTemplate(@PathParam("testtemplate_id") Long testtemplateId, @PathParam("question_id") Long question_id) {
//		//System.out.println("in de addExistingQuestionToTestTemplate");
//		TestTemplate testTemplate = this.testTemplateService.findById(testtemplateId);
//		//System.out.println("-----we hebben Testtemplate gevonden :" + testTemplate.getName() + " met ID :" + testTemplate.getId()+ " voor examen :" + testTemplate.getForExam());
//		if (testTemplate != null) {
//			//System.out.println("-----we hebben Testtemplate is niet null!");
//			Question question = this.questionService.findById(question_id);
//			//System.out.println("-----we hebben deze question gevonden :" + question.getName() + " met ID :" + question.getId()+ " voor programmeertaal :" + question.getProgrammingLanguage());
//			if (question != null) {
//				//System.out.println("-----we hebben question is niet null!");
//				testTemplate.addQuestion(question);
//				this.testTemplateService.save(testTemplate);
//				return Response.ok(testTemplateService.convertToTestTemplateModelBasic(testTemplate)).build();
//
//		     //   return Response.accepted(question).build();
//			}
//		}
//		return Response.noContent().build();
//	}
	
// made obsolete by putTestTemplateWithQuestions
//
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("{testtemplate_id}/removequestionfromtesttemplate/{question_id}")
//	public Response removeExistingQuestionToTestTemplate(@PathParam("testtemplate_id") Long testtemplateId, @PathParam("question_id") Long question_id) {
//		//System.out.println("in de removequestionfromtesttemplate");
//		TestTemplate testTemplate = this.testTemplateService.findById(testtemplateId);
//		//System.out.println("*****we hebben Testtemplate gevonden :" + testTemplate.getName() + " met ID :" + testTemplate.getId()+ " voor examen :" + testTemplate.getForExam());
//		if (testTemplate != null) {
//			//System.out.println("*****we hebben Testtemplate is niet null!");
//			Question question = this.questionService.findById(question_id);
//			//System.out.println("*****we hebben deze question gevonden :" + question.getName());
//			if (question != null) {
//				//System.out.println("*****we hebben question is niet null!");
//				testTemplate.removeQuestion(question);
//				this.testTemplateService.save(testTemplate);
//				return Response.ok(testTemplateService.convertToTestTemplateModelBasic(testTemplate)).build();
//
//		     //   return Response.accepted(question).build();
//			}
//		}
//		return Response.noContent().build();
//	}

}
