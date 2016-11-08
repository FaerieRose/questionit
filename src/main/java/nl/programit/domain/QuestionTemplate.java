package nl.programit.domain;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	private EnumProgrammingLanguage programmingLanguage;
	private EnumForExam forExam;
	private Date creationDateTime = new Date();
	private boolean isEnabled;

	@OneToOne(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Instructor creator;
	
	// ---------------------------------------------------
	// GETTER & SETTER for creator
	public Instructor getCreator() {
		return creator;
	}
	public void setCreator(Instructor creator) {
		this.creator = creator;
	}
	
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
		return programmingLanguage.getLanguage();
	}
	public void setProgrammingLanguage(EnumProgrammingLanguage programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
	
	// ---------------------------------------------------
	// GETTER & SETTER for forExam
	public String getForExam() {
		return forExam.getExam();
	}
	public void setForExam(EnumForExam forExam) {
		this.forExam = forExam;
	}		

	// ---------------------------------------------------
	// GETTER & SETTER for isEnabled
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
