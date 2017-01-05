package nl.programit.domain.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nl.programit.domain.Question;
import nl.programit.domain.TestTemplate;

/**
 * A model to get the meta data of a TestTemplate but for instructor only the id
 * 
 * @author S. Martens
 * @version v0.1
 * @since 2016-12-16
 */

public class TestTemplateModelBasic {
	private TestTemplate tt;
	
	public TestTemplateModelBasic(TestTemplate tt) {
		this.tt = tt;
	}
	
	// Getters
	public long getId() {
		return this.tt.getId();
	}
	
	public long getAttemptTimeInMinutes() {
		return tt.getAttemptTimeInMinutes();
	}
	
	public Date getCreationDateTime() {
		return tt.getCreationDateTime();
	}
	
	public long getCreatorId() {
	return tt.getCreator().getId();
    }
	
	public String getName() {
		return tt.getName();
	}
	
	public int getProgrammingLanguage() {
		return tt.getProgrammingLanguage();
	}

	public int getForExam() {
		return tt.getForExam();
	}

	public boolean isEnabled() {
		return tt.isEnabled();
	}
	
	public List<Long> getQuestionsIds() {
	if (tt.getQuestions() != null){
		List<Long> result = new ArrayList<>();
		for (Question question : tt.getQuestions()){
			result.add(question.getId());
		}
		return result;
	}
	else {
		List<Long> resultNok = new ArrayList<>();
		resultNok.add(-1L);
		return resultNok;
		}
	}
	
	public long getSize() {
		return tt.getQuestions().size();
	}
	
}
