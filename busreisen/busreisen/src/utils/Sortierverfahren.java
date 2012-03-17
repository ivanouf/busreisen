package utils;

import model.Kunde;

/**
 * Diese Klasse ist für die Sortierung der Daten zuständig.
 * 
 * @author Thomas
 * @version 09.03.2012
 * 
 */
public class Sortierverfahren {

	/**
	 * Diese Methode sortiert ein Kunden-Array aufsteigend in der
	 * lexikographischen Reihenfolge der Nachnamen der Kunden.
	 * 
	 * @param kunden
	 *            Array mit Objekten vom Typ {@link Kunde}
	 */
	public static void bubbleSort(Kunde[] kunden) {
		boolean tausch = false;
		do {
			tausch = false;
			for (int j = 0; j < kunden.length - 1; j++) {
				for (int i = 0; i < kunden.length - 1; i++) {
					if (kunden[i].getName().compareToIgnoreCase(
							kunden[i + 1].getName()) > 0) {
						Kunde hilfsVar = kunden[i];
						kunden[i] = kunden[i + 1];
						kunden[i + 1] = hilfsVar;
						tausch = true;
					}
				}
			}
		} while (tausch);
	}
}
