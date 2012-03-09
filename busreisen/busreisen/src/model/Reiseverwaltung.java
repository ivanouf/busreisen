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
public class Reiseverwaltung {

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
	 * Instanz von {@link Kunde}.
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
	public Reiseverwaltung(){
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
		Buchung stornierung;
		int plaetze = -1;
		int antwort = KonsoleIO.readIntegerFromConsole("Geben Sie die Nummer zu der Buchung ein, die Sie stornieren wollen!");
		try {
			stornierung = DateiIO.searchBuchungInLogFile(antwort);
			while((plaetze < 0) || (plaetze>stornierung.getPlaetze()) ){ 
				
				plaetze = KonsoleIO.readIntegerFromConsole("Geben Sie die Anzahl der Plätze ein, die storniert werden sollen!");
				if ((plaetze < 0) || (plaetze>stornierung.getPlaetze())){
					KonsoleIO.printFehlermeldung("Fehlerhafte Eingabe! Wiederhohlen Sie die Eingabe!");
				}
			}
			stornierung.storniere(aktuelleStornoNr, plaetze);
			aktuelleStornoNr++;
			DateiIO.saveBuchungToLogFile(stornierung);
			
		} catch (Exception e) {
			KonsoleIO.printFehlermeldung(INPUT_FEHLERMELDUNG);
		}
		
		
	}
	
	/**
	 * 
	 */
	public void kundenDatenKorrektur(){
		String name = KonsoleIO.readStringFromConsole("Geben Sie den Nachnamen des Kunden ein.");
		String vorname = KonsoleIO.readStringFromConsole("Geben Sie den Vornamen des Kunden ein.");
		Kunde kunden[];
		try {
			kunden = DateiIO.searchKundeInKundenstamm(name,vorname);
			KonsoleIO.readGewuenschterKunde(kunden);
			int pos = KonsoleIO.readGewuenschterKunde(kunden);
			reisender = kunden[pos];
			System.out.println("Nachnamen := 1");
			System.out.println("Vornamen := 2");
			System.out.println("Adresse := 3");
			System.out.println("Telefonnummer := 4");
			int eingabe = KonsoleIO.readIntegerFromConsole("Was möchten Sie verändern? (1-4; 0 = Abbruch)");
//			while(eingabe != 0){
			switch (eingabe){
			case 1:
				name = KonsoleIO.readStringFromConsole("Geben Sie den Namen des Kunden ein!");
				reisender.setName(name);
				break;
			case 2:
				vorname = KonsoleIO.readStringFromConsole("Geben Sie den Vornamen des Kunden ein!");
				reisender.setVorname(vorname);
				break;
			case 3:
				String adresse = KonsoleIO.readStringFromConsole("Geben Sie die Adresse des Kunden ein!");
				reisender.setAdresse(adresse);
				break;
			case 4:
				String telefonnummer = KonsoleIO.readStringFromConsole("Geben Sie die Telefonnummer des Kunden ein!");
				reisender.setTelefonnr(telefonnummer);
				break;
			case 0:
				break;
			default:
				break;
//			}
			}
			DateiIO.saveKundeToCSV(reisender);
		} catch (IOException e) {
			KonsoleIO.printFehlermeldung(INPUT_FEHLERMELDUNG);
		}
	}
	
	/**
	 * 
	 * @param gesuchteBuchungsNr
	 */
	public void buchungsDatenKorrektur(int gesuchteBuchungsNr){
		Kunde kunden[];
		try {
			Buchung buchung = DateiIO.searchBuchungInLogFile(gesuchteBuchungsNr);
			kunden = DateiIO.searchKundeInKundenstamm(buchung.getKunde().getName(),buchung.getKunde().getVorname());
			int pos = KonsoleIO.readGewuenschterKunde(kunden);
			reisender = kunden[pos];
			System.out.println("Nachnamen := 1");
			System.out.println("Vornamen := 2");
			System.out.println("Adresse := 3");
			System.out.println("Telefonnummer := 4");
			System.out.println("Ziel := 5");
			System.out.println("Woche := 6");
			System.out.println("Anzahl der Plaetze := 7");
			int eingabe = KonsoleIO.readIntegerFromConsole("Was möchten Sie ändern?(1-7; 0 = Abbruch)");
//			while ( eingabe != 0 ){
			switch (eingabe){
			case 1:
				String name = KonsoleIO.readStringFromConsole("Geben Sie den Namen des Kunden ein!");
				reisender.setName(name);
				break;
			case 2:
				String vorname = KonsoleIO.readStringFromConsole("Geben Sie den Vornamen des Kunden ein!");
				reisender.setVorname(vorname);
				break;
			case 3:
				String adresse = KonsoleIO.readStringFromConsole("Geben Sie die Adresse des Kunden ein!");
				reisender.setAdresse(adresse);
				break;
			case 4:
				String telefonnummer = KonsoleIO.readStringFromConsole("Geben Sie die Telefonnummer des Kunden ein!");
				reisender.setTelefonnr(telefonnummer);
				break;
			case 5:
				String ziel = KonsoleIO.readStringFromConsole("Geben Sie das Ziel des Kunden ein!");
				buchung.setReiseZiel(Reiseziel.valueOf(ziel));
				break;
			case 6:
				int woche = KonsoleIO.readIntegerFromConsole("Geben Sie die Woche ein, in der der Kunde fahren moechte!");
				buchung.setWoche(woche);
				break;
			case 7:
				int anzahlPlaetze = KonsoleIO.readIntegerFromConsole("Geben Sie die Anzahl der Plätze ein, die der Kunde buchen möchte!");
				buchung.setPlaetze(anzahlPlaetze);
				break;
			default:
				break;
//			case 0:
//				break;
//			}
			}
			
			DateiIO.saveBuchungToLogFile(buchung);
		} catch (Exception e) {
			KonsoleIO.printFehlermeldung(INPUT_FEHLERMELDUNG);
		}
		
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
