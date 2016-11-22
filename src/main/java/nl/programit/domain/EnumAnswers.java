package nl.programit.domain;

/**
 * Enum for allowed answers
 *  0 = X<br>
 *  1 = A<br>
 *  2 = B<br>
 *  3 = C<br>
 *  4 = D<br>
 *  5 = E<br>
 *  6 = F<br>
 *  7 = G<br>
 *  8 = H<br>
 *  9 = I<br>
 * 10 = J<br>
 * 
 * @author FaerieRose
 * @version v0.1
 * @since 2016-11-08
 */
public enum EnumAnswers {
	X ( 0, 'x'),
	A ( 1, 'a'), 
	B ( 2, 'b'), 
	C ( 3, 'c'), 
	D ( 4, 'd'), 
	E ( 5, 'e'), 
	F ( 6, 'f'), 
	G ( 7, 'g'), 
	H ( 8, 'h'), 
	I ( 9, 'i'), 
	J (10, 'j');
	private final char lowerCase;
	private final int number;
	
	/**
	 * Constructor
	 * @param i
	 * @param c
	 */
	private EnumAnswers(int i, char c) {
		this.lowerCase = c;
		this.number = i;
	}

	// ---------------------------------------------------
	// GETTER for lowercase		
	public char getLowerCase() {
		return this.lowerCase;
	}	

	// ---------------------------------------------------
	// GETTER for number		
	public int getNumber() {
		return this.number;
	}
}
