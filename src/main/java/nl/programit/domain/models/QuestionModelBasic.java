package nl.programit.domain.models;

import java.util.List;

import nl.programit.domain.AnswerList;
import nl.programit.domain.Instructor;
import nl.programit.domain.Question;

public class QuestionModelBasic {
	private Question question;
	
	public QuestionModelBasic(Question question) {
		this.question = question;
	}

	public long getId() {
		return this.question.getId();
	}

	public String getName() {
		return this.question.getName();
	}

	public String getQuestion() {
		return this.question.getQuestion();
	}

	public String getExplantionAnswer() {
		return this.question.getExplantionAnswer();
	}

	public String getTypeOfQuestion() {
		return this.question.getTypeOfQuestion();
	}

	public InstructorModelName getCreator() {
		Instructor creator = this.question.getCreator();
		InstructorModelName modelName = new InstructorModelName(creator);
		return modelName;
	}
	
	public List<String> getPossibleAnswers() {
		return this.question.getPossibleAnswers();
	}
	
	public int getProgrammingLanguage() {
		return this.question.getProgrammingLanguage();
	}
	
	public int getForExam() {
		return this.question.getForExam();
	}
	
	public AnswerList getCorrectAnswers() {
		return this.question.getCorrectAnswers();
	}
}
