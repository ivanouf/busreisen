package model;

/**
 * Diese Enumeration verwaltet die Reiseziele des Reisebüros.
 * 
 * @author Thomas
 * @version 09.03.2012
 */
public enum Reiseziel {

	MADRID, WIEN, ROM, BERLIN;

	/**
	 * Diese Methode gibt das entsprechende Reiseziel als String aus.
	 * 
	 * @return String
	 */
	public String toString() {
		switch (this) {
		case MADRID:
			return "Madrid";
		case WIEN:
			return "Wien";
		case ROM:
			return "Rom";
		case BERLIN:
			return "Berlin";
		default:
			return "";
		}
	}
}
