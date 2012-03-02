package model;
/**
 * Diese Klasse beschreibt einen Bus des Reisebüros.
 * @author Thomas
 * @version 28.02.2012
 * 
 */
public class Bus {

	private int anzahlPlaetze;
	private int anzahlFreiePlaetze;
	private int woche;
	private String reiseZiel;

	
	/**
	 * Dieser Konstruktor initialisiert die Attribute mit Default-Werten
	 */
	public Bus(){
		this.anzahlPlaetze = 0;
		this.anzahlFreiePlaetze = 0;
		this.woche = 0;
		this.reiseZiel = "NoWhere";
	}
	/**
	 * Diese Methode setzt die übergebenen Werte.
	 * @param anzahlPlaetze 
	 * 					Anzahl der Plätze als Integer
	 * @param woche 
	 * 					Woche als Integer
	 * @param reiseZiel 
	 * 					Reiseziel als String
	 */
	public Bus(int anzahlPlaetze, int woche, String reiseZiel){
		this.anzahlPlaetze = anzahlPlaetze;
		this.woche = woche;
		this.reiseZiel = reiseZiel;
		
	}
	/**
	 * Diese Methode gibt die Anzahl der Plätze als Integer zurück
	 * @return anzahlPlaetze
	 * 					Anzahl der Plätze als Integer
	 */
	public int getAnzahlPlaetze() {
		return anzahlPlaetze;
	}
	/**
	 * Diese Methode setzt die Anzahl der Plätze
	 * @param anzahlPlaetze 
	 * 					Anzahl der Plätze als Integer
	 */
	public void setAnzahlPlaetze(int anzahlPlaetze) {
		this.anzahlPlaetze = anzahlPlaetze;
	}
	/**
	 * Diese Methode gibt die Anzahl der freien Plätze als Integer zurück
	 * @return anzahlFreiePlaetze
	 * 					Anzahl freier Plätze als Integer
	 */
	public int getAnzahlFreiePlaetze() {
		return anzahlFreiePlaetze;
	}
	/**
	 * Diese Methode setzt die Anzahl der freien Plätze
	 * @param anzahlFreiePlaetze 
	 * 					Anzahl freier Plätze als Integer
	 */
	public void setAnzahlFreiePlaetze(int anzahlFreiePlaetze) {
		this.anzahlFreiePlaetze = anzahlFreiePlaetze;
	}
	/**
	 * Diese Methode gibt die Woche als Integer zurück
	 * @return woche
	 * 					Woche als Integer
	 */
	public int getWoche() {
		return woche;
	}
	/**
	 * Diese Methode setzt die Woche
	 * @param woche
	 * 					Woche als Integer
	 */
	public void setWoche(int woche) {
		this.woche = woche;
	}
	/**
	 * Diese Methode gibt das Reiseziel als String zurück
	 * @return reiseZiel
	 * 					Reiseziel als String
	 */
	public String getReiseZiel() {
		return reiseZiel;
	}
	/**
	 * Diese Methode setzt das Reiseziel
	 * @param reiseZiel
	 * 					Reiseziel als String
	 */
	public void setReiseZiel(String reiseZiel) {
		this.reiseZiel = reiseZiel;
	}
	
}
