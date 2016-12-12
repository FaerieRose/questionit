package nl.programit.domain.models;

import java.util.List;

import nl.programit.domain.TestTemplate;

/**
 * A model to get only the meta data of a TestTemplate (i.e. id, size, allowed time, etc.)
 * 
 * @author Bas Smulders
 * @version v0.1
 * @since 2016-12-12
 */

public class TestTemplateModelMeta {
	private TestTemplate tt;
	
	public TestTemplateModelMeta(TestTemplate tt) {
		this.tt = tt;
	}
	
	public long getId() {
		return this.tt.getId();
	}
	
	public long getAttemptTimeInMinutes() {
		return tt.getAttemptTimeInMinutes();
	}
	
	public long getSize() {
		return tt.getQuestions().size();
	}
}
