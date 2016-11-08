package nl.programit.domain;

/**
 * Enum for allowed exams<br>
 *  0 = None<br>
 *  1 = OCA<br>
 *  2 = OCP<br>
 *  3 = 70-480 
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-08
 */
public enum EnumExams {
	NONE ("None"), OCA ("OCA"), OCP ("OCP"), _70_480 ("70-480");
	private final String exam;
	
	/**
	 * Constructor
	 * @param s
	 */
	private EnumExams(String s) {
		this.exam = s;
	}
	
	// ---------------------------------------------------
	// GETTER for Exam	
	public String getExam() {
		return this.exam;
	}
}
