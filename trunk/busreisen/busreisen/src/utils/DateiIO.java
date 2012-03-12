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

/**
 * Diese Klasse l�dt und speichert die Daten der Bussoftware. Der Kundenstamm
 * wird in einer .csv-Datei abgelegt. Die Buchungen und Stornierungen werden in
 * einer Log-Datei mitgeschrieben, die ebenfalls im CSV-Format vorliegt.
 * 
 * @author Philipp
 * @version 12.03.2012
 * 
 */
public class DateiIO {

	/**
	 * Tabellen�berschriften der Kundenstamm-Datei
	 */
	private static String[] kunden_headers = { "Nummer", "Name", "Vorname",
			"Adresse", "Telefonnummer" };

	/**
	 * Tabellen�berschriften der Log-Datei
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
	 * Diese Methode schreibt die Tabellen�berschriften in die CSV-Datei, in der
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
	 * Diese Methode schreibt die Tabellen�berschriften in die Logdatei.
	 * 
	 * @throws IOException
	 */
	public static void writeHeadersInLogFile() throws IOException {
		// Datei oeffnen und zum Schreiben vorbereiten
		File logfile = new File(LOGFILE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(logfile));

		for (int i = 0; i < kunden_headers.length; i++) {
			bw.write(log_headers[i] + ";");
		}
		bw.write("\n");
		bw.close();
	}

	/**
	 * Diese Methode speichert den Kundenstamm in einer CSV-Datei.
	 * 
	 * @param kunde
	 *            Instanz der Klasse {@link Kunde}
	 * @throws IOException
	 */
	public static void saveKundeToKundenstamm(Kunde kunde) throws IOException {
		// Datei �ffnen und zum Schreiben vorbereiten
		File csv_file = new File(KUNDEN_FILE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(csv_file));

		// Die Attribute des Kunden werden einzeln in den FileWriter
		// geschrieben.
		bw.append(kunde.getNummer() + ";");
		bw.append(kunde.getName() + ";");
		bw.append(kunde.getVorname() + ";");
		bw.append(kunde.getAdresse() + ";");
		bw.append(kunde.getTelefonnr() + ";");
		bw.newLine();
		bw.close();
	}

