package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Kunde;

/**
 * Diese Klasse lädt und speichert die Daten
 * 
 * @author Thomas
 * @version 01.03.2012
 * 
 */
public class DateiIO {

	private static String[] headers = { "Buchungsnummer", "Name", "Vorname",
			"Adresse", "Telefonnummer", "Ziel", "Anzahl der Plätze" };

	public static void saveKundenToCSV(String filename, Kunde[] kunden)
			throws IOException {
		File csv_file = new File(filename);
		BufferedWriter bw = new BufferedWriter(new FileWriter(csv_file));
		for (int i = 0; i < headers.length; i++) {
			bw.write(headers[i] + ";");
		}
		bw.write("\n");
		for (int i = 0; i < kunden.length; i++) {
			Kunde kunde = kunden[i];
			bw.write(kunde.getBuchungsnr() + ";");
			bw.write(kunde.getName() + ";");
			bw.write(kunde.getVorname() + ";");
			bw.write(kunde.getAdresse() + ";");
			bw.write(kunde.getTelefonnr() + ";");
			bw.write(kunde.getReiseZiel() + ";");
			bw.write(kunde.getBuchungsnr() + ";");
			bw.write("\n");
		}

	}
}
