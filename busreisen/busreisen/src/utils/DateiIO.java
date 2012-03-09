package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.Buchung;
import model.Kunde;
import model.Reiseziel;

/**
 * Diese Klasse lädt und speichert die Daten der Bussoftware. Der Kundenstamm
 * wird in einer .csv-Datei abgelegt. Die Buchungen und Stornierungen werden in
 * einer Textdatei mitgeschrieben.
 * 
 * @author Philipp
 * @version 09.03.2012
 * 
 */
public class DateiIO {

	/**
	 * Tabellenüberschriften in der CSV-Datei
	 */
	private static String[] kunden_headers = { "Name", "Vorname", "Adresse",
			"Telefonnummer" };

	/**
	 * Tabellenüberschriften in der Log-Datei
	 */
	private static String[] log_headers = { "Art der Buchung", "Nummer",
			"Name", "Vorname", "Adresse", "Telefonnummer", "Ziel", "Woche",
			"Anzahl der Plaetze", "Geaendert" };

	/**
	 * Name der Logdatei
	 */
	public static final String LOGFILE = "Bussoftware_Log.csv";

	/**
	 * Name der .csv-Datei, in der die Kunden gespeichert sind
	 */
	public static final String KUNDEN_FILE = "Bussoftware_Kunden.csv";

	/**
	 * Diese Methode schreibt die Tabellenüberschriften in die CSV-Datei, in der
	 * die Kunden gespeichert werden.
	 * 
	 * @throws IOException
	 */
	public static void writeHeadersInKundenFile() throws IOException {
		File csv_file = new File(KUNDEN_FILE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(csv_file));
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
	public static void writeHeadersInLog() throws IOException {
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
		File csv_file = new File(KUNDEN_FILE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(csv_file));
		bw.append(kunde.getName() + ";");
		bw.append(kunde.getVorname() + ";");
		bw.append(kunde.getAdresse() + ";");
		bw.append(kunde.getTelefonnr() + ";");
		bw.append("\n");
		bw.close();
	}

	/**
	 * Diese Methode sucht einen Kunden aus der CSV-Datei mithilfe seines Vor-
	 * und Nachnamens. Da Mehrfacheinträge möglich sind, wird ein Array von
	 * Kunden zurückgegeben.
	 * 
	 * @param name
	 *            Nachname des gesuchten Kunden
	 * @param vorname
	 *            Vorname des gesuchten Kunden
	 * @return Array von Kunden
	 * @throws IOException
	 */
	public static Kunde[] searchKundeInKundenstamm(String name, String vorname)
			throws Exception {
		FileReader fr = new FileReader(KUNDEN_FILE);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		// Der erste Durchlauf dient dazu, herauszufinden, wie viele Kunden es
		// zu dem übergebenem Namen gibt.
		int menge = 0;
		while (line != null) {
			line = br.readLine();
			String[] items = line.split(";");
			if ((items[0].equals(name)) && (items[1].equals(vorname))) {
				menge++;
			}
		}

		// Der zweite Durchlauf dient dazu, das zurückgegebene Array zu füllen.
		Kunde[] results = new Kunde[menge];

		br = new BufferedReader(fr);
		line = "";
		int counter = 0;
		while (line != null) {
			line = br.readLine();
			String[] items = line.split(";");
			if ((items[0].equals(name)) && (items[1].equals(vorname))) {
				results[counter] = new Kunde(items[0], items[1], items[2],
						items[3]);
				counter++;
			}
		}
		br.close();
		return results;
	}

	/**
	 * Diese Methode speichert eine übergebene Buchung in einer Log-Datei.
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
			bw.append("Stornierung: ");
			bw.append(buchung.getStornonr() + ";");
		} else {
			bw.append("Buchung: ");
			bw.append(buchung.getBuchungsnr() + ";");
		}

		bw.append(buchung.getKunde().getName() + ";");
		bw.append(buchung.getKunde().getVorname() + ";");
		bw.append(buchung.getKunde().getAdresse() + ";");
		bw.append(buchung.getKunde().getTelefonnr() + ";");
		bw.append(buchung.getReiseZiel().toString() + ";");
		bw.append(buchung.getWoche() + ";");
		bw.append(String.valueOf(buchung.getPlaetze()));
		bw.append("\n");
		bw.close();
	}

	/**
	 * Diese Methode sucht eine Buchung zur entsprechenden Buchungsnummer.
	 * 
	 * @param buchungsnr
	 *            Nummer der gesuchten Buchung
	 * @return Instanz der Klasse {@link Buchung}
	 * @throws IOException
	 */
	public static Buchung searchBuchungInLogFile(int buchungsnr)
			throws Exception {
		FileReader fr = new FileReader(LOGFILE);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		while (line != null) {
			line = br.readLine();
			String[] items = line.split(";");
			int nummer = Integer.parseInt(items[1]);
			if (nummer == buchungsnr) {
				Buchung b = new Buchung();
				b.setBuchungsnr(buchungsnr);
				b.setKunde(new Kunde(items[2], items[3], items[4], items[5]));
				b.setReiseZiel(Reiseziel.valueOf(items[6]));
				int woche = Integer.parseInt(items[7]);
				b.setWoche(woche);
				int plaetze = Integer.parseInt(items[8]);
				b.setPlaetze(plaetze);
				return b;
			}
		}
		return null;
	}

	/**
	 * Diese Methode gibt die Teilnehmer zu einer spezifizierten Reise zurueck.
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
		int menge = 0;
		FileReader fr = new FileReader(LOGFILE);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		while (line != null) {
			line = br.readLine();
			String[] items = line.split(";");
			if ((ziel.equals(items[6]))
					&& (woche == Integer.parseInt(items[7]))) {
				if (!(items[9].equals("ja"))) {
					menge++;
				}
			}
		}

		Kunde[] teilnehmer = new Kunde[menge];
		br = new BufferedReader(fr);
		line = "";
		int counter = 0;
		while (line != null) {
			line = br.readLine();
			String[] items = line.split(";");
			if ((ziel.equals(items[6]))
					&& (woche == Integer.parseInt(items[7]))) {
				if (!(items[9].equals("ja"))) {
					teilnehmer[counter] = new Kunde(items[2], items[3],
							items[4], items[5]);
					counter++;
				}
			}
		}
		br.close();

		Sortierverfahren.bubbleSort(teilnehmer);
		return teilnehmer;
	}
}
