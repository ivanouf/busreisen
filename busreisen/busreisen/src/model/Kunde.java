package model;

/**
 * Diese Klasse beschreibt einen Kunden, der bei dem Reisebüro eine Reise bucht.
 * 
 * @author Philipp
 * @version 28.02.2012
 */
public class Kunde {

	/**
	 * Name des Kunden
	 */
	private String name;

	/**
	 * Vorname des Kunden
	 */
	private String vorname;

	/**
	 * Adresse des Kunden
	 */
	private String adresse;

	/**
	 * Telefonnnummer des Kunden
	 */
	private String telefonnr;

	/**
	 * Buchungsnummer für diesen Kunden
	 */
	private int buchungsnr;

	/**
	 * Reiseziel des Kunden
	 */
	private String reiseZiel;

	/**
	 * Vom Kunden gewünschte Reisewoche
	 */
	private int woche;

	/**
	 * Der Konstruktor initialisiert die Buchungsnummer und die Wochennummer mit
	 * 0.
	 */
	public Kunde() {
		this.name = "";
		this.vorname = "";
		this.adresse = "";
		this.telefonnr = "";
		this.buchungsnr = 0;
		this.woche = 0;
	}

	/**
	 * Der Konstruktor setzt Initalwerte für Name, Vorname, Adresse,
	 * Telefonnummer. Außerdem werden die Buchungsnummer und die Wochennummer
	 * mit 0 initialisiert.
	 * 
	 * @param name
	 * @param vorname
	 * @param adresse
	 * @param telefonnr
	 */
	public Kunde(String name, String vorname, String adresse, String telefonnr) {
		this.name = name;
		this.vorname = vorname;
		this.adresse = adresse;
		this.telefonnr = telefonnr;
		this.buchungsnr = 0;
		this.woche = 0;
	}

	/**
	 * Diese Methode gibt den Namen des Kunden zurück.
	 * 
	 * @return Name als String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Diese Methode ändert den Namen des Kunden.
	 * 
	 * @param name
	 *            Name als String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Diese Methode gibt den Vornamen des Kunden zurück.
	 * 
	 * @return Vorname als String
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * Diese Methode ändert den Vornamen des Kunden.
	 * 
	 * @param vorname
	 *            Vorname als String
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * Diese Methode gibt die Adresse des Kunden zurück.
	 * 
	 * @return adresse Adresse als String
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Diese Methode ändert die Adresse des Kunden.
	 * 
	 * @param adresse
	 *            Adresse als String
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * Diese Methode gibt die Telefonnummer des Kunden zurück.
	 * 
	 * @return telefonnr Telefonnummer des Kunden
	 */
	public String getTelefonnr() {
		return telefonnr;
	}

	/**
	 * Diese Methode ändert die Telefonnummer des Kunden.
	 * 
	 * @param telefonnr
	 *            Telefonnummer als String
	 */
	public void setTelefonnr(String telefonnr) {
		this.telefonnr = telefonnr;
	}

	/**
	 * Diese Methode gibt die Buchungsnummer des Kunden zurück.
	 * 
	 * @return buchungsnr Buchungsnummer als Integer
	 */
	public int getBuchungsnr() {
		return buchungsnr;
	}

	/**
	 * Diese Methode ändert die Buchungsnummer des Kunden.
	 * 
	 * @param buchungsnr
	 *            Buchungsnummer als Integer
	 * 
	 */
	public void setBuchungsnr(int buchungsnr) {
		this.buchungsnr = buchungsnr;
	}

	/**
	 * Diese Methode gibt das Reiseziel des Kunden zurück.
	 * 
	 * @return reiseZiel Reiseziel als String
	 */
	public String getReiseZiel() {
		return reiseZiel;
	}

	/**
	 * Diese Methode ändert das Reiseziel des Kunden.
	 * 
	 * @param reiseZiel
	 *            Reiseziel als String
	 */
	public void setReiseZiel(String reiseZiel) {
		this.reiseZiel = reiseZiel;
	}

	/**
	 * Diese Methode gibt die Woche zurück, die der Kunde gebucht hat.
	 * 
	 * @return woche Buchungswoche als Integer
	 */
	public int getWoche() {
		return woche;
	}

	/**
	 * Diese Methode ändert die Woche, die der Kunde gebucht hat.
	 * 
	 * @param woche
	 *            Buchungswoche als Integer
	 */
	public void setWoche(int woche) {
		this.woche = woche;
	}

}
