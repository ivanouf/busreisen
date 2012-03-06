package model;

/**
 * Diese Klasse beschreibt einen Bus des Reisebï¿½ros.
 * 
 * @author Thomas
 * @version 06.03.2012
 * 
 */
public class Bus {

	/**
	 * Anzahl der Plätze in einen Bus, die insgesamt zur Verfügung stehen.
	 */
	private int anzahlPlaetze;
	/**
	 * Anzahl der Plätze in einen Bus, die noch frei sind.
	 */
	private int anzahlFreiePlaetze;
	/**
	 * Woche, in der der Bus fährt.
	 */
	private int woche;
	/**
	 * Reiseziel des Busses als Item der Enumeration {@link Reiseziel}
	 */
	private Reiseziel reiseZiel;

	/**
	 * Dieser Konstruktor initialisiert die Attribute mit Default-Werten.
	 */
	public Bus() {
		this.anzahlPlaetze = 0;
		this.anzahlFreiePlaetze = 0;
		this.woche = 0;
		this.reiseZiel = null;
	}

	/**
	 * Diese Methode setzt die Ã¼bergebenen Werte. Der Bus ist unbesetzt, das
	 * heiÃŸt alle verfÃ¼gbaren PlÃ¤tze sind frei.
	 * 
	 * @param anzahlPlaetze
	 *            Anzahl der PlÃ¤tze als Integer
	 * @param woche
	 *            Woche als Integer
	 * @param reiseZiel
	 *            Reiseziel als String
	 */
	public Bus(int anzahlPlaetze, int woche, Reiseziel reiseZiel) {
		this.anzahlPlaetze = anzahlPlaetze;
		this.woche = woche;
		this.reiseZiel = reiseZiel;
		this.anzahlFreiePlaetze = anzahlPlaetze;
	}

	/**
	 * Diese Methode gibt die Anzahl der PlÃ¤tze als Integer zurï¿½ck
	 * 
	 * @return anzahlPlaetze Anzahl der PlÃ¤tze als Integer
	 */
	public int getAnzahlPlaetze() {
		return anzahlPlaetze;
	}

	/**
	 * Diese Methode setzt die Anzahl der Plï¿½tze
	 * 
	 * @param anzahlPlaetze
	 *            Anzahl der Plï¿½tze als Integer
	 */
	public void setAnzahlPlaetze(int anzahlPlaetze) {
		this.anzahlPlaetze = anzahlPlaetze;
	}

	/**
	 * Diese Methode gibt die Anzahl der freien Plï¿½tze als Integer zurï¿½ck
	 * 
	 * @return anzahlFreiePlaetze Anzahl freier Plï¿½tze als Integer
	 */
	public int getAnzahlFreiePlaetze() {
		return anzahlFreiePlaetze;
	}

	/**
	 * Diese Methode setzt die Anzahl der freien Plï¿½tze
	 * 
	 * @param anzahlFreiePlaetze
	 *            Anzahl freier Plï¿½tze als Integer
	 */
	public void setAnzahlFreiePlaetze(int anzahlFreiePlaetze) {
		this.anzahlFreiePlaetze = anzahlFreiePlaetze;
	}

	/**
	 * Diese Methode gibt die Woche als Integer zurï¿½ck
	 * 
	 * @return woche Woche als Integer
	 */
	public int getWoche() {
		return woche;
	}

	/**
	 * Diese Methode setzt die Woche
	 * 
	 * @param woche
	 *            Woche als Integer
	 */
	public void setWoche(int woche) {
		this.woche = woche;
	}

	/**
	 * Diese Methode gibt das Reiseziel als String zurï¿½ck
	 * 
	 * @return reiseZiel Reiseziel als String
	 */
	public Reiseziel getReiseZiel() {
		return reiseZiel;
	}

	/**
	 * Diese Methode setzt das Reiseziel
	 * 
	 * @param reiseZiel
	 *            Reiseziel als String
	 */
	public void setReiseZiel(Reiseziel reiseZiel) {
		this.reiseZiel = reiseZiel;
	}

}
