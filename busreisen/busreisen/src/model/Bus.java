package model;

/**
 * Diese Klasse beschreibt einen Bus des Reiseb�ros.
 * 
 * @author Thomas
 * @version 14.03.2012
 * 
 */
public class Bus {

	/**
	 * Anzahl der Pl�tze in einen Bus, die insgesamt zur Verf�gung stehen.
	 */
	private int anzahlPlaetze;
	/**
	 * Anzahl der Pl�tze in einen Bus, die noch frei sind.
	 */
	private int anzahlFreiePlaetze;
	/**
	 * Woche, in der der Bus f�hrt.
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
	 * Diese Methode setzt die �bergebenen Werte. Der Bus ist unbesetzt, das
	 * hei�t alle verf�gbaren Pl�tze sind frei.
	 * 
	 * @param anzahlPlaetze
	 *            Anzahl der Pl�tze als Integer
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
	 * Diese Methode gibt die Anzahl der Pl�tze als Integer zur�ck
	 * 
	 * @return anzahlPlaetze Anzahl der Pl�tze als Integer
	 */
	public int getAnzahlPlaetze() {
		return anzahlPlaetze;
	}

	/**
	 * Diese Methode setzt die Anzahl der Pl�tze
	 * 
	 * @param anzahlPlaetze
	 *            Anzahl der Pl�tze als Integer
	 */
	public void setAnzahlPlaetze(int anzahlPlaetze) {
		this.anzahlPlaetze = anzahlPlaetze;
	}

	/**
	 * Diese Methode gibt die Anzahl der freien Pl�tze als Integer zur�ck
	 * 
	 * @return anzahlFreiePlaetze Anzahl freier Pl�tze als Integer
	 */
	public int getAnzahlFreiePlaetze() {
		return anzahlFreiePlaetze;
	}

	/**
	 * Diese Methode setzt die Anzahl der freien Pl�tze
	 * 
	 * @param anzahlFreiePlaetze
	 *            Anzahl freier Pl�tze als Integer
	 */
	public void setAnzahlFreiePlaetze(int anzahlFreiePlaetze) {
		this.anzahlFreiePlaetze = anzahlFreiePlaetze;
	}

	/**
	 * Diese Methode gibt die Woche als Integer zur�ck
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
	 * Diese Methode gibt das Reiseziel als String zur�ck
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
