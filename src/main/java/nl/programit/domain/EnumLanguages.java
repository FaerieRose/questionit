package nl.programit.domain;

/**
 * Enum for allowed programming languages<br>
 *  0 = None<br>
 *  1 = Java<br>
 *  2 = HTHML, CSS, JavaScript<br>
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-08
 */
public enum EnumLanguages {
	NONE ("None"), JAVA ("Java"), HTML_CSS_JS ("HTML, CSS, JavaScript");
	private final String language;
	
	/**
	 * Constructor
	 * @param s
	 */
	private EnumLanguages(String s) {
		this.language = s;
	}

	// ---------------------------------------------------
	// GETTER for Language	
	public String getLanguage() {
		return this.language;
	}
}
