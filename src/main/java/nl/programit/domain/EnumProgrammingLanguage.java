package nl.programit.domain;

/**
 * Enum for allowed programming languages<br>
 *  0 = Java<br>
 *  1 = HTHML, CSS, JavaScript<br>
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-08
 */
public enum EnumProgrammingLanguage {
	JAVA ("Java"), HTML_CSS_JS ("HTML, CSS, JavaScript");
	private final String language;
	
	private EnumProgrammingLanguage(String s) {
		this.language = s;
	}
	
	public String getLanguage() {
		return this.language;
	}
}
