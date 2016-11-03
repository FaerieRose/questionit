package nl.programit.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//See: https://en.wikibooks.org/wiki/Java_Persistence/Inheritance look for Mapped Superclasses

/**
 * Parent class for Question and QuestionList 
 * @author FaerieRose
 * @version v0.1
 */
@MappedSuperclass
public abstract class QuestionTemplate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private String programmingLanguage;
	private String forExam;
	private Date creationDateTime = new Date();
	
	public Date getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProgrammingLanguage() {
		return programmingLanguage;
	}
	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
	
	public String getForExam() {
		return forExam;
	}
	public void setForExam(String forExam) {
		this.forExam = forExam;
	}
		
}
