package model;

import java.io.File;
import java.io.IOException;

import utils.DateiIO;
import utils.KonsoleIO;

/**
 * Diese Klasse ist fuer die Verwaltung der Busse zustaendig.
 * 
 * @author Thomas + Phiilpp
 * @version 09.03.2012
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
	 * Array von Instanzen der Klasse {@link Reise}, in dem die Reisen, die die
	 * Firma anbietet, abgelegt sind
	 */
	private Reise reise[];

	/**
	 * Der Konstruktor initialisiert die Attribute mit festgelegten Werten und
	 * die Reisen, die gebucht werden koennen. Außerdem werden beim allerersten
	 * Programmstart die CSV-Dateien fuer die interne Verwaltung angelegt.
	 */
	public Reiseverwaltung() {
		this.aktuelleBuchungsNr = 100;
		this.aktuelleStornoNr = 1000;
		this.reisender = null;
		reisenAnlegen();

		// Anlegen der CSV Dateien
		try {
			File file = new File(DateiIO.LOGFILE);
			if (!(file.exists())) {
				DateiIO.writeHeadersInLog();
			}
			file = new File(DateiIO.KUNDEN_FILE);
			if (!(file.exists())) {
				DateiIO.writeHeadersInKundenFile();
			}
		} catch (IOException e) {
			KonsoleIO.printFehlermeldung(OUTPUT_FEHLERMELDUNG);
		}
	}

	/**
	 * Diese Methode gibt an, ob die Buchung erfolgreich war
	 * 
	 * @return boolean true fuer erfolgreich false fuer fehlgeschlagen
	 */
	public void buchen() {

		int antwort = KonsoleIO
				.readIntegerFromConsole("Wollen Sie zu einem bestehenden Kunden eine Reise buchen? (1 = Ja ; 2 = Nein)");

		if (antwort == 2) {
			kundeAnlegen();
		} else {
			reisender = sucheKunde();
		}

		Reiseziel ziel = KonsoleIO.readGewuenschtesReiseziel();
		int woche = 0;
		while ((woche <= 0) || (woche > 3)) {
			woche = KonsoleIO
					.readIntegerFromConsole("In welcher Woche moechte der Kunde fahren? [1-3]");
		}

		Buchung buchung = new Buchung(aktuelleBuchungsNr, ziel, woche,
				reisender);
		aktuelleBuchungsNr++;
		int plaetze = KonsoleIO
				.readIntegerFromConsole("Wie viele Plaetze moechte der Kunde buchen?");
		buchung.setPlaetze(plaetze);

		try {
			Reise reise = getReiseZuZiel(ziel);
			if (reise.buchungOK(buchung)) {
				DateiIO.saveBuchungToLogFile(buchung);
			} else {
				KonsoleIO
						.printFehlermeldung("Die Reise kann nicht ueberbucht werden!");
			}
		} catch (IOException e) {
			KonsoleIO.printFehlermeldung(OUTPUT_FEHLERMELDUNG);
		}
	}

	/**
	 * Diese Methode gibt die {@link Reise} zum gewuenschten {@link Reiseziel}
	 * zurueck.
	 * 
	 * @param ziel
	 *            Element von {@link Reiseziel}
	 * @return Instanz von {@link Reise}
	 */
	private Reise getReiseZuZiel(Reiseziel ziel) {
		for (int i = 0; i < reise.length; i++) {
			if (reise[i].getReiseziel().equals(ziel)) {
				return reise[i];
			}
		}
		return null;
	}

	/**
	 * Disese Methode sucht einen Kunden und gibt die spezifizierten Kunden aus.
	 * 
	 * @return kunde als Kunde
	 */
	private Kunde sucheKunde() {
		String name = KonsoleIO
				.readStringFromConsole("Geben Sie den Namen des Kunden ein!");
		String vorname = KonsoleIO
				.readStringFromConsole("Geben Sie den Nachnamen des Kunden ein!");
		try {
			Kunde kunde[] = DateiIO.searchKundeInKundenstamm(name, vorname);
			if (kunde.length == 1) {
				return kunde[0];
			} else {
				int antwort = KonsoleIO.readGewuenschterKunde(kunde);
				return kunde[antwort];
			}
		} catch (Exception e) {
			KonsoleIO.printFehlermeldung(INPUT_FEHLERMELDUNG);
		}
		return null;
	}

	/**
	 * Diese Methode legt die Reisen fest.
	 */
	private void reisenAnlegen() {
		reise = new Reise[4];
		reise[0] = new Reise(Reiseziel.BERLIN, Wochentag.FREITAG);
		reise[1] = new Reise(Reiseziel.MADRID, Wochentag.MONTAG);
		reise[2] = new Reise(Reiseziel.ROM, Wochentag.SAMSTAG);
		reise[3] = new Reise(Reiseziel.WIEN, Wochentag.SONNTAG);

	}

	/**
	 * Gibt an, ob die Stornierung erfolgreich war.
	 * 
	 * @return boolean true fuer erfolgreich false fuer fehlgeschlagen
	 */
	public void stornieren() {
		Buchung stornierung;
		int plaetze = -1;
		int antwort = KonsoleIO
				.readIntegerFromConsole("Geben Sie die Nummer zu der Buchung ein, die Sie stornieren wollen!");
		try {
			stornierung = DateiIO.searchBuchungInLogFile(antwort);
			while ((plaetze < 0) || (plaetze > stornierung.getPlaetze())) {

				plaetze = KonsoleIO
						.readIntegerFromConsole("Geben Sie die Anzahl der Pl�tze ein, die storniert werden sollen!");
				if ((plaetze < 0) || (plaetze > stornierung.getPlaetze())) {
					KonsoleIO
							.printFehlermeldung("Fehlerhafte Eingabe! Wiederhohlen Sie die Eingabe!");
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
	 * Diese Methode veraendert Daten in einen Kunden und speichert diese.
	 */
	public void kundenDatenKorrektur() {
		String name = KonsoleIO
				.readStringFromConsole("Geben Sie den Nachnamen des Kunden ein.");
		String vorname = KonsoleIO
				.readStringFromConsole("Geben Sie den Vornamen des Kunden ein.");
		Kunde kunden[];
		try {
			kunden = DateiIO.searchKundeInKundenstamm(name, vorname);
			KonsoleIO.readGewuenschterKunde(kunden);
			int pos = KonsoleIO.readGewuenschterKunde(kunden);
			reisender = kunden[pos];
			System.out.println("Nachnamen \t\t:= \t1");
			System.out.println("Vornamen \t\t:= \t2");
			System.out.println("Adresse \t\t:= \t3");
			System.out.println("Telefonnummer \t:= \t4");
			int eingabe = KonsoleIO
					.readIntegerFromConsole("Was m�chten Sie ver�ndern? (1-4; 0 = Abbruch)");
			// while(eingabe != 0){
			switch (eingabe) {
			case 1:
				name = KonsoleIO
						.readStringFromConsole("Geben Sie den Namen des Kunden ein!");
				reisender.setName(name);
				break;
			case 2:
				vorname = KonsoleIO
						.readStringFromConsole("Geben Sie den Vornamen des Kunden ein!");
				reisender.setVorname(vorname);
				break;
			case 3:
				String adresse = KonsoleIO
						.readStringFromConsole("Geben Sie die Adresse des Kunden ein!");
				reisender.setAdresse(adresse);
				break;
			case 4:
				String telefonnummer = KonsoleIO
						.readStringFromConsole("Geben Sie die Telefonnummer des Kunden ein!");
				reisender.setTelefonnr(telefonnummer);
				break;
			case 0:
				break;
			default:
				break;
			// }
			}
			DateiIO.saveKundeToKundenstamm(reisender);
		} catch (Exception e) {
			KonsoleIO.printFehlermeldung(INPUT_FEHLERMELDUNG);
		}
	}

	/**
	 * Diese Methode veraendert Daten in einer Buchung und speichert diese ab.
	 */
	public void buchungsDatenKorrektur() {
		Kunde kunden[];
		try {
			int gesuchteBuchungsNr = KonsoleIO
					.readIntegerFromConsole("Geben Sie die Buchungsnummer ein.");
			Buchung buchung = DateiIO
					.searchBuchungInLogFile(gesuchteBuchungsNr);

			if (buchung != null) {
				// Wenn die Buchung vorhanden ist, wird der zugehoerige Kunde
				// gesucht.
				kunden = DateiIO.searchKundeInKundenstamm(buchung.getKunde()
						.getName(), buchung.getKunde().getVorname());
				int pos = KonsoleIO.readGewuenschterKunde(kunden);
				reisender = kunden[pos];

				// Zuerst wird die Buchung detailliert ausgegeben.
				System.out.println("Nachnamen \t:= \t1");
				System.out.println("Vornamen \t:= \t2");
				System.out.println("Adresse \t\t:= \t3");
				System.out.println("Telefonnummer \t:= \t4");
				System.out.println("Ziel \t\t:= \t5");
				System.out.println("Woche \t\t:= \t6");
				System.out.println("Anzahl der Plaetze \t:= \t7");

				// Danach startet ein Eingabedialog, mit dessen Hilfe der Nutzer
				// Angaben aendern kann.
				int eingabe = KonsoleIO
						.readIntegerFromConsole("Was moechten Sie aendern?(1-7; 0 = Abbruch)");
				// while ( eingabe != 0 ){
				switch (eingabe) {
				case 1:
					String name = KonsoleIO
							.readStringFromConsole("Geben Sie den Namen des Kunden ein!");
					reisender.setName(name);
					break;
				case 2:
					String vorname = KonsoleIO
							.readStringFromConsole("Geben Sie den Vornamen des Kunden ein!");
					reisender.setVorname(vorname);
					break;
				case 3:
					String adresse = KonsoleIO
							.readStringFromConsole("Geben Sie die Adresse des Kunden ein!");
					reisender.setAdresse(adresse);
					break;
				case 4:
					String telefonnummer = KonsoleIO
							.readStringFromConsole("Geben Sie die Telefonnummer des Kunden ein!");
					reisender.setTelefonnr(telefonnummer);
					break;
				case 5:
					String ziel = KonsoleIO
							.readStringFromConsole("Geben Sie das Ziel des Kunden ein!");
					buchung.setReiseZiel(Reiseziel.valueOf(ziel));
					break;
				case 6:
					int woche = KonsoleIO
							.readIntegerFromConsole("Geben Sie die Woche ein, in der der Kunde fahren moechte!");
					buchung.setWoche(woche);
					break;
				case 7:
					int anzahlPlaetze = KonsoleIO
							.readIntegerFromConsole("Geben Sie die Anzahl der Pl�tze ein, die der Kunde buchen m�chte!");
					buchung.setPlaetze(anzahlPlaetze);
					break;
				default:
					break;
				// case 0:
				// break;
				// }
				}

				// Zum Schluss wird die Buchung in der Logdatei gespeichert.
				DateiIO.saveBuchungToLogFile(buchung);
			} else {
				KonsoleIO
						.printFehlermeldung("Die gesuchte Buchung ist nicht vorhanden!");
			}
		} catch (Exception e) {
			KonsoleIO.printFehlermeldung(INPUT_FEHLERMELDUNG);
		}

	}

	/**
	 * Diese Methode sucht eine Buchung und gibt diese auf der Konsole aus.
	 */
	public void sucheBuchung() {
		Buchung buchung;
		try {
			// Die gesuchte Buchungsnummer wird von der Konsole eingelesen.
			int aktuelleBuchungsNr = KonsoleIO
					.readIntegerFromConsole("Geben Sie die Buchungsnummer ein.");
			buchung = DateiIO.searchBuchungInLogFile(aktuelleBuchungsNr);

			// Wenn die Buchung vorhanden ist, werden ihre Attribute auf der
			// Konsole ausgegeben.
			if (buchung != null) {
				System.out.println("Buchungsnummer:\t\t"
						+ buchung.getBuchungsnr());
				System.out
						.println("Name:\t\t\t" + buchung.getKunde().getName());
				System.out.println("Vorname:\t\t\t"
						+ buchung.getKunde().getVorname());
				System.out.println("Adresse:\t\t\t"
						+ buchung.getKunde().getAdresse());
				System.out.println("Telefonnummer:\t\t"
						+ buchung.getKunde().getTelefonnr());
				System.out.println("Reiseziel:\t\t\t"
						+ buchung.getReiseZiel().toString());
				System.out.println("Woche:\t\t\t" + buchung.getWoche());
				System.out.println("Plaetze:\t\t\t"
						+ String.valueOf(buchung.getPlaetze()));
			} else {
				KonsoleIO
						.printFehlermeldung("Die gesuchte Buchung ist nicht vorhanden!");
			}
		} catch (Exception e) {
			KonsoleIO.printFehlermeldung(INPUT_FEHLERMELDUNG);

		}

	}

	/**
	 * Diese Methode legt einen neuen Kunden an.
	 */
	public void kundeAnlegen() {
		// Nacheinander werden die Attribute, die fuer einen Kunden wichtig
		// sind, nacheinander von der Konsole eingelesen.
		reisender = new Kunde();
		String name = KonsoleIO
				.readStringFromConsole("Geben Sie einen Namen fuer den Kunden ein!");
		reisender.setName(name);
		String vorname = KonsoleIO
				.readStringFromConsole("Geben Sie einen Vornamen fuer den Kunden ein!");
		reisender.setVorname(vorname);
		String telefonnr = KonsoleIO
				.readStringFromConsole("Geben Sie die Telefonnummer des Kunden ein!");
		reisender.setTelefonnr(telefonnr);
		String adresse = KonsoleIO
				.readStringFromConsole("Geben Sie die Adresse des Kunden ein!");
		reisender.setAdresse(adresse);

		// Zum Schluss wird der Kunde im Kundenstamm abgelegt.
		try {
			DateiIO.saveKundeToKundenstamm(reisender);
		} catch (IOException e) {
			KonsoleIO.printFehlermeldung(OUTPUT_FEHLERMELDUNG);
		}

	}
}
