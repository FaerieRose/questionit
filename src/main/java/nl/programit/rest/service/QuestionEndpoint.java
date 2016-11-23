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
import nl.programit.domain.EnumExams;
import nl.programit.domain.EnumLanguages;
import nl.programit.domain.Instructor;
import nl.programit.domain.Question;
import nl.programit.domain.models.QuestionModelBasic;
import nl.programit.domain.models.QuestionModelName;
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
	 * @return 200 + JSON if there is data, otherwise 404 (Not Found) 
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
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	
	/**
	 * GET all Questions
	 * Path = 'api/questions'
	 * @return 200 + JSON if there is data, otherwise 404 (Not Found) 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("select/{for_exam}/{language}/{enabled}/{obsolete}")
	public Response getQuestions(
			@PathParam("for_exam") EnumExams exam,
			@PathParam("language") EnumLanguages language,
			@PathParam("enabled" ) boolean enabled,
			@PathParam("obsolete") boolean obsolete) {
//		Iterable<Question> questions = this.questionService.findAll();
		List<Question> questions = new ArrayList<>(); 
		List<QuestionModelName> result = new ArrayList<>(); 
		questions = (List<Question>) this.questionService.findAll();
		for (Question question: questions) {
			if (question.getForExam() == exam.ordinal() || exam == EnumExams.NONE) {
				if (question.getProgrammingLanguage() == language.ordinal() || language == EnumLanguages.NONE) {
					if (question.isEnabled() == enabled) {
						if (question.isObsolete() == obsolete) {
							result.add(this.questionService.convertToModelName(question));
						}
					}
				}
			}
		}
		return Response.ok(result).build();
	}	
	
	/**
	 * GET one Question with specified id. The format must be specified behind the id. There are 
	 * three choices: exam, examreview and basic. <br>
	 * Path = 'api/questions/{id}/{format}'
	 * @param id the id of the requested Question
	 * @param format the format in which the Question must be sent: 'exam', 'examreview' or 'basic'
	 * @return 200 + JSON if there is data, 400 if the format is not specified correctly, otherwise 404 (Not Found)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/{format}")
	public Response getQuestionById(@PathParam("id") Long id, @PathParam("format") String format) {
		System.out.println(format);
		Question question = this.questionService.findById(id);
		if (question != null) {
			if (format.equals("exam")) {
				return Response.ok(this.questionService.convertToModelExam(question)).build();
			} else if (format.equals("examreview")) {
				return Response.ok(this.questionService.convertToModelExamReview(question)).build();
			} else if (format.equals("basic")) {
				return Response.ok(this.questionService.convertToModelBasic(question)).build();
			}
			return Response.status(Status.BAD_REQUEST).build();
		} 
		return Response.status(Status.NOT_FOUND).build();
	}
	
	

	/**
	 * POST a Question. A new entry is created. If the question has a valid id, that question is made obsolete.
	 * If no valid instructor_id or answerlist_id are supplied, the question is not saved!
	 * Creator, correctAnswers & givenAnswers may not be included in JSON<br>
	 * Path = 'api/questions/creator/{instructor_id}/correct-answers/{answerlist_id}'
	 * @param instructor_id must be a valid existing instructor that will be attached to Creator
	 * @param answerlist_id must be a valid existing answerlist that will be attached to CorrectAnswers
	 * @param question new Question
	 * @return 200 + ID if new entry created, 404 if answerlist or instructor did not exist, 500 otherwise 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
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
				question.setEnabled(true);
				question.setObsolete(false);
				Question result = this.questionService.save(question);
				if (result != null) {
					return Response.ok(Long.toString(result.getId())).build();
				} 
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();		
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}	
	
	/**
	 * POST adds an existing AnswerList to givenAnswerList or sets an existing AnswerList to correctAnswers, depending on the field<br>
	 * Path = 'api/questions/{id}/{answerlist_id}/{field}'
	 * @param id must be the id of an existing Question
	 * @param answerlist_id must be the id of an existing AnswerList
	 * @param field must be 'correct' or 'given'.
	 * @return 200 if AnswerList added or set, 400 if field was not correct, otherwise 404 
	 */
	@POST
	@Path("{id}/{answerlist_id}/{field}")
	public Response addAnswerListToQuestion(@PathParam("id") Long id, @PathParam("answerlist_id") Long answerlist_id, @PathParam("field") String field) {
		Question question = this.questionService.findById(id);
		if (question != null) {
			AnswerList answerlist = this.answerListService.findById(answerlist_id);
			if (answerlist != null) {
				if (field.equals("correct")) {
					question.setCorrectAnswers(answerlist);
					this.questionService.save(question);
			        return Response.ok().build();
				} else if (field.equals("given")) {
					question.addGivenAnswerList(answerlist);
					this.questionService.save(question);
			        return Response.ok().build();
				}
				return Response.status(Status.BAD_REQUEST).build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}	
	
	/**
	 * DELETE one Question with specified id
	 * Path = 'api/questions/{id}'
	 * @return 200 if there was data, otherwise 404 
	 */
	@DELETE
	@Path("{id}")
	public Response removeQuestion(@PathParam("id") Long id) {
		Question question = this.questionService.findById(id);
		if (question != null) {
			this.questionService.deleteById(id);
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
}
