package nl.programit.domain.models;

import java.util.Date;

/**
 * A model for attempt times
 * 
 * @author S.Martens
 * @version v0.1
 * @since 2016-11-28
 */

public class TimesModelAttempt {
	private Date creationDateTime;
	private int attemptTimeInMinutes;
	private Date startDateTime;
	private Date endDateTime;
	private int timeToCompletInSeconds;
	
	public Date getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	public int getAttemptTimeInMinutes() {
		return attemptTimeInMinutes;
	}
	public void setAttemptTimeInMinutes(int attemptTimeInMinutes) {
		this.attemptTimeInMinutes = attemptTimeInMinutes;
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
