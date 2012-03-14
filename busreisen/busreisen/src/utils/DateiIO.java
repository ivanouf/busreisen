package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.Buchung;
import model.Kunde;
import model.Reiseziel;
import model.TeilnehmerListe;

/**
 * Diese Klasse lädt und speichert die Daten der Bussoftware. Der Kundenstamm
 * wird in einer .csv-Datei abgelegt. Die Buchungen und Stornierungen werden in
 * einer Log-Datei mitgeschrieben, die ebenfalls im CSV-Format vorliegt.
 * Außerdem werden die Daten der letzten Sitzung (Kundennummer, Buchungsnummer,
 * Stornonummer) gespeichert, damit sie bei der nächsten Sitzung zur Verfügung
 * stehen.
 * 
 * @author Philipp
 * @version 14.03.2012
 * 
 */
public class DateiIO {

	/**
	 * Tabellenüberschriften der Kundenstamm-Datei
	 */
	private static String[] kunden_headers = { "Nummer", "Name", "Vorname",
			"Adresse", "Telefonnummer" };

	/**
	 * Tabellenüberschriften der Log-Datei
	 */
	private static String[] log_headers = { "Art der Buchung", "Nummer",
			"Kundennummer", "Name", "Vorname", "Adresse", "Telefonnummer",
			"Ziel", "Woche", "Anzahl der Plaetze" };

	/**
	 * Name der Log-Datei
	 * 
	 * @value "Bussoftware_Log.csv"
	 */
	public static final String LOGFILE = "Bussoftware_Log.csv";

	/**
	 * Name der der Kundenstamm-Datei
	 * 
	 * @value "Bussoftware_Kunden.csv"
	 */
	public static final String KUNDEN_FILE = "Bussoftware_Kunden.csv";

	/**
	 * Name der Datei, in der Buchungs-, Storno- und Kundennummer der letzten
	 * Sitzung gespeichert werden.
	 * 
	 * @value "Bussoftware_LetzteSitzung.csv
	 */
	public static final String LAST_SESSION_FILE = "Bussoftware_LetzteSitzung.csv";

	/**
	 * Diese Methode schreibt die Tabellenüberschriften in die CSV-Datei, in der
	 * die Kunden gespeichert werden.
	 * 
	 * @throws IOException
	 */
	public static void writeHeadersInKundenFile() throws IOException {
		// Datei oeffnen und zum Schreiben vorbereiten
		File kunden_file = new File(KUNDEN_FILE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(kunden_file));

		for (int i = 0; i < kunden_headers.length; i++) {
			bw.write(kunden_headers[i] + ";");
		}
		bw.write("\n");
		bw.close();
	}

	/**
	 * Diese Methode schreibt die Tabellenüberschriften in die Logdatei.
	 * 
	 * @throws IOException
	 */
	public static void writeHeadersInLogFile() throws IOException {
		// Datei oeffnen und zum Schreiben vorbereiten
		File logfile = new File(LOGFILE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(logfile));

		for (int i = 0; i < log_headers.length; i++) {
			bw.write(log_headers[i] + ";");
		}
		bw.write("\n");
		bw.close();
	}

	/**
	 * Diese Methode liest die Nummern von der letzten Sitzung ein.
	 * 
	 * @return Array von Integer-Werten mit den Nummern
	 * @throws IOException
	 */
	public static int[] readNummernVonLetzterSitzung() throws IOException {
		// Speicher für das Ergebnis-Array reservieren
		int[] nummern = new int[3];

		// Datei öffnen und zum Lesen vorbereiten
		File file = new File(LAST_SESSION_FILE);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;

		int counter = 0;
		do {
			line = br.readLine();
			nummern[counter] = Integer.parseInt(line);
			counter++;
		} while (counter < 3);
		br.close();

		return nummern;
	}

	/**
	 * Diese Methode speichert die aktuellen Nummern der letzten Sitzung ab.
	 * 
	 * @param kundenNr
	 *            aktuelle Kundennummer der letzten Sitzung
	 * @param buchungsNr
	 *            aktuelle Kundennummer der letzten Sitzung
	 * @param stornoNr
	 *            aktuelle Kundennummer der letzten Sitzung
	 * @throws IOException
	 */
	public static void saveNummernVonLetzterSitzung(int kundenNr,
			int buchungsNr, int stornoNr) throws IOException {
		// Datei öffnen und zum Schreiben vorbereiten
		File file = new File(LAST_SESSION_FILE);
		PrintWriter pw = new PrintWriter(file);

		pw.println(kundenNr);
		pw.println(buchungsNr);
		pw.println(stornoNr);

		pw.close();
	}

