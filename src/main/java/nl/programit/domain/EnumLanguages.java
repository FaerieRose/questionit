package nl.programit.domain;

import java.util.ArrayList;

/**
 * Enum for allowed programming languages<br>
 *  0 = None<br>
 *  1 = Java<br>
 *  2 = HTHML, CSS, JavaScript<br>
 *  3 = PHP<br>
 *  4 = C#
 * 
 * @author FaerieRose, S.Martens
 * @version v0.1
 * @since 2016-11-28
 */
public enum EnumLanguages {
	NONE ("None"), 
	JAVA ("Java"){
		public ArrayList<String> getLevels(){
			ArrayList<String> levels = new ArrayList<String>();
			levels.add(EnumExams.OCA.toString());
			levels.add(EnumExams.OCP.toString());
			return levels;
		}
	}, 
	HTML_CSS_JS ("HTML, CSS, JavaScript"){
		public ArrayList<String> getLevels(){
			ArrayList<String> levels = new ArrayList<String>();
			levels.add(EnumExams._70_480.toString());
			return levels;
		}
	}, 
	PHP ("PHP"), 
	C_SHARP ("C#"){
		public ArrayList<String> getLevels(){
			ArrayList<String> levels = new ArrayList<String>();
			levels.add(EnumExams._70_483.toString());
			return levels;
		}
	};
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
	
	public  ArrayList<String> getLevels(){
		 ArrayList<String> levels = new  ArrayList<String>();
		return levels;
		
	}
}
