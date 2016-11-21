package nl.programit.rest.service;

import java.util.ArrayList;
import java.util.Date;
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

import nl.programit.domain.AnswerList;
import nl.programit.domain.Instructor;
import nl.programit.domain.Question;
import nl.programit.domain.models.QuestionModelBasic;
import nl.programit.persistence.AnswerListService;
import nl.programit.persistence.InstructorService;
import nl.programit.persistence.QuestionService;

/**
 * Endpoint for serveral ReST services to GET, POST and DELETE Questions
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-03
 */
@Path("questions")
public class QuestionEndpoint {
	
	@Autowired
	QuestionService questionService;

	@Autowired
	InstructorService instructorService;

	@Autowired
	AnswerListService answerListService;
	
	/**
	 * GET all Questions
	 * Path = 'api/questions'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestionsAll() {
		Iterable<Question> questions = this.questionService.findAll();
		if (questions != null) {
			List<QuestionModelBasic> result = new ArrayList<>();
			for (Question question: questions) {
				result.add(this.questionService.convertToModelBasic(question));
			}
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}

	/**
	 * GET one Question with specified id
	 * Path = 'api/questions/{id}'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getQuestionById(@PathParam("id") Long id) {
		Question question = this.questionService.findById(id);
		if (question != null) {
			return Response.ok(this.questionService.convertToModelBasic(question)).build();
		} else {
			return Response.noContent().build();
		}
	}

	/**
	 * GET one Question in exam format with specified id
	 * Path = 'api/questions/exam/{id}'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("exam/{id}")
	public Response getQuestionForExam(@PathParam("id") Long id) {
		Question question = this.questionService.findById(id);
		if (question != null) {
			return Response.ok(this.questionService.convertToModelExam(question)).build();
		} else {
			return Response.noContent().build();
		}
	}

	/**
	 * GET one Question in exam review format with specified id
	 * Path = 'api/questions/examreview/{id}'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("examreview/{id}")
	public Response getQuestionForExamReview(@PathParam("id") Long id) {
		Question question = this.questionService.findById(id);
		if (question != null) {
			return Response.ok(this.questionService.convertToModelExamReview(question)).build();
		} else {
			return Response.noContent().build();
		}
	}

	/**
	 * POST a Question. A new entry is created. If the question has a valid id, that question is made obsolete.
	 * If no valid instructor_id or answerlist_id are supplied, the question is not saved!
	 * Creator, correctAnswers & givenAnswers may not be included in JSON<br>
	 * Path = 'api/questions/creator/{instructor_id}/correct-answers/{answerlist_id}'
	 * @param instructor_id must be a valid existing instructor that will be attached to Creator
	 * @param answerlist_id must be a valid existing answerlist that will be attached to CorrectAnswers
	 * @param question new Question
	 * @return 202 + JSON if new entry created, otherwise 304 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("creator/{instructor_id}/correct-answers/{answerlist_id}")
	public Response postQuestion(@PathParam("instructor_id") Long instructor_id, @PathParam("answerlist_id") Long answerlist_id, Question question) {
		Instructor creator = this.instructorService.findById(instructor_id);
		if (creator != null) {
			AnswerList answerList = this.answerListService.findById(answerlist_id);
			if (answerList != null) {
				Date dateNow = new Date();
				question.setCreationDateTime(dateNow);
				question.setCreator(creator);
				question.setCorrectAnswers(answerList);
				this.questionService.save(question);
				return Response.accepted(this.questionService.convertToModelBasic(question)).build();
			}
			return Response.notModified("Invalid AnswerList ID").build();
		}
		return Response.notModified("Invalid Instructor ID").build();
	}	
	
	/**
	 * POST an AnswerList. If it does not contain existing AnswerList id, a new one AnswerList is created, otherwise the existing one is overwritten<br>
	 * Path = 'api/questions/answer-list'
	 * @param answerList 
	 * @return 202 + JSON if there is data, otherwise 304 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("answer-list")
	public Response postAnswerList(AnswerList answerList) {
		AnswerList answer = this.answerListService.save(answerList);
		if (answer != null) {
			return Response.accepted(answer).build();
		}
		return Response.notModified("AnswerList not added to database").build();
	}
	
	/**
	 * POST adds an existing AnswerList to the GivenAnswers of a Question<br>
	 * Path = 'api/questions/{id}/given-answers/{answerlist_id}'
	 * @param id must be the id of an existing Question
	 * @param answerlist_id must be the id of an existing AnswerList
	 * @return 200 + JSON if there is data, otherwise 204 
	 */
	@POST
	@Path("{id}/given-answers/{answerlist_id}")
	public Response addGivenAnswerListToQuestion(@PathParam("id") Long id, @PathParam("answerlist_id") Long answerlist_id) {
		Question question = this.questionService.findById(id);
		if (question != null) {
			AnswerList answerlist = this.answerListService.findById(answerlist_id);
			if (answerlist != null) {
				question.addGivenAnswerList(answerlist);
				this.questionService.save(question);
		        return Response.accepted().build();
			}
		}
		return Response.noContent().build();
	}	
	
	/**
	 * POST a new correctAnswerList. The correctAnswerList is created and attached to the Question with the specified id.<br>
	 * Path = 'api/questions/{id}/correct-answers/{answerlist_id}'
	 * @param id must be the id of an existing Question
	 * @param answerlist_id must be the id of an existing AnswerList
	 * @return 200 + JSON if there is data, otherwise 204 
	 */
	@POST
	@Path("{id}/correct-answers/{answerlist_id}")
	public Response addCorrectAnswerListToQuestion(@PathParam("id") Long id, @PathParam("answerlist_id") Long answerlist_id) {
		Question question = this.questionService.findById(id);
		if (question != null) {
			AnswerList answerlist = this.answerListService.findById(answerlist_id);
			if (answerlist != null) {
				question.setCorrectAnswers(answerlist);
				this.questionService.save(question);
		        return Response.accepted().build();
			}
		}
		return Response.noContent().build();
	}
	
	/**
	 * DELETE one Question with specified id
	 * Path = 'api/questions/{id}'
	 * @return 200 + JSON if there is data, otherwise 404 
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response removeQuestion(@PathParam("id") Long id) {
		Question question = this.questionService.findById(id);
		if (question != null) {
			Question result = this.questionService.deleteById(id);
			return Response.ok(result).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	
}
