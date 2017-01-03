package nl.programit.rest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import nl.programit.domain.AnswerList;
import nl.programit.domain.Attempt;
import nl.programit.domain.Question;
import nl.programit.domain.Student;
import nl.programit.domain.TestTemplate;
import nl.programit.domain.models.AttemptModelBasic;
import nl.programit.domain.models.QuestionModelAttempt;
import nl.programit.domain.models.TestTemplateModelBasic;
import nl.programit.domain.models.TimesModelAttempt;
import nl.programit.persistence.AnswerListService;
import nl.programit.persistence.AttemptService;
import nl.programit.persistence.QuestionService;
import nl.programit.persistence.StudentService;
import nl.programit.persistence.TestTemplateService;

/**
 * Endpoint for serveral ReST services to GET, POST and DELETE Attempts
 * 
 * @author FaerieRose S.Martens
 * @version v0.1
 * @since 2016-11-29
 */
@Path("attempts")
public class AttemptEndpoint {

	@Autowired
	AttemptService attemptService;
	
	@Autowired
	TestTemplateService testTemplateService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	AnswerListService answerListService;
	
	@Autowired
	QuestionService questionService;
	
	/**
	 * GET all Attempts
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAttemptsAll() {
		if ( this.attemptService.findAll() == null) {
			return Response.noContent().build();
		} else {
			List<Attempt> result = (List) this.attemptService.findAll();
			List<AttemptModelBasic> attemps = new ArrayList<>();
			for (Attempt line : result){
				attemps.add( new AttemptModelBasic(line));
			}
			return Response.ok(attemps).build();
		}
	}
	
	/**
	 * GET one Attempt with id
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getAttemptById(@PathParam("id") Long id) {
		Attempt result = this.attemptService.findById(id);
		if (result != null) {
			AttemptModelBasic attempt = new AttemptModelBasic(result);
			return Response.ok(attempt).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * GET TestTemplate of one Attempt with id
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/TestTemplate")
	public Response getTestTemplate(@PathParam("id") Long id) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getTestTemplate() == null ){
			return Response.noContent().build();
		}
		TestTemplate result = this.attemptService.findById(id).getTestTemplate();
		if (result != null) {
			TestTemplateModelBasic template = new TestTemplateModelBasic(result);
			return Response.ok(template).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * GET givenAnswers of one Attempt with id
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/givenAnswers")
	public Response getGivenAnswers(@PathParam("id") Long id) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getGivenAnswers() == null ){
			return Response.noContent().build();
		}
		List<AnswerList> result = this.attemptService.findById(id).getGivenAnswers();
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * GET given Answers List of one Attempt with id
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/givenAnswersList")
	public Response getGivenAnswersList(@PathParam("id") Long id) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getGivenAnswers() == null) {
			return Response.noContent().build();
		} else {
			List<AnswerList> result = this.attemptService.findById(id).getGivenAnswers();
			List<String> givenAnswersList = new ArrayList<>();
			for (AnswerList question : result){
				String ansewerString = new String();
				for( int i =0 ; i < question.getAnswers().size(); i++){
					if (question.getAnswers().get(i) == true)
						ansewerString += ("" + (char)(65 +i) + ", " );
				}
				givenAnswersList.add(ansewerString);
			}
			return Response.ok(givenAnswersList).build();
		}
	}
	
	/**
	 * GET markedQuestions of one Attempt with id
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/markedQuestions")
	public Response getMarkedQuestions(@PathParam("id") Long id) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getMarkedQuestions() == null) {
			return Response.noContent().build();
		}
		List<Integer> result = this.attemptService.findById(id).getMarkedQuestions();
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * GET invalid answered questions where true/false count is not the same as in the correct answers. 
	 * for a review at the end of the exam a list is requested of the invalid answered questions
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) or 406 "Not Acceptable when number is not corresponding 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/review")	
	public Response getInvalidAnsweredQuestions(@PathParam("id") Long id) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getGivenAnswers() == null ||
				this.attemptService.findById(id).getTestTemplate() == null || 
				this.attemptService.findById(id).getTestTemplate().getQuestions() == null ){
			return Response.noContent().build();
		}
		List<AnswerList> givenAnswers = this.attemptService.findById(id).getGivenAnswers();
		List<AnswerList> correctAnswers = new ArrayList<>(); 
		TestTemplate testTemplate = this.attemptService.findById(id).getTestTemplate();
		List<Question> listQuestions = testTemplate.getQuestions();
		for (Question correctQuestion : listQuestions){
			correctAnswers.add(correctQuestion.getCorrectAnswers());
		}
		if (givenAnswers.size() != correctAnswers.size()){
			return Response.notAcceptable(null).build();
		}
	
		List<Integer> result = this.attemptService.supplyInvalidAnsweredQuestionsStream(givenAnswers, correctAnswers);
		
		return Response.ok(result).build();	
	}
	
	
	/**
	 * GET times of one Attempt with id
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent) 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/times")
	public Response getTimes(@PathParam("id") Long id) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getTestTemplate() == null ){
			return Response.noContent().build();
		} else {
			TimesModelAttempt result = new TimesModelAttempt();
			result.setCreationDateTime(this.attemptService.findById(id).getTestTemplate().getCreationDateTime());
			result.setEndDateTime(this.attemptService.findById(id).getEndDateTime());
			result.setAttemptTimeInMinutes(this.attemptService.findById(id).getTestTemplate().getAttemptTimeInMinutes());
			result.setStartDateTime(this.attemptService.findById(id).getStartDateTime());
			result.setTimeToCompletInSeconds(this.attemptService.findById(id).getTimeToCompleteInSeconds());
			
			return Response.ok(result).build();
		}	
	}
	
	/**
	 * GET question of one Attempt with id and number
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, or 204 (noContent), or 406 "Not Acceptable when number is not corresponding 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/question/{nr}")
	public Response getQuestion(@PathParam("id") Long id, @PathParam("nr") Integer nr) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getTestTemplate() == null || 
				this.attemptService.findById(id).getTestTemplate().getQuestions() == null){
			return Response.noContent().build();
		}
		if (nr < 1 || nr > this.attemptService.findById(id).getTestTemplate().getQuestions().size()){
			return Response.notAcceptable(null).build();
		}
		Question result = this.attemptService.findById(id).getTestTemplate().getQuestions().get(nr-1);
		if (result != null) {
			QuestionModelAttempt resultModelAttempt = new QuestionModelAttempt(result);
			return Response.ok(resultModelAttempt).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * GET answerList from givenAnswers corresponding with number relating to index
	 *  ( number = index + 1 )
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, or 204 (noContent), or 406 "Not Acceptable when number is not corresponding 
	 * @author S.Martens
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/answerlist/{nr}")	
	public Response getAnswerList(@PathParam("id") Long id, @PathParam("nr") Integer nr) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getGivenAnswers() == null){
			return Response.noContent().build();
		}
		if (nr < 1 || nr > this.attemptService.findById(id).getGivenAnswers().size()){
			return Response.notAcceptable(null).build();
		}
		AnswerList result = this.attemptService.findById(id).getGivenAnswers().get(nr -1); 
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * GET answer of question from one Attempt with id and number
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent), or 406 "Not Acceptable when number is not corresponding 
	 * @author S.Martens 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/question/{nr}/correctAnswers")
	public Response getCorrectAnswerOfQuestion(@PathParam("id") Long id, @PathParam("nr") Integer nr) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getTestTemplate() == null || 
				this.attemptService.findById(id).getTestTemplate().getQuestions() == null){
			return Response.noContent().build();
		}
		if (nr < 1 || nr > this.attemptService.findById(id).getTestTemplate().getQuestions().size()){
			return Response.notAcceptable(null).build();
		}
		AnswerList result = this.attemptService.findById(id).getTestTemplate().getQuestions().get(nr-1).getCorrectAnswers();
		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	/**
	 * GET answers of all question from one Attempt with id and number
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent), or 406 
	 * @author S.Martens 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/correctAnswers")
	public Response getCorrectAnswersOfQuestions(@PathParam("id") Long id, @PathParam("nr") Integer nr) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getTestTemplate() == null || 
				this.attemptService.findById(id).getTestTemplate().getQuestions() == null){
			return Response.noContent().build();
		}
		List<String> correctAnswersList = new ArrayList<>();
		List<Question> questions = this.attemptService.findById(id).getTestTemplate().getQuestions();
		for (Question question : questions){
			AnswerList correctAnswerList = question.getCorrectAnswers();
			String ansewerString = new String();
			for( int i =0 ; i < correctAnswerList.getAnswers().size(); i++){
				if (correctAnswerList.getAnswers().get(i) == true)
					ansewerString += ("" + (char)(65 +i) + ", " );
			}
			correctAnswersList.add(ansewerString);
		}
		
		return Response.ok(correctAnswersList).build();
	}
	
	/**
	 * GET list of scores of answer of questions from one Attempt with id
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent), or 406 "Not Acceptable when number is not corresponding 
	 * @author S.Martens 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/scoresList")
	public Response getScores(@PathParam("id") Long id) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getTestTemplate() == null || 
				this.attemptService.findById(id).getTestTemplate().getQuestions() == null){
			return Response.noContent().build();
		}
		
		List<AnswerList> givenAnswers = this.attemptService.findById(id).getGivenAnswers();
		List<AnswerList> correctAnswers = this.attemptService.supplyCorrectAnswers(id);
		if (givenAnswers.size() != correctAnswers.size()){
			return Response.notAcceptable(null).build();
		}
		List<Boolean> scoresList = this.attemptService.supplyScoreList(givenAnswers, correctAnswers);
		
		return Response.ok(scoresList).build();
		
	}
	
	/**
	 * GET rate of scores of answer of questions from one Exam with id
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, otherwise 204 (noContent), or 406 "Not Acceptable when number is not corresponding 
	 * @author S.Martens 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/scoresRate")
	public Response getRate(@PathParam("id") Long id) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getTestTemplate() == null || 
				this.attemptService.findById(id).getTestTemplate().getQuestions() == null){
			return Response.noContent().build();
		}
		
		List<AnswerList> givenAnswers = this.attemptService.findById(id).getGivenAnswers();
		List<AnswerList> correctAnswers = this.attemptService.supplyCorrectAnswers(id);
		if (givenAnswers.size() != correctAnswers.size()){
			return Response.notAcceptable(null).build();
		}
		
		List<Boolean> scoresList = this.attemptService.supplyScoreList(givenAnswers, correctAnswers); 
		double scorePercentages = this.attemptService.calculatePercentageStream(scoresList);
		 
		return Response.ok(scorePercentages).build();
		
	}
	
	/**
	 * POST to start one Attempt. a new Attempt entry is created.
	 * Creator, correctAnswers & givenAnswers may not be included in JSON<br>
	 * Path = 'api/attempts'
	 * @return 202 + JSON if there is data, otherwise 204 (noContent)
	 * @author S.Martens
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("start/{questionTemplate_id}/{student_id}")
	public Response postNewAttempt(@PathParam("questionTemplate_id") Long ql_id, @PathParam("student_id") Long s_id) {
		if (this.testTemplateService.findById(ql_id) == null || this.studentService.findById(s_id) == null){
			return Response.noContent().build();
		}
		else {
			Attempt attempt = new Attempt();
			attempt.setTestTemplate(this.testTemplateService.findById(ql_id));

			for (int i = 0; i < attempt.getTestTemplate().getQuestions().size(); i++){
				AnswerList answerList = new AnswerList();
				this.answerListService.save(answerList);
				attempt.getGivenAnswers().add(answerList);
			}			
			this.attemptService.save(attempt);
			Student student = this.studentService.findByIdWithAttempts(s_id);	
			student.getAttempts().add(attempt);	
			this.studentService.save(student);

			return Response.accepted(attempt.getId()).build();
		}	
	}
	
	/**
	 * POST to end one Attempt.
	 * Creator, correctAnswers & givenAnswers may not be included in JSON<br>
	 * Path = 'api/attempts'
	 * @return 202 + JSON if there is data, otherwise 204, or 406 "Not Acceptable when id is not active 
	 * @author S.Martens
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/end")		
	public Response postEndAttempt(@PathParam("id") Long id) {
		if (this.attemptService.findById(id) == null){
			return Response.noContent().build();
		}
		else if (this.attemptService.findById(id).getEndDateTime() != null){
			return Response.notAcceptable(null).build();
		}
		else {
			Attempt attempt = this.attemptService.findById(id);
			attempt.setEndDateTime(new Date());
			attempt.setTimeToCompleteInSeconds(attempt.getTimeToCompleteInSeconds() + 10);
			
			List<AnswerList> givenAnswers = this.attemptService.findById(id).getGivenAnswers(); 
			TestTemplate testTemplate = this.attemptService.findById(id).getTestTemplate();
			if (testTemplate != null){
				List<Question> listQuestions = testTemplate.getQuestions();
				for (int i = 0; i < givenAnswers.size(); i++ ){
					listQuestions.get(i).getGivenAnswers().add(givenAnswers.get(i));
					this.questionService.save(listQuestions.get(i));
				}
			}
			
			this.attemptService.save(attempt);
			
			return Response.accepted(attempt.getId()).build();
		}	
	}
	 
	/**
	 * PUT for one Attempt for elapsed time a ping for increment of 10sec is given
	 * Path = 'api/attempts'
	 * @return 202 + JSON if there is data, otherwise 204 
	 * @author S.Martens
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/ping")
	public Response putPing(@PathParam("id") Long id) {
		if (this.attemptService.findById(id) == null){
			return Response.noContent().build();
		} else {
			Attempt attempt = this.attemptService.findById(id);
			attempt.setTimeToCompleteInSeconds(attempt.getTimeToCompleteInSeconds() + 10);
			this.attemptService.save(attempt);
			
			return Response.accepted(attempt.getTimeToCompleteInSeconds()).build();
		}
	}
	
	/**
	 * PUT for one Attempt for a marked number to add to marked question list
	 * Path = 'api/attempts'
	 * @return 202 + JSON if there is data, otherwise 204,  or 406 "Not Acceptable when number is not allowed
	 * @author S.Martens
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/mark/{question_nr}")
	public Response putMark(@PathParam("id") Long id, @PathParam("question_nr") Integer nr) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getTestTemplate() == null || 
				this.attemptService.findById(id).getTestTemplate().getQuestions() == null || this.attemptService.findById(id).getMarkedQuestions() == null){
			return Response.noContent().build();
		}
		if (nr < 1 || nr > this.attemptService.findById(id).getTestTemplate().getQuestions().size()){
			return Response.notAcceptable(null).build();
		} else {
			Attempt attempt = this.attemptService.findById(id);
			if (attempt.getMarkedQuestions().contains(nr)){
				return Response.noContent().build();
			} else {
			attempt.getMarkedQuestions().add(nr);
			this.attemptService.save(attempt);
			
			return Response.accepted(attempt.getMarkedQuestions()).build();
			}
		}
	}
	
	/**
	 * PUT for one Attempt to un-marked a number to remove from the marked question list
	 * Path = 'api/attempts'
	 * @return 202 + JSON if there is data, otherwise 204,  or 406 "Not Acceptable when number is not allowed
	 * @author S.Martens
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/unmark/{question_nr}")
	public Response putUnmark(@PathParam("id") Long id, @PathParam("question_nr") Integer nr) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getTestTemplate() == null || 
				this.attemptService.findById(id).getTestTemplate().getQuestions() == null || this.attemptService.findById(id).getMarkedQuestions() == null){
			return Response.noContent().build();
		}
		if (nr < 1 || nr > this.attemptService.findById(id).getTestTemplate().getQuestions().size()){
			return Response.notAcceptable(null).build();
		} 
		Attempt attempt = this.attemptService.findById(id);
		if (!attempt.getMarkedQuestions().contains(nr)){
			return Response.noContent().build();
		} else {
			attempt.getMarkedQuestions().remove(nr);
			this.attemptService.save(attempt);
			
			return Response.accepted(attempt.getMarkedQuestions()).build();
		}
	}
	
	/**
	 * PUT answerList from givenAnswers corresponding with number relating to index
	 *  ( number = index + 1 )
	 * Path = 'api/attempts'
	 * @return 200 + JSON if there is data, or 204 (noContent), or 406 "Not Acceptable when number is not corresponding 
	 * @author S.Martens
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/answerlist/{nr}")
	public Response putAnswerList(@PathParam("id") Long id, @PathParam("nr") Integer nr, AnswerList answerListIn) {
		if (this.attemptService.findById(id) == null || this.attemptService.findById(id).getGivenAnswers() == null){
			return Response.noContent().build();
		}
		if (nr < 1 || nr > this.attemptService.findById(id).getGivenAnswers().size()){
			return Response.notAcceptable(null).build();
		}
		AnswerList answerListDb = this.attemptService.findById(id).getGivenAnswers().get(nr -1); 
		answerListDb.setAnswers(answerListIn.getAnswers());
		this.answerListService.save(answerListDb);
		
		return Response.ok(answerListDb).build();
	}
	
	/**
	 * POST one new Attempt 
	 * Path = 'api/attempts'
	 * @return 202 + JSON attempt data
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postStartNewAttempt(Attempt attempt) {
		this.attemptService.save(attempt);
		return Response.accepted(attempt).build();
	}
}