	/**
	 * Diese Methode speichert einen übergebenen Kunden in der
	 * Kundenstamm-Datei.
	 * 
	 * @param kunde
	 *            Instanz der Klasse {@link Kunde}
	 * @throws Exception
	 */
	public static void saveKundeToKundenstamm(Kunde kunde) throws Exception {
		File inFile = new File(KUNDEN_FILE);

		// Eine *.tmp Datei erstellen, die später wieder umbenannt wird
		File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		// Datei öffnen und zum Schreiben vorbereiten
		BufferedReader br = new BufferedReader(new FileReader(KUNDEN_FILE));
		PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		String line = null;

		// Die Inhalte von der Originaldatei auslesen und in der *.tmp Datei
		// wieder einfügen. Die zu aendernde Zeile wird neu geschrieben.
		do {
			line = br.readLine();
			if (line != null) {
				pw.println(line);
			}
		} while (line != null);
		br.close();

		// Die Kundendaten werden angehängt.
		pw.append(kunde.getNummer() + ";");
		pw.append(kunde.getName() + ";");
		pw.append(kunde.getVorname() + ";");
		pw.append(kunde.getAdresse() + ";");
		pw.append(kunde.getTelefonnr() + ";");
		pw.print("\n");
		pw.close();

		// Die Originaldatei löschen
		inFile.delete();

		// Die *.tmp Datei in den Originalnamen umbenennen
		if (!tempFile.renameTo(inFile))
			throw new Exception("Datei konnte nicht umbenannt werden");
	}

	/**
	 * Diese Methode sucht einen Kunden in der Kundenstamm-Datei mithilfe seines
	 * Vor- und Nachnamens. Da Mehrfacheinträge möglich sind, wird ein Array von
	 * Kunden zurueckgegeben.
	 * 
	 * @param name
	 *            Nachname des gesuchten Kunden
	 * @param vorname
	 *            Vorname des gesuchten Kunden
	 * @return Array von Kunden
	 * @throws Exception
	 */
	public static Kunde[] searchKundeInKundenstamm(String name, String vorname)
			throws Exception {
		// Datei öffnen und zum Lesen vorbereiten
		FileReader fr = new FileReader(KUNDEN_FILE);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		// Der erste Durchlauf dient dazu, herauszufinden, wie viele Kunden es
		// zu dem übergebenen Namen gibt.
		int menge = 0;
		do {
			line = br.readLine();
			if (line != null) {
				String[] items = line.split(";");
				if ((items[1].equals(name)) && (items[2].equals(vorname))) {
					menge++;
				}
			}
		} while (line != null);
		br.close();
		fr.close();

		// Der zweite Durchlauf dient dazu, das zurückgegebene Array zu
		// füllen.
		Kunde[] results = new Kunde[menge];

		fr = new FileReader(KUNDEN_FILE);
		br = new BufferedReader(fr);
		line = br.readLine();
		int counter = 0;
		while (counter < menge) {
			line = br.readLine();
			if (line != null) {
				String[] items = line.split(";");
				if ((items[1].equals(name)) && (items[2].equals(vorname))) {
					// Kunde wird mit den gefundenen Daten erzeugt.
					int nummer = Integer.parseInt(items[0]);
					results[counter] = new Kunde(nummer, items[1], items[2],
							items[3], items[4]);
					counter++;
				}
			}
		}
		br.close();
		return results;
	}

	/**
	 * Diese Methode schreibt einen geaenderten Kundendatensatz in den
	 * Kundenstamm. Als Identifikationskriterium gilt dabei die Kundennummer.
	 * 
	 * @param kunde
	 *            Instanz von [@Kunde}, die die neuen Werte für den Kunden
	 *            besitzt.
	 * @throws Exception
	 */
	public static void changeKundeInKundenstamm(Kunde kunde) throws Exception {
		File inFile = new File(KUNDEN_FILE);

		// Eine *.tmp Datei erstellen, die später wieder umbenannt wird
		File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		BufferedReader br = new BufferedReader(new FileReader(KUNDEN_FILE));
		PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		String line = br.readLine();
		pw.println(line);

		// Die Inhalte von der Originaldatei auslesen und in der *.tmp Datei
		// wieder einfügen. Die zu aendernde Zeile wird neu geschrieben.
		do {
			line = br.readLine();
			if (line != null) {
				String[] items = line.split(";");

				// Wenn der zu ändernde Kunde gefunden wurde, eine Zeile mit den
				// neuen Daten schreiben.
				int nummer = Integer.parseInt(items[0]);
				if (nummer == kunde.getNummer()) {
					pw.append(kunde.getNummer() + ";");
					pw.append(kunde.getName() + ";");
					pw.append(kunde.getVorname() + ";");
					pw.append(kunde.getAdresse() + ";");
					pw.append(kunde.getTelefonnr() + ";");
					pw.append("\n");
				} else {
					// Ansonsten schreibe den Inhalt der alten Zeile
					pw.println(line);
				}
			}
		} while (line != null);

		pw.close();
		br.close();

		// Die Originaldatei löschen
		inFile.delete();

		// Die *.tmp Datei in den Originalnamen umbenennen
		if (!tempFile.renameTo(inFile))
			throw new Exception("Datei konnte nicht umbenannt werden");
	}

