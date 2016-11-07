package nl.programit.domain;

/**
 * Enum for allowed exams<br>
 *  0 = OCA<br>
 *  1 = OCP<br>
 *  2 = 70-480 
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-08
 */
public enum EnumForExam {
	OCA ("OCA"), OCP ("OCP"), _70_480 ("70-480");
	private final String exam;
	
	private EnumForExam(String s) {
		this.exam = s;
	}
	
	public String getExam() {
		return this.exam;
	}
}
