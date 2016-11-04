package nl.programit.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//See: https://en.wikibooks.org/wiki/Java_Persistence/Inheritance look for Mapped Superclasses

/**
 * Parent class for Question and QuestionList.
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-03
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
	
	// ---------------------------------------------------
	// GETTER & SETTER for creationDateTime
	public Date getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	
	// ---------------------------------------------------
	// GETTER & SETTER for id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	// ---------------------------------------------------
	// GETTER & SETTER for name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// ---------------------------------------------------
	// GETTER & SETTER for programmingLanguage
	public String getProgrammingLanguage() {
		return programmingLanguage;
	}
	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
	
	// ---------------------------------------------------
	// GETTER & SETTER for forExam
	public String getForExam() {
		return forExam;
	}
	public void setForExam(String forExam) {
		this.forExam = forExam;
	}		
}
