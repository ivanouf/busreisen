package model;

import java.io.File;
import java.io.IOException;

import utils.DateiIO;
import utils.KonsoleIO;

/**
 * Diese Klasse ist fuer die Verwaltung der Busse zustaendig.
 * 
 * @author Thomas + Philipp
 * @version 15.03.2012
 * 
 */
public class Reiseverwaltung {

	/**
	 * Standardisierte Fehlermeldung fuer das Speichern der Dateien
	 * 
	 * @value "Fehler beim Speichern!"
	 */
	private static final String OUTPUT_FEHLERMELDUNG = "Fehler beim Speichern!";

	/**
	 * Standardisierte Fehlermeldung fuer das Laden der Dateien
	 * 
	 * @value "Fehler beim Laden!"
	 */
	private static final String INPUT_FEHLERMELDUNG = "Fehler beim Laden!";

	/**
	 * Aktuelle Buchungsnummer (dreistellig), die vom System zugewiesen wird.
	 */
	private int aktuelleBuchungsNr;

	/**
	 * Aktuelle Stornierungsnummer (vierstellig), die vom System zugewiesen
	 * wird.
	 */
	private int aktuelleStornoNr;

	/**
	 * Aktuelle Kundennummer (mit 1 beginnend), die vom System zugewiesen wird.
	 */
	private int aktuelleKundenNr;

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
	 * die Reisen, die gebucht werden k�nnen. Au�erdem werden beim allerersten
	 * Programmstart die CSV-Dateien f�r die interne Verwaltung angelegt.
	 */
	public Reiseverwaltung() {
		this.aktuelleKundenNr = 1;
		this.aktuelleBuchungsNr = 100;
		this.aktuelleStornoNr = 1000;
		this.reisender = null;
		reisenAnlegen();

		// Bei der ersten Sitzung m�ssen die CSV-Dateien angelegt werden.
		try {
			File file = new File(DateiIO.LOGFILE);
			if (!(file.exists())) {
				DateiIO.writeHeadersInLogFile();
			}

			int aktuelleNummern[] = DateiIO.readNummernVonLetzterSitzung();
			aktuelleKundenNr = aktuelleNummern[0];
			aktuelleBuchungsNr = aktuelleNummern[1];
			aktuelleStornoNr = aktuelleNummern[2];

			file = new File(DateiIO.KUNDEN_FILE);
			if (!(file.exists())) {
				DateiIO.writeHeadersInKundenFile();
			}
		} catch (IOException e) {
			KonsoleIO.printFehlermeldung(OUTPUT_FEHLERMELDUNG);
		}
	}

	/**
	 * Diese Methode f�hrt eine Buchung durch. Zuerst wird der Kunde gesucht
	 * bzw. angelegt, wenn er noch nicht im Kundenstamm vorhanden ist.
	 * 
	 * @return boolean true f�r erfolgreich <br>
	 *         false f�r fehlgeschlagen
	 */
	public void buchen() {
		// Kunden anlegen oder suchen
		reisender = null;
		while (reisender == null) {
			int antwort = KonsoleIO
					.readIntegerFromConsole("Wollen Sie zu einem bestehenden Kunden eine Reise buchen? (1 = Ja ; 2 = Nein)");
			if (antwort == 2) {
				kundeAnlegen();
			} else {
				reisender = sucheKunde();
			}
		}

		// Reiseziel und gew�nschte Woche einlesen
		Reiseziel ziel = KonsoleIO
				.readGewuenschtesReiseziel("Wohin m�chte der Kunde reisen?");
		int woche = 0;
		while ((woche <= 0) || (woche > 3)) {
			woche = KonsoleIO
					.readIntegerFromConsole("In welcher Woche m�chte der Kunde fahren? [1-3]");
		}

		// Gebuchte Pl�tze bestimmen
		int plaetze = 0;
		do {
			plaetze = KonsoleIO
					.readIntegerFromConsole("Wie viele Pl�tze m�chte der Kunde buchen?");
			if (plaetze <= 0) {
				KonsoleIO
						.printFehlermeldung("Bitte geben Sie eine positive ganze Zahl ein!");
			}
		} while (plaetze <= 0);

		// Buchung mit aktueller Nummer erstellen
		Buchung buchung = new Buchung(aktuelleBuchungsNr, ziel, woche,
				reisender, plaetze);

		// �berpr�fen, ob die Buchung durchgef�hrt werden kann.
		// Wenn ja, wird sie durchgefuehrt und in der Logdatei gespeichert.
		// Ansonsten erscheint eine Fehlermeldung.
		try {
			Reise reise = getReiseZuZiel(ziel);
			if (reise.buchungOK(buchung)) {
				reise.aktualisiereNachBuchung(buchung);
				DateiIO.saveBuchungToLogFile(buchung);
				KonsoleIO
						.printErfolgsmeldung("Buchung erfolgreich unter der Buchungsnummer "
								+ aktuelleBuchungsNr + " angelegt.");
				aktuelleBuchungsNr++;
			} else {
				KonsoleIO
						.printFehlermeldung("Die Reise kann nicht �berbucht werden!");
			}
		} catch (Exception e) {
			KonsoleIO.printFehlermeldung(OUTPUT_FEHLERMELDUNG);
		}
	}

