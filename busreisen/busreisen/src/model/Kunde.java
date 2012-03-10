package model;

/**
 * Diese Klasse beschreibt einen Kunden, der bei dem Reiseb�ro eine Reise bucht.
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
	 * Der Konstruktor initialisiert die Buchungsnummer und die Wochennummer mit
	 * 0.
	 */
	public Kunde() {
		this.name = "";
		this.vorname = "";
		this.adresse = "";
		this.telefonnr = "";
	}

	/**
	 * Der Konstruktor setzt Initalwerte fuer Name, Vorname, Adresse,
	 * Telefonnummer. Ausserdem werden die Buchungsnummer und die Wochennummer
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

	}

	/**
	 * Diese Methode gibt den Namen des Kunden zurueck.
	 * 
	 * @return Name als String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Diese Methode aendert den Namen des Kunden.
	 * 
	 * @param name
	 *            Name als String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Diese Methode gibt den Vornamen des Kunden zurueck.
	 * 
	 * @return Vorname als String
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * Diese Methode aendert den Vornamen des Kunden.
	 * 
	 * @param vorname
	 *            Vorname als String
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * Diese Methode gibt die Adresse des Kunden zurueck.
	 * 
	 * @return adresse Adresse als String
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Diese Methode aendert die Adresse des Kunden.
	 * 
	 * @param adresse
	 *            Adresse als String
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * Diese Methode gibt die Telefonnummer des Kunden zurueck.
	 * 
	 * @return telefonnr Telefonnummer des Kunden
	 */
	public String getTelefonnr() {
		return telefonnr;
	}

	/**
	 * Diese Methode aendert die Telefonnummer des Kunden.
	 * 
	 * @param telefonnr
	 *            Telefonnummer als String
	 */
	public void setTelefonnr(String telefonnr) {
		this.telefonnr = telefonnr;
	}

	/**
	 * Diese Methode wird, abgeleitet von {@link Object} und hier
	 * ueberschrieben, verwendet, um zwei Kunden-Objekte miteinander vergleichen
	 * zu koennen.
	 */
	public boolean equals(Object object) {
		Kunde kunde = (Kunde) object;
		return ((kunde.name.equals(this.name))
				&& (kunde.vorname.equals(this.vorname))
				&& (kunde.adresse.equals(this.adresse)) && (kunde.telefonnr
					.equals(this.telefonnr)));
	}
}
