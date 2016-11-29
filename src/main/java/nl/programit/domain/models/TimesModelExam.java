package nl.programit.domain.models;

import java.util.Date;

/**
 * A model for exam times
 * 
 * @author S.Martens
 * @version v0.1
 * @since 2016-11-28
 */

public class TimesModelExam {
	private Date creationDateTime;
	private int examTimeInMinutes;
	private Date startDateTime;
	private Date endDateTime;
	private int timeToCompletInSeconds;
	
	public Date getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	public int getExamTimeInMinutes() {
		return examTimeInMinutes;
	}
	public void setExamTimeInMinutes(int examTimeInMinutes) {
		this.examTimeInMinutes = examTimeInMinutes;
	}
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	public int getTimeToCompletInSeconds() {
		return timeToCompletInSeconds;
	}
	public void setTimeToCompletInSeconds(int timeToCompletInSeconds) {
		this.timeToCompletInSeconds = timeToCompletInSeconds;
	}
	
}