	/**
	 * Diese Methode speichert eine uebergebene Buchung in einer Log-Datei.
	 * 
	 * @param buchung
	 *            Instanz der Klasse {@link Buchung}
	 * @throws Exception
	 */
	public static void saveBuchungToLogFile(Buchung buchung) throws Exception {
		File inFile = new File(LOGFILE);

		// Eine *.tmp Datei erstellen, die später wieder umbenannt wird
		File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		// Datei öffnen und zum Schreiben vorbereiten
		BufferedReader br = new BufferedReader(new FileReader(LOGFILE));
		PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		String line = null;

		// Die Inhalte von der Originaldatei auslesen und in der *.tmp Datei
		// wieder einfügen. Die zu aendernde Zeile wird neu geschrieben.
		do {
			line = br.readLine();
			if (line != null) {
				pw.println(line);
			}
		} while (line != null);
		br.close();

		// Wenn die Buchung storniert ist, wird "Stornierung" + Stornonummer
		// ausgegeben. Ansonsten wird "Buchung" + Buchungsnummer ausgegeben.
		if (buchung.getStornonr() > 0) {
			pw.append("Stornierung" + ";");
			pw.append(buchung.getStornonr() + ";");
		} else {
			pw.append("Buchung" + ";");
			pw.append(buchung.getBuchungsnr() + ";");
		}

		// Die Attribute der Buchung werden einzeln in den FileWriter
		// geschrieben.
		pw.append(buchung.getKunde().getNummer() + ";");
		pw.append(buchung.getKunde().getName() + ";");
		pw.append(buchung.getKunde().getVorname() + ";");
		pw.append(buchung.getKunde().getAdresse() + ";");
		pw.append(buchung.getKunde().getTelefonnr() + ";");
		pw.append(buchung.getReiseZiel().toString() + ";");
		pw.append(buchung.getWoche() + ";");
		pw.append(String.valueOf(buchung.getPlaetze()));
		pw.print("\n");
		pw.close();

		// Die Originaldatei löschen
		inFile.delete();

		// Die *.tmp Datei in den Originalnamen umbenennen
		if (!tempFile.renameTo(inFile))
			throw new Exception("Datei konnte nicht umbenannt werden");
	}

	/**
	 * Diese Methode erstellt eine Instanz der Klasse {@link Buchung} auf Basis
	 * der Daten aus einer Zeile im Logfile.
	 * 
	 * @param nummer
	 *            Nummer der Buchung
	 * @param details
	 *            Elemente der Zeile (Semikolon als Trennungszeichen)
	 * @return Instanz der Klasse {@link Buchung}
	 */
	private static Buchung erstelleBuchungAusLogEintrag(int nummer,
			String[] details) {
		Buchung b = new Buchung();
		b.setBuchungsnr(nummer);

		Kunde kunde = new Kunde();
		kunde.setNummer(Integer.parseInt(details[2]));
		kunde.setName(details[3]);
		kunde.setVorname(details[4]);
		kunde.setAdresse(details[5]);
		kunde.setTelefonnr(details[6]);
		b.setKunde(kunde);

		String ziel = details[7].toUpperCase();
		b.setReiseZiel(Reiseziel.valueOf(ziel));

		int woche = Integer.parseInt(details[8]);
		b.setWoche(woche);

		int plaetze = Integer.parseInt(details[9]);
		b.setPlaetze(plaetze);

		return b;
	}

