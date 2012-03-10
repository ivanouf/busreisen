package model;

/**
 * Diese Klasse modelliert eine Buchung, die von einem Mitarbeiter des
 * Reisebüros in das Programm eingegeben wird.
 * 
 * @author Philipp
 * @version 10.03.2012
 */
public class Buchung {

	/**
	 * Instanz von {@link Kunde}, auf die sich die Buchung bezieht.
	 */
	private Kunde kunde;

	/**
	 * Buchungsnummer für diesen Kunden
	 */
	private int buchungsnr;

	/**
	 * Falls die Buchung storniert wird, existiert eine Stornonummer. Ansonsten
	 * hat dieses Attribut den Wert 0.
	 */
	private int stornonr;

	/**
	 * Reiseziel des Kunden als Item der Enumeration {@link Reiseziel}
	 */
	private Reiseziel reiseZiel;

	/**
	 * Vom Kunden gewünschte Reisewoche
	 */
	private int woche;

	/**
	 * Vom Kunden gebuchte Anzahl der Plätze
	 */
	private int plaetze;

	/**
	 * Falls die Buchung korrigiert wird, enthaelt dieser String den Wert "ja",
	 * ansonsten "nein".
	 */
	private String korrigiert;

	/**
	 * Dieser Konstruktor erstellt eine leere Buchung.
	 */
	public Buchung() {
		this.buchungsnr = 0;
		this.reiseZiel = null;
		this.woche = 0;
		this.kunde = null;
		this.stornonr = 0;
		this.korrigiert = "nein";
	}

	/**
	 * Im Konstruktor wird die Buchung über folgende Parameter definiert.
	 * 
	 * @param buchungsnr
	 *            vom System zugewiesene Buchungsnummer
	 * @param reiseZiel
	 *            gewünschtes Reiseziel des Kunden
	 * @param woche
	 *            gebuchte Reisewoche
	 * @param kunde
	 *            Kunde, der die Reise gebucht hat
	 */
	public Buchung(int buchungsnr, Reiseziel reiseZiel, int woche, Kunde kunde) {
		this.buchungsnr = buchungsnr;
		this.reiseZiel = reiseZiel;
		this.woche = woche;
		this.kunde = kunde;
		this.stornonr = 0;
		this.korrigiert = "nein";
	}

	/**
	 * Diese Methode git den Kunden zurück, zu dem die Buchnung gehört hat.
	 * 
	 * @return Instanz vom Typ {@link Kunde}
	 */
	public Kunde getKunde() {
		return kunde;
	}

	/**
	 * Diese Methode definiert den Kunden, zu dem die Buchnung gehört.
	 * 
	 * @param kunde
	 *            Instanz vom Typ {@link Kunde}
	 */
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	/**
	 * Diese Methode gibt die Buchungsnummer zurück.
	 * 
	 * @return Buchungsnummer als ganze Zahl
	 */
	public int getBuchungsnr() {
		return buchungsnr;
	}

	/**
	 * Diese Methode definiert die Buchungsnummer.
	 * 
	 * @param buchungsnr
	 *            Buchungsnummer als ganze Zahl
	 */
	public void setBuchungsnr(int buchungsnr) {
		this.buchungsnr = buchungsnr;
	}

	/**
	 * Diese Methode gibt die Stornonummer zurück.
	 * 
	 * @return Stornonummer als ganze Zahl
	 */
	public int getStornonr() {
		return stornonr;
	}

	/**
	 * Diese Methode definiert die Stornonummer. Außerdem wird die Anzahl der
	 * gebuchten Plaetze reduziert.
	 * 
	 * @param stornonr
	 *            Stornonummer als ganze Zahl
	 * @param stornierte_plaetze
	 *            Anzahl der Plätze, die storniert werden sollen
	 */
	public void storniere(int stornonr, int stornierte_plaetze) {
		this.stornonr = stornonr;
		this.plaetze = this.plaetze - stornierte_plaetze;
	}

	/**
	 * Diese Methode gibt das Reiseziel zurück.
	 * 
	 * @return Reiseziel als String
	 */
	public Reiseziel getReiseZiel() {
		return reiseZiel;
	}

	/**
	 * Diese Methode definiert das Reiseziel.
	 * 
	 * @param reiseZiel
	 *            Reiseziel als String
	 */
	public void setReiseZiel(Reiseziel reiseZiel) {
		this.reiseZiel = reiseZiel;
	}

	/**
	 * Diese Methode gibt die Woche zurück.
	 * 
	 * @return Woche als ganze Zahl
	 */
	public int getWoche() {
		return woche;
	}

	/**
	 * Diese Methode definiert die Woche der Buchung.
	 * 
	 * @param woche
	 *            Woche als ganze Zahl
	 */
	public void setWoche(int woche) {
		this.woche = woche;
	}

	/**
	 * Diese Methode gibt die Anzahl der gebuchten Plätze zurück.
	 * 
	 * @return Anzahl der Plätze als ganze Zahl
	 */
	public int getPlaetze() {
		return plaetze;
	}

	/**
	 * Diese Methode definiert die Anzahl der gebuchten Plätze.
	 * 
	 * @param plaetze
	 *            Anzahl der Plätze als ganze Zahl
	 */
	public void setPlaetze(int plaetze) {
		this.plaetze = plaetze;
	}

	/**
	 * Diese Methode gibt den Aenderungsstatus der Buchung zureck.
	 * 
	 * @return "ja", wenn Buchung korrigiert wurde <br>
	 *         "nein", wenn keine Korrektur druchgefuehrt wurde
	 */
	public String getKorrigiert() {
		return korrigiert;
	}

	/**
	 * Diese Methode setzt den internen String {@link korrigiert} auf den Wert
	 * "ja".
	 * 
	 * @param value
	 *            "ja" oder "nein"
	 */
	public void setKorrigiert(String value) {
		this.korrigiert = value;
	}

}
