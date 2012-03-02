package utils;

import java.io.BufferedWriter;
import java.io.File;
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
 * @version 02.03.2012
 * 
 */
public class DateiIO {

	/**
	 * Tabellenüberschriften in der CSV-Datei
	 */
	private static String[] csv_headers = { "Name", "Vorname", "Adresse",
			"Telefonnummer" };

	/**
	 * Tabellenüberschriften in der Log-Datei
	 */
	private static String[] log_headers = { "Buchungsnummer", "Name",
			"Vorname", "Adresse", "Telefonnummer", "Ziel", "Anzahl der Plätze" };

	/**
	 * Name der Logdatei
	 */
	private static final String LOGFILE = "Bussoftware_Log.txt";

	/**
	 * Name der .csv-Datei, in der die Kunden gespeichert sind
	 */
	private static final String CSVFILE = "Bussoftware_Kunden.csv";

	/**
	 * Diese Methode schreibt die Tabellenüberschriften in die CSV-Datei, in der
	 * die Kunden gespeichert werden.
	 * 
	 * @throws IOException
	 */
	public static void writeHeadersInCSV() throws IOException {
		File csv_file = new File(CSVFILE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(csv_file));
		for (int i = 0; i < csv_headers.length; i++) {
			bw.write(csv_headers[i] + ";");
		}
		bw.write("\n");
		bw.close();
	}

	/**
	 * Diese Methode schreibt die tabellenüberschriften in die Logdatei.
	 * 
	 * @throws IOException
	 */
	public static void writeHeadersInLog() throws IOException {
		File logfile = new File(LOGFILE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(logfile));
		for (int i = 0; i < csv_headers.length; i++) {
			bw.write(log_headers[i] + ";");
		}
		bw.write("\n");
		bw.close();
	}

	/**
	 * Diese Methode speichert den Kundenstamm in einer CSV-Datei.
	 * 
	 * @param kunden
	 *            Array von allen Kunden
	 * @throws IOException
	 */
	public static void saveKundenToCSV(Kunde[] kunden) throws IOException {
		File csv_file = new File(CSVFILE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(csv_file));
		for (int i = 0; i < kunden.length; i++) {
			Kunde kunde = kunden[i];
			bw.write(kunde.getName() + ";");
			bw.write(kunde.getVorname() + ";");
			bw.write(kunde.getAdresse() + ";");
			bw.write(kunde.getTelefonnr() + ";");
			bw.write("\n");
		}
		bw.close();
	}

	/**
	 * Diese Methode speichert eine übergebene Buchung in einer Log-Datei.
	 * 
	 * @param buchung
	 *            Instanz von {@link Buchung}
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
