package model;

/**
 * Diese Enumeration verwaltet die Reiseziele des Reisebüros.
 * 
 * @author Thomas
 * @version 02.03.2012
 */
public enum Reiseziel {

	MADRID, WIEN, ROM, BERLIN;

	/**
	 * Diese Methode gibt das übergebene Reiseziel als String aus.
	 * 
	 * @param ziel
	 *            Item aus der Enumeration Reiseziel
	 * @return String
	 */
	public String toString(Reiseziel ziel) {
		switch (ziel) {
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
