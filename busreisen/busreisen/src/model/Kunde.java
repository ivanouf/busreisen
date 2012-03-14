package model;

/**
 * Diese Klasse beschreibt einen Kunden, der bei dem Reisebüro eine Reise bucht.
 * 
 * @author Philipp
 * @version 12.03.2012
 */
public class Kunde {

	/**
	 * Nummer, die den Kunden eindeutig identifiziert
	 */
	private int kdNummer;

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
	 * Der Konstruktor initialisiert die Buchungsnummer und die Wochennummer mit
	 * 0.
	 */
	public Kunde() {
		this.kdNummer = 0;
		this.name = "";
		this.vorname = "";
		this.adresse = "";
		this.telefonnr = "";
	}

	/**
	 * Der Konstruktor setzt Initalwerte für Name, Vorname, Adresse,
	 * Telefonnummer. Außerdem werden die Buchungsnummer und die Wochennummer
	 * mit 0 initialisiert.
	 * 
	 * @param nummer
	 *            Kundennummer als ganze Zahl
	 * @param name
	 *            Name des Kunden als String
	 * @param vorname
	 *            Vorname des Kunden als String
	 * @param adresse
	 *            Adresse des Kunden als String
	 * @param telefonnr
	 *            Telefonnummer des Kunden als String
	 */
	public Kunde(int nummer, String name, String vorname, String adresse,
			String telefonnr) {
		this.kdNummer = nummer;
		this.name = name;
		this.vorname = vorname;
		this.adresse = adresse;
		this.telefonnr = telefonnr;

	}

	/**
	 * Diese Methode gibt die Kundennummer zurück.
	 * 
	 * @return Kundennummer als Integer
	 */
	public int getNummer() {
		return kdNummer;
	}

	/**
	 * Diese Methode setzt die Kundennummer für diesen Kunden.
	 * 
	 * @param kdNummer
	 *            Kundennummer als Integer.
	 */
	public void setNummer(int kdNummer) {
		this.kdNummer = kdNummer;
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
	 * Diese Methode gibt das Kunden-Objekt mit seinen Attributen als String
	 * aus.
	 * 
	 * @return Kundennummer + " " + Kundenname + " " + Kundenvorname + " " +
	 *         Kundenadresse + " " + Telefonnummer
	 */
	public String toString() {
		return (this.kdNummer + " " + this.name + " " + this.vorname + " "
				+ this.adresse + " " + this.telefonnr);
	}

	/**
	 * Diese Methode wird verwendet, um zwei Kunden-Objekte inhaltlich
	 * miteinander vergleichen zu können. Dazu wird die toString()-Methode
	 * verwendet.
	 * 
	 * @return true or false
	 */
	public boolean equals(Object object) {
		Kunde kunde = (Kunde) object;
		return (this.toString().equals(kunde.toString()));
	}
}