	/**
	 * Diese Methode sucht eine Buchung zur entsprechenden Buchungsnummer. Dabei
	 * wird die Logdatei linear nach einem Eintrag durchsucht, der zur gesuchten
	 * Buchungsnummer passt.
	 * 
	 * @param buchungsnr
	 *            Nummer der gesuchten Buchung
	 * @return Instanz der Klasse {@link Buchung}
	 * @throws Exception
	 */
	public static Buchung searchBuchungInLogFile(int buchungsnr)
			throws Exception {
		// Datei öffnen und zum Lesen vorbereiten
		FileReader fr = new FileReader(LOGFILE);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		// Mithilfer einer linearen Suche werden alle Einträge mit der
		// gesuchten Buchungsnummer abgeglichen.
		do {
			line = br.readLine();
			if (line != null) {
				String[] items = line.split(";");
				int nummer = Integer.parseInt(items[1]);
				// Wenn die Nummer des Eintrags mit der gesuchten Nummer
				// übereinstimmmt, wird
				// auf Basis der gefundenen Daten ein Buchungsobjekt erstellt.
				if (nummer == buchungsnr) {
					br.close();
					return erstelleBuchungAusLogEintrag(buchungsnr, items);
				}
			}
		} while (line != null);

		br.close();
		return null;
	}

	/**
	 * Diese Methode sucht zu einer Buchung eine passende Stornierung.
	 * 
	 * @param buchung
	 *            Instanz der Klasse {@link Buchung}
	 * @return Buchung mit Stornonummer oder null, wenn keine gefunden wurde
	 * @throws Exception
	 */
	public static Buchung searchStornierungZurBuchung(Buchung buchung)
			throws Exception {
		// Datei öffnen und zum Lesen vorbereiten
		FileReader fr = new FileReader(LOGFILE);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		// Mithilfer einer linearen Suche wird nach Stornierungen gesucht. Diese
		// werden dann inhaltlich (siehe Javadoc für equals()) mit der Buchung
		// abgeglichen.
		do {
			line = br.readLine();
			if (line != null) {
				String[] items = line.split(";");
				if (items[0].contains("Stornierung")) {
					Buchung stornierung = erstelleBuchungAusLogEintrag(0, items);
					stornierung.setStornonr(Integer.parseInt(items[1]));
					if (stornierung.equals(buchung)) {
						br.close();
						return stornierung;
					}
				}
			}
		} while (line != null);

		br.close();
		return null;
	}

	/**
	 * Diese Methode gibt die Teilnehmer zu einer spezifizierten Reise zurück.
	 * Dabei wird die Logdatei linear durchsucht. Dabei wird der Kunde, der zur
	 * Reise gehört, zu einer {@link TeilnehmerListe} hinzugeügt. Sollte eine
	 * Buchung später wieder storniert werden, wird die Teilnehmerliste
	 * aktualisiert. Zum Schluss wird die Liste von Teilnehmern in ein Array vom
	 * Typ {@link Kunde} umgewandelt und dann alphabetisch aufsteigend sortiert.
	 * 
	 * @param ziel
	 *            Reiseziel als String
	 * @param woche
	 *            Woche, in der die Reise stattfindet als Integer
	 * @return Array von Kunden, in dem sie ihren Namen nach lexikographisch
	 *         sortiert sind
	 * @throws Exception
	 */
	public static Kunde[] getTeilnehmerZuReise(String ziel, int woche)
			throws Exception {
		// Teilnehmerliste erstellen
		TeilnehmerListe teilnehmerListe = new TeilnehmerListe();
		// Datei oeffnen und zum Lesen vorbereiten
		FileReader fr = new FileReader(LOGFILE);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();

		do {
			line = br.readLine();
			if (line != null) {
				String[] items = line.split(";");
				// Wenn die aktuelle Buchung / Stornierung zur gesuchten Reise
				// gehört
				if ((ziel.equals(items[7]))
						&& (woche == Integer.parseInt(items[8]))) {
					// Erstelle eine Kundeninstanz aus den Daten in der Buchung
					Kunde aktKunde = new Kunde();
					aktKunde.setNummer(Integer.parseInt(items[2]));
					aktKunde.setName(items[3]);
					aktKunde.setVorname(items[4]);
					aktKunde.setAdresse(items[5]);
					aktKunde.setTelefonnr(items[6]);

					// Wenn eine Buchung vorliegt
					if (items[0].contains("Buchung")) {
						// Wenn der aktuelle Kunde nocht nicht als Teilnehmer
						// vermerkt worden ist, füge ihn der Liste hinzu.
						if (!(teilnehmerListe.containsTeilnehmer(aktKunde))) {
							int aktPlaetze = Integer.parseInt(items[9]);
							teilnehmerListe.appendTeilnehmer(aktKunde,
									aktPlaetze);
						}
					}

					// Wenn eine Stornierung vorliegt
					else {
						int storniertePlaetze = Integer.parseInt(items[9]);
						int gebuchtePlaetze = teilnehmerListe
								.getPlaetzeZumKunden(aktKunde);
						int differenz = gebuchtePlaetze - storniertePlaetze;
						// Wenn alle Plätze, die aktuell gebucht waren,
						// storniert
						// wurden, entferne den Teilnehmer aus der Liste
						if (differenz == 0) {
							teilnehmerListe.removeTeilnehmer(aktKunde,
									gebuchtePlaetze);
						} else {
							// Ansonsten reduziere die gebuchten Plätze in der
							// Liste.
							teilnehmerListe.reduziereGebuchtePlaetze(aktKunde,
									differenz);
						}
					}
				}
			}
		} while (line != null);
		br.close();

		// Erzeuge ein Kundenarray aus der Teilnahmeliste und sortiere es.
		Kunde[] alleTeilnehmer = teilnehmerListe.toArray();
		Sortierverfahren.bubbleSort(alleTeilnehmer);
		return alleTeilnehmer;
	}

