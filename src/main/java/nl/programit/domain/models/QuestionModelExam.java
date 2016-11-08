package nl.programit.domain.models;

import java.util.List;

import nl.programit.domain.Question;

public class QuestionModelExam {
	private Question question;
	
	public QuestionModelExam(Question question) {
		this.question = question;
	}
	
	public long getId() {
		return this.question.getId();
	}

	public boolean isEnabled() {
		return this.question.isEnabled();
	}
	
	public String getName() {
		return this.question.getName();
	}
	
	public String getQuestion() {
		return this.question.getQuestion();
	}
	
	public List<String> getPossibleAnswers() {
		return this.question.getPossibleAnswers();
	}
	
	public String getTypeOfQuestion() {
		return this.question.getTypeOfQuestion();
	}
}
