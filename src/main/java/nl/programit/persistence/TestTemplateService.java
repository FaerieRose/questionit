package nl.programit.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.programit.domain.TestTemplate;
import nl.programit.domain.models.TestTemplateModelMeta;

/**
 * This class contains several methods to interact with TestTemplateRepository
 * to add, get or remove data from the database
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-04
 */
@Service
@Transactional
public class TestTemplateService {

	@Autowired
	private TestTemplateRepository testTemplateRepository;


	/**
	 * Saves a questionlist to the database. If no id included, a new entry
	 * is created, otherwise an existing one is overwritten
	 * @param testTemplate the TestTemplate to be saved
	 */
	public void save(TestTemplate testTemplate) {
		this.testTemplateRepository.save(testTemplate);
	}
	
	/**
	 * Retrieves all questionLists stored in the database 
	 * @return all questionLists
	 */	
	public Iterable<TestTemplate> findAll() {
		Iterable<TestTemplate> result = this.testTemplateRepository.findAll();
		return result;
	}
	
	/**
	 * Retrieves one Questionlist from the database with specified id. If the id
	 * does not exist, null is returned 
	 * @param id the id of the TestTemplate
	 * @return requested TestTemplate or null if it does not exist
	 */
	public TestTemplate findById(long id) {
		TestTemplate result = this.testTemplateRepository.findOne(id);
		return result;
	}
	
	/**
	 * 2016-12-12: Added by Bas Smulders
	 * Converts a TestTemplate to TestTemplateModelMeta to send only meta data of given TestTemplate
	 * @param tt the TestTemplate to be converted
	 * @return TestTemplateModelMeta version of the TestTemplate
	 */
	public TestTemplateModelMeta convertToTestTemplateModelMeta(TestTemplate tt) {
		TestTemplateModelMeta result = new TestTemplateModelMeta(tt);
		return result;
	}
}
