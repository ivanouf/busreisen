package model;

import java.io.IOException;

import utils.DateiIO;
import utils.KonsoleIO;

/**
 * Diese Klasse ist für die Verwaltung der Busse zuständig.
 * 
 * @author Thomas
 * @version 06.03.2012
 *
 */
public class Busverwaltung {

	private static final String OUTPUT_FEHLERMELDUNG = "Fehler beim Speichern!";
	
	private static final String INPUT_FEHLERMELDUNG = "Fehler beim Laden!";
	
	/**
	 * Aktuelle Buchungsnummer, die vom System zugewiesen wird.
	 */
	private int aktuelleBuchungsNr;
	
	/**
	 * Aktuelle Stornierungsnummer, die vom System zugewiesen wird.
	 */
	private int aktuelleStornoNr;
	
	/**
	 * 
	 */
	private Kunde reisender;
	
	/**
	 * Buchungsnummer, nach der gesucht werden soll.
	 */
	private int gesuchteBuchungsNr;
	
	/**
	 * Stornierungsnummer, nach der gesucht werden soll.
	 */
	private int gesuchteStornoNr;
	/**
	 * Array von Instanzen der Klasse {@link Reise}.
	 */
	private Reise reise[];
	
	/**
	 * Der Konstruktor initialisiert die Attribute mit festgelegten Werten.
	 */
	public Busverwaltung(){
		this.aktuelleBuchungsNr = 100;
		this.aktuelleStornoNr = 1000;
		this.gesuchteBuchungsNr = 0;
		this.gesuchteStornoNr = 0;
		this.reisender = null;
		try {
			DateiIO.writeHeadersInCSV();
			DateiIO.writeHeadersInLog();
		} catch (IOException e) {
			KonsoleIO.printFehlermeldung(OUTPUT_FEHLERMELDUNG);
		}
		
		
	}
	
	/**
	 * Diese Methode liest die Daten aus der CSV Datei
	 * @return Kunde
	 * 				Gibt einen Kunden zurück
	 */
	public Kunde[] datenLesen(){
		return null;
	}
	
	/**
	 * Diese Methode speichert die Daten.
	 */
	public void datenSpeichern(){
		
	}
	
	/**
	 * Diese Methode gibt an, ob die Buchung erfolgreich war
	 * @return boolean
	 * 				true für erfolgreich
	 * 				false für fehlgeschlagen
	 */
	public void buchen(){
		int antwort = KonsoleIO.readIntegerFromConsole("Wollen Sie zu einem bestehenden Kunden eine Reise buchen? (1 = Ja ; 2 = Nein)");
		if(antwort == 2){
			kundeAnlegen();
			}else{
				String name = KonsoleIO.readStringFromConsole("Geben Sie den Namen des Kunden ein!");
				String vorname = KonsoleIO.readStringFromConsole("Geben Sie den Nachnamen des Kunden ein!");
				reisender = sucheKunde();
			}
		Reiseziel ziel = KonsoleIO.readGewuenschtesReiseziel();
		int woche = 0;
		while((woche <= 0) || (woche > 3)){
			woche = KonsoleIO.readIntegerFromConsole("In welcher Woche wollen Sie fahren? [1-3]");
			}
		Buchung buchung = new Buchung(aktuelleBuchungsNr,ziel,woche,reisender);
		aktuelleBuchungsNr++;
		int plaetze = KonsoleIO.readIntegerFromConsole("Wie viele Plätze möchten der Kunde buchen?");
		buchung.setPlaetze(plaetze);
		try {
			DateiIO.saveBuchungToLogFile(buchung);
		} catch (IOException e) {
			KonsoleIO.printFehlermeldung(OUTPUT_FEHLERMELDUNG);
		}
	}
	
	/**
	 * Diese Methode gibt die {@link Reise} zum gewünschte {@link Reiseziel} zurück.
	 * @param ziel
	 * 				Element von {@link Reiseziel}
	 * @return Instanz von {@link Reise}
	 */
	private Reise getReiseZuZiel(Reiseziel ziel){
		for (int i = 0; i < reise.length; i++) {
			if(reise[i].getReiseziel().equals(ziel)){
				return reise[i];
			}
		}
		return null;
	}
	
	/**
	 * Disese Methode sucht einen Kunden und gibt die spezifizierten Kunden aus.
	 * @return kunde als Kunde 
	 */
	private Kunde sucheKunde(){
		String name = KonsoleIO.readStringFromConsole("Geben Sie den Namen des Kunden ein!");
		String vorname = KonsoleIO.readStringFromConsole("Geben Sie den Nachnamen des Kunden ein!");
		try {
			Kunde kunde[] = DateiIO.searchKundeInKundenstamm(name, vorname);
			if (kunde.length == 1){
				return kunde[0];
			}else{
				int antwort = KonsoleIO.readGewuenschterKunde(kunde);
				return kunde[antwort];
			}
		} catch (IOException e) {
			KonsoleIO.printFehlermeldung(INPUT_FEHLERMELDUNG);
		}
		return null;
	}
	
	/**
	 * Diese Methode legt die Reisen fest.
	 */
	private void reisenAnlegen(){
		reise = new Reise [4];
		reise[0] = new Reise(Reiseziel.BERLIN, Wochentag.FREITAG);
		reise[1] = new Reise(Reiseziel.MADRID,Wochentag.MONTAG);
		reise[2] = new Reise(Reiseziel.ROM,Wochentag.SAMSTAG);
		reise[3] = new Reise(Reiseziel.WIEN,Wochentag.SONNTAG);
		
	}
	
	/**
	 * Gibt an, ob die Stornierung erfolgreich war.
	 * @return boolean
	 * 				true für erfolgreich
	 * 				false für fehlgeschlagen
	 */
	public void stornieren(){
		int antwort = KonsoleIO.readIntegerFromConsole("Geben Sie die Nummer zu der Buchung ein, die Sie stornieren wollen!");
		try {
			Buchung stornierung = DateiIO.searchBuchungInLogFile(aktuelleStornoNr);
		} catch (Exception e) {
			KonsoleIO.printFehlermeldung(INPUT_FEHLERMELDUNG);
		}
		
		aktuelleStornoNr++;
	}
	
	/**
	 * 
	 */
	public void datenKorrektur(){
		
	}
	
	/**
	 * 
	 * @param gesuchteBuchungsNr
	 */
	public void datenKorrektur(int gesuchteBuchungsNr){
		
	}
	
	/**
	 * 
	 * @param gesuchteBuchungsNr
	 * @return
	 */
	public int suchen(int gesuchteBuchungsNr){
		return 0;
	}
	
	/**
	 * Diese Methode legt einen neuen Kunden an.
	 */
	public void kundeAnlegen(){
	
		reisender = new Kunde();
		String name = KonsoleIO.readStringFromConsole("Geben Sie einen Namen für den Kunden ein!");
		reisender.setName(name);
		String vorname = KonsoleIO.readStringFromConsole("Geben Sie einen Vornamen für den Kunden ein!");
		reisender.setVorname(vorname);
		String telefonnr = KonsoleIO.readStringFromConsole("Geben Sie die Telefonnummer des Kunden ein!");
		reisender.setTelefonnr(telefonnr);
		String adresse = KonsoleIO.readStringFromConsole("Geben Sie die Adresse des Kunden ein!");
		reisender.setAdresse(adresse);
		try {
			DateiIO.saveKundeToCSV(reisender);
		} catch (IOException e) {
			KonsoleIO.printFehlermeldung(OUTPUT_FEHLERMELDUNG);
		}
		
	}
}