	/**
	 * Diese Methode sucht alle Buchungen zu einem gegebenen Reiseziel. Als
	 * Ergebnis werden die relevanten Informationen der Buchung (Nummer, Woche,
	 * Plaetze) als String mit Leerzeichen verkettet. Für Buchungen dient das
	 * Semilkolon als Trennzeichen.
	 * 
	 * @param reiseziel
	 *            Reiseziel als String
	 * @return String der Form "Nummer Woche Plätze;Nummer Woche Plätze;..." <br>
	 *         leerer String, wenn es keine Buchungen zum Reiseziel gibt
	 * @throws Exception
	 */
	public static String searchAlleBuchungenZurReise(String reiseziel)
			throws Exception {
		// Datei öffnen und zum Lesen vorbereiten
		FileReader fr = new FileReader(LOGFILE);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		String result = "";
		// Mithilfer einer linearen Suche werden alle Einträge zu dem
		// übergebenen Reiseziel gesucht.
		do {
			line = br.readLine();
			if (line != null) {
				String[] items = line.split(";");
				if (items[7].equals(reiseziel)) {
					// Es wird ein String der Form "Nummer Woche Plätze:"
					// erzeugt
					// und mit dem Ergebnisstring verkettet.
					String eintrag = items[1] + " " + items[8] + " " + items[9]
							+ ";";
					result += eintrag;
				}
			}
		} while (line != null);
		br.close();
		return result;
	}

	/**
	 * Diese Methode wird aufgerufen, um nach einer Änderung von Kundendaten im
	 * Kundenstamm alle Buchungen dieses Kunden auf den aktuellen Stand zu
	 * bringen.
	 * 
	 * @param kunde
	 *            Instanz der Klasse {@link Kunde}
	 * @throws Exception
	 */
	public static void updateBuchungenZuKunde(Kunde kunde) throws Exception {
		File inFile = new File(LOGFILE);

		// Eine *.tmp Datei erstellen, die später wieder umbenannt wird
		File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		BufferedReader br = new BufferedReader(new FileReader(LOGFILE));
		PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		String line = br.readLine();
		pw.println(line);

		// Die Inhalte von der Originaldatei auslesen und in der *.tmp Datei
		// wieder einfügen. Die zu ändernde Zeile wird neu geschrieben.
		do {
			line = br.readLine();
			if (line != null) {
				String[] items = line.split(";");

				int aktuelleKundennummer = Integer.parseInt(items[2]);
				// Pruefe, ob die vorliegende Buchung zum geänderten Kunden
				// gehört
				if (aktuelleKundennummer == kunde.getNummer()) {
					// Wenn ja, schreibe die Zeile neu
					pw.append(items[0] + ";"); // Buchungsart bleibt gleich
					pw.append(items[1] + ";"); // Nummer der Buchung/Stornierung
												// bleibt
												// gleich
					pw.append(items[2] + ";"); // Kundennummer bleibt gleich
					pw.append(kunde.getName() + ";");
					pw.append(kunde.getVorname() + ";");
					pw.append(kunde.getAdresse() + ";");
					pw.append(kunde.getTelefonnr() + ";");
					pw.append(items[7] + ";"); // Reiseziel bleibt gleich
					pw.append(items[8] + ";"); // Gebuchte Woche bleibt gleich
					pw.append(items[9] + ";"); // Gebuchte Plätze bleiben gleich
					pw.append("\n");
				} else {
					// Ansonsten übernehme die alte Zeile.
					pw.println(line);
				}
			}
		} while (line != null);
		pw.close();
		br.close();

		// Die Originaldatei löschen
		inFile.delete();

		// Die *.tmp Datei in den Originalnamen umbenennen
		if (!tempFile.renameTo(inFile))
			throw new Exception("Datei konnte nicht umbenannt werden");
	}
}
