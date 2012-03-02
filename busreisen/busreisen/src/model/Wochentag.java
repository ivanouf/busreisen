package model;
/**
 * Diese Enumeration verwaltet alle Starttage der Reisen.
 * 
 * @author Thomas
 * @version 02.03.2012
 * 
 */
public enum Wochentag {

	MONTAG,
	FREITAG,
	SAMSTAG,
	SONNTAG;

	/**
	 * Diese Methode gibt den übergebenen Wochentag als String zurück.
	 * @param tag
	 * 			Item aus der Enumeration Wochentag
	 * @return String
	 */
	public String toString(Wochentag tag){
		switch(tag){
		case MONTAG:
			return "Montag";
		case FREITAG:
			return "Freitag";
		case SAMSTAG:
			return "Samstag";
		case SONNTAG:
			return "Sonntag";
		default:
			return "";
		}
	}
}