	/**
	 * Diese Methode gibt die {@link Reise} zum gew�nschten {@link Reiseziel}
	 * zur�ck.
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
	 * Disese Methode sucht einen Kunden nach Name und Vorname. Dann werden
	 * seine Daten ausgegeben.
	 * 
	 * @return Instanz von {@link Kunde}
	 */
	private Kunde sucheKunde() {
		// Name und Vorname des Kunden werden eingelesen.
		String name = KonsoleIO
				.readStringFromConsole("Geben Sie den Namen des Kunden ein!");
		String vorname = KonsoleIO
				.readStringFromConsole("Geben Sie den Vornamen des Kunden ein!");

		// Es wird eine Suchanfrage an den Kundenstamm gesendet.
		try {
			Kunde kunde[] = DateiIO.searchKundeInKundenstamm(name, vorname);
			// Wenn es genau einen passenden Eintrag gibt, wird dieser
			// zur�ckgegeben.
			// Ansonsten wird gefragt, welcher Kunde gemeint ist.
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
	 * Diese Methode legt die Reisen fest. Wenn dies nicht die erste Sitzung
	 * ist, wird die Busbelegung von der letzten Sitzung mithilfe der Logdatei
	 * geladen.
	 */
	private void reisenAnlegen() {
		reise = new Reise[4];
		reise[0] = new Reise(Reiseziel.BERLIN, Wochentag.FREITAG);
		reise[1] = new Reise(Reiseziel.MADRID, Wochentag.MONTAG);
		reise[2] = new Reise(Reiseziel.ROM, Wochentag.SAMSTAG);
		reise[3] = new Reise(Reiseziel.WIEN, Wochentag.SONNTAG);

		File file = new File(DateiIO.LOGFILE);
		if (file.exists()) {
			reise[0].ladeBusbelegung();
			reise[1].ladeBusbelegung();
			reise[2].ladeBusbelegung();
			reise[3].ladeBusbelegung();
		}
	}

	/**
	 * Diese Methode fuehrt eine Stornierung durch.
	 */
	public void stornieren() {
		Buchung buchung, stornierung;
		int storniertePlaetze = -1;
		int aktuellePlaetze = 0;

		// Die Buchung, die storniert werden soll, muss gefunden werden.
		int antwort = KonsoleIO
				.readIntegerFromConsole("Geben Sie die Nummer zu der Buchung ein, die Sie stornieren wollen!");
		try {
			buchung = DateiIO.searchBuchungInLogFile(antwort);
			stornierung = DateiIO.searchStornierungZurBuchung(buchung);

			// Wenn die Buchung schon einmal storniert wurde, m�ssen die
			// aktuellen Pl�tze, die gebucht sind, ermittelt werden.
			if (stornierung != null) {
				aktuellePlaetze = buchung.getPlaetze()
						- stornierung.getPlaetze();
			} else {
				aktuellePlaetze = buchung.getPlaetze();
			}

			while ((storniertePlaetze < 0)
					|| (storniertePlaetze > aktuellePlaetze)) {
				storniertePlaetze = KonsoleIO
						.readIntegerFromConsole("Geben Sie die Anzahl der Pl�tze ein, die storniert werden sollen!");
				// Abfangen der Fehlereignisse "negative Anzahl von Pl�tzen"
				// und "Unterdeckung"
				if ((storniertePlaetze < 0)
						|| (storniertePlaetze > aktuellePlaetze)) {
					KonsoleIO
							.printFehlermeldung("Fehlerhafte Eingabe! Wiederholen Sie die Eingabe!");
				}
			}
			// Wenn alles in Ordnung ist, kann storniert werden.
			buchung.storniere(aktuelleStornoNr, storniertePlaetze);
			aktuelleStornoNr++;

			// Synchronisierung mit der entsprechenden Reise
			Reise reise = getReiseZuZiel(buchung.getReiseZiel());
			reise.aktualisiereNachBuchung(buchung);
			DateiIO.saveBuchungToLogFile(buchung);

		} catch (Exception e) {
			KonsoleIO.printFehlermeldung(INPUT_FEHLERMELDUNG);
		}

	}

	/**
	 * Diese Methode veraendert Daten in einen Kunden und speichert diese.
	 */
	public void kundenDatenKorrektur() {
		// Der betreffende Kunde wird mithilfe Name und Vorname gesucht.
		String name = KonsoleIO
				.readStringFromConsole("Geben Sie den Nachnamen des Kunden ein.");
		String vorname = KonsoleIO
				.readStringFromConsole("Geben Sie den Vornamen des Kunden ein.");
		Kunde kunden[];
		try {
			kunden = DateiIO.searchKundeInKundenstamm(name, vorname);
			int pos = KonsoleIO.readGewuenschterKunde(kunden);
			reisender = kunden[pos];

			// Der User soll mithilfe von Zahlen angegeben, was er �ndern
			// m�chte.
			System.out.println("Nachname [1]");
			System.out.println("Vornamen [2]");
			System.out.println("Adresse  [3]");
			System.out.println("Telefonnummer [4]");

			int eingabe = KonsoleIO
					.readIntegerFromConsole("Was m�chten Sie ver�ndern? (1-4; 0 = Abbruch)");
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
			}

			// Zum Schluss werden die �nderungen im Kundenstamm gespeichert.
			DateiIO.changeKundeInKundenstamm(reisender);
			// Alle Buchungen zu diesem Kunden werden geupdated.
			DateiIO.updateBuchungenZuKunde(reisender);
			KonsoleIO.printErfolgsmeldung("Kundendaten erfolgreich ge�ndert!");
		} catch (Exception e) {
			KonsoleIO.printFehlermeldung(INPUT_FEHLERMELDUNG);
		}
	}

	/**
	 * Diese Methode ver�ndert Daten in einer Buchung und speichert diese ab.
	 */
	public void buchungsDatenKorrektur() {
		try {
			int gesuchteBuchungsNr = KonsoleIO
					.readIntegerFromConsole("Geben Sie die Buchungsnummer ein.");
			Buchung alteBuchung = DateiIO
					.searchBuchungInLogFile(gesuchteBuchungsNr);

			// Wenn eine Buchung mit der gesuchten Buchungsnummer existiert
			if (alteBuchung != null) {

				// Pr�fe, ob es zu dieser Buchung bereits eine Stornierung gibt
				Buchung stornierungVonAlterBuchung = DateiIO
						.searchStornierungZurBuchung(alteBuchung);

				if (stornierungVonAlterBuchung != null) {
					// Wenn es eine Stornierung gibt, pr�fe, wie viele Pl�tze
					// noch tats�chlich gebucht sind.
					int restliche_plaetze = alteBuchung.getPlaetze()
							- stornierungVonAlterBuchung.getPlaetze();

					if (restliche_plaetze > 0) {
						// Wenn noch Pl�tze gebucht sind, storniere diese
						// zus�tzlich.
						alteBuchung.storniere(aktuelleStornoNr,
								restliche_plaetze);
						aktuelleStornoNr++;

						Reise reise = getReiseZuZiel(alteBuchung.getReiseZiel());
						reise.aktualisiereNachBuchung(alteBuchung);
					} else {
						KonsoleIO
								.printErfolgsmeldung("Diese Buchung wurde bereits vollst�ndig storniert, deshalb kann sie nicht ge�ndert werden.");
					}
				}

				else {
					// Wenn es noch keine Stornierung gibt, storniere die
					// Buchung vollst�ndig.
					alteBuchung.storniere(aktuelleStornoNr,
							alteBuchung.getPlaetze());
					aktuelleStornoNr++;

					Reise reise = getReiseZuZiel(alteBuchung.getReiseZiel());
					reise.aktualisiereNachBuchung(alteBuchung);

					DateiIO.saveBuchungToLogFile(alteBuchung);
				}

				// Nachdem die alte Buchung komplett storniert wurde, kann nun
				// eine neue Buchung auf Basis der Daten der alten Buchung
				// angelegt werden.
				Buchung neueBuchung = new Buchung(aktuelleBuchungsNr,
						alteBuchung.getReiseZiel(), alteBuchung.getWoche(),
						alteBuchung.getKunde(), alteBuchung.getPlaetze());
				// Au�erdem wird das Reise-Objekt geladen.
				Reise reise = getReiseZuZiel(neueBuchung.getReiseZiel());

				// Eine Nummercode-Abfrage, was ge�ndert werden soll.
				System.out.println("Ziel [1]");
				System.out.println("Woche [2]");
				System.out.println("Anzahl der Pl�tze [3]");

				// Danach startet ein Eingabedialog, mit dessen Hilfe der Nutzer
				// Angaben �ndern kann. Dieser ist dem eigentlichen Buchvorgang
				// sehr �hnlich.
				int eingabe = KonsoleIO
						.readIntegerFromConsole("Was m�chten Sie �ndern?(1-3; 0 = Abbruch)");
				switch (eingabe) {
				case 1:
					Reiseziel ziel = KonsoleIO
							.readGewuenschtesReiseziel("Geben Sie das Ziel des Kunden ein!");
					neueBuchung.setReiseZiel(ziel);
					reise = getReiseZuZiel(neueBuchung.getReiseZiel());
					break;
				case 2:
					int woche = 0;
					while ((woche <= 0) || (woche > 3)) {
						woche = KonsoleIO
								.readIntegerFromConsole("In welcher Woche m�chte der Kunde fahren? [1-3]");
					}
					neueBuchung.setWoche(woche);
					break;
				case 3:
					do {
						int anzahlPlaetze = KonsoleIO
								.readIntegerFromConsole("Geben Sie die Anzahl der Pl�tze ein, die der Kunde buchen m�chte!");
						neueBuchung.setPlaetze(anzahlPlaetze);

						if (!reise.buchungOK(neueBuchung)) {
							KonsoleIO
									.printFehlermeldung("Die Reise kann nicht �berbucht werden!");
						}

					} while (!reise.buchungOK(neueBuchung));
					break;
				default:
					break;
				}

				// Die Busbelegung wird aktualisiert. Anschlie�end wird die
				// Buchung in der Logdatei gespeichert.
				reise.aktualisiereNachBuchung(neueBuchung);
				DateiIO.saveBuchungToLogFile(neueBuchung);

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
			int gesuchteBuchungsNr = KonsoleIO
					.readIntegerFromConsole("Geben Sie die Buchungsnummer ein.");
			buchung = DateiIO.searchBuchungInLogFile(gesuchteBuchungsNr);

			// Wenn die Buchung vorhanden ist, werden ihre Attribute auf der
			// Konsole ausgegeben.
			if (buchung != null) {
				System.out
						.println("Buchungsnummer: " + buchung.getBuchungsnr());
				System.out.println("Name: " + buchung.getKunde().getName());
				System.out.println("Vorname: "
						+ buchung.getKunde().getVorname());
				System.out.println("Adresse: "
						+ buchung.getKunde().getAdresse());
				System.out.println("Telefonnummer: "
						+ buchung.getKunde().getTelefonnr());
				System.out.println("Reiseziel: "
						+ buchung.getReiseZiel().toString());
				System.out.println("Woche: " + buchung.getWoche());
				System.out.println("Pl�tze: "
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
		// Nacheinander werden die Attribute, die f�r einen Kunden wichtig
		// sind, nacheinander von der Konsole eingelesen.
		String name = KonsoleIO
				.readStringFromConsole("Geben Sie einen Namen f�r den Kunden ein!");

		String vorname = KonsoleIO
				.readStringFromConsole("Geben Sie einen Vornamen f�r den Kunden ein!");

		String telefonnr = KonsoleIO
				.readStringFromConsole("Geben Sie die Telefonnummer des Kunden ein!");

		String adresse = KonsoleIO
				.readStringFromConsole("Geben Sie die Adresse des Kunden ein!");

		reisender = new Kunde(aktuelleKundenNr, name, vorname, adresse,
				telefonnr);
		aktuelleKundenNr++;

		// Zum Schluss wird der Kunde im Kundenstamm abgelegt.
		try {
			DateiIO.saveKundeToKundenstamm(reisender);
			KonsoleIO.printErfolgsmeldung("Kunde erfolgreich unter der Nummer "
					+ aktuelleKundenNr + " angelegt!");
		} catch (Exception e) {
			KonsoleIO.printFehlermeldung(OUTPUT_FEHLERMELDUNG);
		}

	}

	/**
	 * Diese Methode erm�glicht es dem Benutzer, zu einem Bus die Anzahl der
	 * freien Pl�tze angezeigt zu bekommen.
	 */
	public void zeigeFreiePlaetzeEinesBusses() {
		Reiseziel ziel = KonsoleIO
				.readGewuenschtesReiseziel("F�r welche Reise m�chten Sie den Bus anzeigen lassen?");
		int woche = KonsoleIO
				.readIntegerFromConsole("In welcher Woche f�hrt der gesuchte Bus? [1], [2] oder [3]");

		Reise gesuchteReise = getReiseZuZiel(ziel);
		Bus gesuchterBus = gesuchteReise.getBusZurReisewoche(woche);

		KonsoleIO.printErfolgsmeldung("In dem Bus nach " + ziel.toString()
				+ ", der in der " + woche + ". Woche f�hrt, sind "
				+ gesuchterBus.getAnzahlFreiePlaetze() + " Pl�tze frei.");
	}

	/**
	 * Diese Methode erm�glicht es dem Benutzer, die Teilnehmer einer Reise in
	 * alphabetischer Reihenfolge ausgeben zu lassen.
	 */
	public void zeigeTeilnehmerEinerReise() {
		Reiseziel ziel = KonsoleIO
				.readGewuenschtesReiseziel("F�r welche Reise m�chten Sie sich die Teilnehmer anzeigen lassen?");
		int woche = KonsoleIO
				.readIntegerFromConsole("In welcher Woche findet die gesuchte Reise statt? [1], [2] oder [3]");

		try {
			Kunde[] teilnehmer = DateiIO.getTeilnehmerZuReise(ziel.toString(),
					woche);
			for (int i = 0; i < teilnehmer.length; i++) {
				String s = teilnehmer[i].getName() + " \t "
						+ teilnehmer[i].getVorname();
				KonsoleIO.printErfolgsmeldung(s);
			}
		} catch (Exception e) {
			KonsoleIO.printFehlermeldung(INPUT_FEHLERMELDUNG);
		}
	}

	/**
	 * Diese Methode speichert aktuelle Kunden-, Buchungs- und Stornonummer in
	 * einer CSV-Datei.
	 */
	public void speichereDatenDerSitzung() {
		try {
			DateiIO.saveNummernVonLetzterSitzung(aktuelleKundenNr,
					aktuelleBuchungsNr, aktuelleStornoNr);
		} catch (IOException e) {
			KonsoleIO.printFehlermeldung(OUTPUT_FEHLERMELDUNG);
		}
	}
}
