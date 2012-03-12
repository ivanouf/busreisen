package model;

/**
 * Diese Klasse modelliert eine Buchung, die von einem Mitarbeiter des
 * Reisebüros in das Programm eingegeben wird.
 * 
 * @author Philipp
 * @version 12.03.2012
 */
public class Buchung {

	/**
	 * Instanz von {@link Kunde}, auf die sich die Buchung bezieht.
	 */
	private Kunde kunde;

	/**
	 * Buchungsnummer fuer diesen Kunden
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
	 * Vom Kunden gew�nschte Reisewoche
	 */
	private int woche;

	/**
	 * Vom Kunden gebuchte Anzahl der Pl�tze
	 */
	private int plaetze;

	/**
	 * Dieser Konstruktor erstellt eine leere Buchung.
	 */
	public Buchung() {
		this.buchungsnr = 0;
		this.reiseZiel = null;
		this.woche = 0;
		this.kunde = null;
		this.stornonr = 0;
	}

	/**
	 * Im Konstruktor wird die Buchung �ber folgende Parameter definiert.
	 * 
	 * @param buchungsnr
	 *            vom System zugewiesene Buchungsnummer
	 * @param reiseZiel
	 *            gew�nschtes Reiseziel des Kunden
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
	}

	/**
	 * Diese Methode git den Kunden zur�ck, zu dem die Buchnung geh�rt hat.
	 * 
	 * @return Instanz vom Typ {@link Kunde}
	 */
	public Kunde getKunde() {
		return kunde;
	}

	/**
	 * Diese Methode definiert den Kunden, zu dem die Buchnung geh�rt.
	 * 
	 * @param kunde
	 *            Instanz vom Typ {@link Kunde}
	 */
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	/**
	 * Diese Methode gibt die Buchungsnummer zur�ck.
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
	 * Diese Methode gibt die Stornonummer zur�ck.
	 * 
	 * @return Stornonummer als ganze Zahl
	 */
	public int getStornonr() {
		return stornonr;
	}

	/**
	 * Diese Methode setzt die Stornierungsnummer zu dieser Buchung.
	 * 
	 * @param stornonr
	 *            Stornonummer als ganze Zahl
	 */
	public void setStornonr(int stornonr) {
		this.stornonr = stornonr;
	}

	/**
	 * Diese Methode definiert die Stornonummer. Au�erdem wird die Anzahl der
	 * gebuchten Plaetze reduziert.
	 * 
	 * @param stornonr
	 *            Stornonummer als ganze Zahl
	 * @param stornierte_plaetze
	 *            Anzahl der Pl�tze, die storniert werden sollen
	 */
	public void storniere(int stornonr, int stornierte_plaetze) {
		this.stornonr = stornonr;
		this.plaetze = this.plaetze - stornierte_plaetze;
	}

	/**
	 * Diese Methode gibt das Reiseziel zur�ck.
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
	 * Diese Methode gibt die Woche zur�ck.
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
	 * Diese Methode gibt die Anzahl der gebuchten Pl�tze zur�ck.
	 * 
	 * @return Anzahl der Pl�tze als ganze Zahl
	 */
	public int getPlaetze() {
		return plaetze;
	}

	/**
	 * Diese Methode definiert die Anzahl der gebuchten Pl�tze.
	 * 
	 * @param plaetze
	 *            Anzahl der Pl�tze als ganze Zahl
	 */
	public void setPlaetze(int plaetze) {
		this.plaetze = plaetze;
	}

	/**
	 * Diese Methode prueft, ob zwei Buchungen hinsichtlich Kunde, Reiseziel,
	 * Woche und gebuchten Plaetze uebereinstimmen.
	 * 
	 * @param object
	 *            Buchung, mit der verglichen werden soll
	 * @return true oder false
	 */
	public boolean equals(Object object) {
		Buchung b = (Buchung) object;
		if ((this.kunde.equals(b.getKunde()))
				&& (this.plaetze == b.getPlaetze())
				&& (this.woche == b.getWoche())
				&& (this.reiseZiel.equals(b.getReiseZiel()))) {
			return true;
		} else {
			return false;
		}
	}
}
