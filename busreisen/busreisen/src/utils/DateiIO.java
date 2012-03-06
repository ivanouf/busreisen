package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.Buchung;
import model.Kunde;

/**
 * Diese Klasse lädt und speichert die Daten der Bussoftware. Der Kundenstamm
 * wird in einer .csv-Datei abgelegt. Die Buchungen und Stornierungen werden in
 * einer Textdatei mitgeschrieben.
 * 
 * @author Philipp
 * @version 06.03.2012
 * 
 */
public class DateiIO {

	/**
	 * Tabellen�berschriften in der CSV-Datei
	 */
	private static String[] kunden_headers = { "Name", "Vorname", "Adresse",
			"Telefonnummer" };

	/**
	 * Tabellen�berschriften in der Log-Datei
	 */
	private static String[] log_headers = { "Art der Buchung", "Nummer",
			"Name", "Vorname", "Adresse", "Telefonnummer", "Ziel",
			"Anzahl der Pl�tze" };

	/**
	 * Name der Logdatei
	 */
	private static final String LOGFILE = "Bussoftware_Log.csv";

	/**
	 * Name der .csv-Datei, in der die Kunden gespeichert sind
	 */
	private static final String KUNDEN_FILE = "Bussoftware_Kunden.csv";

	/**
	 * Diese Methode schreibt die Tabellen�berschriften in die CSV-Datei, in der
	 * die Kunden gespeichert werden.
	 * 
	 * @throws IOException
	 */
	public static void writeHeadersInCSV() throws IOException {
		File csv_file = new File(KUNDEN_FILE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(csv_file));
		for (int i = 0; i < kunden_headers.length; i++) {
			bw.write(kunden_headers[i] + ";");
		}
		bw.write("\n");
		bw.close();
	}

	/**
	 * Diese Methode schreibt die tabellen�berschriften in die Logdatei.
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
	public static void saveKundeToCSV(Kunde kunde) throws IOException {
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
	public static Kunde[] searchKundeFromCSV(String name, String vorname)
			throws IOException {
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

		return results;
	}

	/**
	 * Diese Methode speichert eine �bergebene Buchung in einer Log-Datei.
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
		bw.append(String.valueOf(buchung.getPlaetze()));
		bw.append("\n");
		bw.close();
	}

}