	/**
	 * Diese Methode sucht einen Kunden in der Kundenstamm-Datei mithilfe seines
	 * Vor- und Nachnamens. Da Mehrfacheintr�ge m�glich sind, wird ein Array von
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
		// Datei �ffnen und zum Lesen vorbereiten
		FileReader fr = new FileReader(KUNDEN_FILE);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		// Der erste Durchlauf dient dazu, herauszufinden, wie viele Kunden es
		// zu dem �bergebenen Namen gibt.
		int menge = 0;
		while (line != null) {
			line = br.readLine();
			String[] items = line.split(";");
			if ((items[1].equals(name)) && (items[2].equals(vorname))) {
				menge++;
			}
		}

		// Der zweite Durchlauf dient dazu, das zur�ckgegebene Array zu
		// f�llen.
		Kunde[] results = new Kunde[menge];

		br = new BufferedReader(fr);
		line = "";
		int counter = 0;
		while ((line != null) && (counter < menge)) {
			line = br.readLine();
			String[] items = line.split(";");
			if ((items[0].equals(name)) && (items[1].equals(vorname))) {
				// Kunde wird mit den gefundenen Daten erzeugt.
				int nummer = Integer.parseInt(items[0]);
				results[counter] = new Kunde(nummer, items[1], items[2],
						items[3], items[4]);
				counter++;
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
	 *            Instanz von [@Kunde}, die die neuen Werte f�r den Kunden
	 *            besitzt.
	 * @throws Exception
	 */
	public static void changeKundeInKundenstamm(Kunde kunde) throws Exception {
		File inFile = new File(KUNDEN_FILE);

		// Eine *.tmp Datei erstellen, die sp�ter wieder umbenannt wird
		File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		BufferedReader br = new BufferedReader(new FileReader(KUNDEN_FILE));
		PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		String line = null;

		// Die Inhalte von der Originaldatei auslesen und in der *.tmp Datei
		// wieder einf�gen. Die zu aendernde Zeile wird neu geschrieben.
		while ((line = br.readLine()) != null) {
			line = br.readLine();
			String[] items = line.split(";");

			// Wenn der zu �ndernde Kunde gefunden wurde, eine Zeile mit den
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
		pw.close();
		br.close();

		// Die Originaldatei l�schen
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
	 * @throws IOException
	 */
	public static void saveBuchungToLogFile(Buchung buchung) throws IOException {
		File logfile = new File(LOGFILE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(logfile));

		// Wenn die Buchung storniert ist, wird "Stornierung" + Stornonummer
		// ausgegeben. Ansonsten wird "Buchung" + Buchungsnummer ausgegeben.
		if (buchung.getStornonr() > 0) {
			bw.append("Stornierung" + ";");
			bw.append(buchung.getStornonr() + ";");
		} else {
			bw.append("Buchung" + ";");
			bw.append(buchung.getBuchungsnr() + ";");
		}

		// Die Attribute der Buchung werden einzeln in den FileWriter
		// geschrieben.
		bw.append(buchung.getKunde().getNummer() + ";");
		bw.append(buchung.getKunde().getName() + ";");
		bw.append(buchung.getKunde().getVorname() + ";");
		bw.append(buchung.getKunde().getAdresse() + ";");
		bw.append(buchung.getKunde().getTelefonnr() + ";");
		bw.append(buchung.getReiseZiel().toString() + ";");
		bw.append(buchung.getWoche() + ";");
		bw.append(String.valueOf(buchung.getPlaetze()));
		bw.newLine();
		bw.close();
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

		b.setReiseZiel(Reiseziel.valueOf(details[7]));

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
		// Datei �ffnen und zum Lesen vorbereiten
		FileReader fr = new FileReader(LOGFILE);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		// Mithilfer einer linearen Suche werden alle Eintr�ge mit der
		// gesuchten Buchungsnummer abgeglichen.
		while (line != null) {
			line = br.readLine();
			String[] items = line.split(";");
			int nummer = Integer.parseInt(items[1]);
			// Wenn die Nummer des Eintrags mit der gesuchten Nummer
			// �bereinstimmmt, wird
			// auf Basis der gefundenen Daten ein Buchungsobjekt erstellt.
			if (nummer == buchungsnr) {
				br.close();
				return erstelleBuchungAusLogEintrag(buchungsnr, items);
			}
		}
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
		// Datei �ffnen und zum Lesen vorbereiten
		FileReader fr = new FileReader(LOGFILE);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		// Mithilfer einer linearen Suche wird nach Stornierungen gesucht. Diese
		// werden dann inhaltlich (siehe Javadoc f�r equals()) mit der Buchung
		// abgeglichen.
		while (line != null) {
			line = br.readLine();
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
		br.close();
		return null;
	}

	/**
	 * Diese Methode gibt die Teilnehmer zu einer spezifizierten Reise zur�ck.
	 * Dabei wird die Logdatei zweimal linear durchsucht. Beim ersten Durchlauf
	 * werden die Eintr�ge, die zur gesuchten Reise passen, gez�hlt. Im zweiten
	 * Durchlauf werden diese Eintr�ge in einem Array abgelegt, welches dann
	 * sortiert zur�ckgegeben wird.
	 * 
	 * @param ziel
	 *            Reiseziel als String
	 * @param woche
	 *            Woche, in der die Reise stattfindet als Integer
	 * @return Array von Kunden, in dem sie ihren Namen nach lexikographisch
	 *         sortiert sind
	 * @throws Exception
	 */
	// TODO
	public static Kunde[] getTeilnehmerZuReise(String ziel, int woche)
			throws Exception {
		// Menge der Teilnehmer
		int menge = 0;
		// Datei oeffnen und zum Lesen vorbereiten
		FileReader fr = new FileReader(LOGFILE);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		// Im ersten linearen Durchlauf werden die passenden Eintraege gezaehlt.
		while (line != null) {
			line = br.readLine();
			String[] items = line.split(";");
			if ((ziel.equals(items[6]))
					&& (woche == Integer.parseInt(items[7]))) {
				// Es kann sein, dass die Buchung schon geaendert wurde.
				// Dann soll sie nicht doppelt gezaehlt werden.
				if (!(items[9].equals("ja"))) {
					menge++;
				}
			}
		}
		br.close();

		// Auf Basis der nun bekannten Menge kann ein Array erstellt werden.
		Kunde[] teilnehmer = new Kunde[menge];
		br = new BufferedReader(fr);
		line = "";
		int counter = 0;
		// Zweiter linearer Durchlauf
		while ((line != null) && (counter < menge)) {
			line = br.readLine();
			String[] items = line.split(";");
			if ((ziel.equals(items[6]))
					&& (woche == Integer.parseInt(items[7]))) {
				if (!(items[9].equals("ja"))) {
					// Teilnehmer wird mit den gefundenen Daten erzeugt.
					Kunde kunde = new Kunde();
					kunde.setName(items[2]);
					kunde.setVorname(items[3]);
					kunde.setAdresse(items[4]);
					kunde.setTelefonnr(items[5]);

					teilnehmer[counter] = kunde;
					counter++;
				}
			}
		}
		br.close();

		Sortierverfahren.bubbleSort(teilnehmer);
		return teilnehmer;
	}

	/**
	 * Diese Methode sucht alle Buchungen zu einem gegebenen Reiseziel. Als
	 * Ergebnis werden die relevanten Informationen der Buchung (Nummer, Woche,
	 * Plaetze) als String mit Leerzeichen verkettet. Fuer Buchungen dient das
	 * Semilkolon als Trennzeichen.
	 * 
	 * @param reiseziel
	 *            Reiseziel als String
	 * @return String der Form "Nummer Woche Pl�tze;Nummer Woche Pl�tze;..."
	 * @throws Exception
	 */
	public static String searchAlleBuchungenZurReise(String reiseziel)
			throws Exception {
		// Datei �ffnen und zum Lesen vorbereiten
		FileReader fr = new FileReader(LOGFILE);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		String result = "";
		// Mithilfer einer linearen Suche werden alle Eintr�ge zu dem
		// �bergebenen Reiseziel gesucht.
		while (line != null) {
			line = br.readLine();
			String[] items = line.split(";");
			if (items[6].equals(reiseziel)) {
				// Es wird ein String der Form "Nummer Woche Pl�tze:" erzeugt
				// und mit dem Ergebnisstring verkettet.
				String eintrag = items[1] + " " + items[7] + " " + items[8]
						+ ";";
				result += eintrag;
			}
		}
		br.close();
		return result;
	}

	/**
	 * Diese Methode wird aufgerufen, um nach einer �nderung von Kundendaten im
	 * Kundenstamm alle Buchungen dieses Kunden auf den aktuellen Stand zu
	 * bringen.
	 * 
	 * @param kunde
	 *            Instanz der Klasse {@link Kunde}
	 * @throws Exception
	 */
	public static void updateBuchungenZuKunde(Kunde kunde) throws Exception {
		File inFile = new File(LOGFILE);

		// Eine *.tmp Datei erstellen, die sp�ter wieder umbenannt wird
		File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		BufferedReader br = new BufferedReader(new FileReader(LOGFILE));
		PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		String line = null;

		// Die Inhalte von der Originaldatei auslesen und in der *.tmp Datei
		// wieder einf�gen. Die zu �ndernde Zeile wird neu geschrieben.
		while ((line = br.readLine()) != null) {
			line = br.readLine();
			String[] items = line.split(";");

			int aktuelleKundennummer = Integer.parseInt(items[2]);
			// Pruefe, ob die vorliegende Buchung zum ge�nderten Kunden geh�rt
			if (aktuelleKundennummer == kunde.getNummer()) {
				// Wenn ja, schreibe die Zeile neu
				pw.append(items[0]); // Buchungsart bleibt gleich
				pw.append(items[1]); // Nummer der Buchung/Stornierung bleibt
										// gleich
				pw.append(items[2]); // Kundennummer bleibt gleich
				pw.append(kunde.getName());
				pw.append(kunde.getVorname());
				pw.append(kunde.getAdresse());
				pw.append(kunde.getTelefonnr());
				pw.append(items[7]); // Reiseziel bleibt gleich
				pw.append(items[8]); // Gebuchte Woche bleibt gleich
				pw.append(items[9]); // Gebuchte Pl�tze bleiben gleich
				pw.append("\n");

			} else {
				// Ansonsten �bernehme die alte Zeile.
				pw.println(line);
			}
		}
		pw.close();
		br.close();

		// Die Originaldatei l�schen
		inFile.delete();

		// Die *.tmp Datei in den Originalnamen umbenennen
		if (!tempFile.renameTo(inFile))
			throw new Exception("Datei konnte nicht umbenannt werden");
	}
}
