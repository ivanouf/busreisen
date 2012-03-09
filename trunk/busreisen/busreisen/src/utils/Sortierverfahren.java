package utils;

import model.Kunde;

/**
 * Diese Klasse ist fuer die Sortierung der Daten zustaendig.
 * 
 * @author Thomas
 * @version 09.03.2012
 * 
 */
public class Sortierverfahren {
	/**
	 * Disese Methode Sortiert die Daten in aufsteigender Reihenfolge. Es wird
	 * das Verfahren "SelectionSort" genutzt.
	 * 
	 * @param daten
	 *            Array von Strings
	 */
	public static void selectionSort(int daten[]) {
		int stelle = 0;
		for (int i = 0; i < daten.length - 1; i++) {
			int min = daten[i];

			for (int j = i + 1; j < daten.length; j++) {
				if (min > daten[j]) {
					stelle = j;
					min = daten[j];
				}
			}
			if (stelle > i) {
				int hilfsVar = daten[stelle];
				daten[stelle] = daten[i];
				daten[i] = hilfsVar;
			}

		}

	}

	/**
	 * Disese Methode Sortiert die Daten in aufsteigender Reihenfolge. Es wird
	 * das Verfahren "BubbleSort" genutzt.
	 * 
	 * @param daten
	 *            Array von Strings
	 */
	public static void bubbleSort(int daten[]) {
		int j;
		for (j = 0; j < daten.length - 1; j++) {
			for (int i = 0; i < daten.length - 1; i++) {
				if (daten[i] > daten[i + 1]) {
					int hilfsVar = daten[i];
					daten[i] = daten[i + 1];
					daten[i + 1] = hilfsVar;
				}
			}
		}
	}

	/**
	 * Disese Methode Sortiert die Daten in aufsteigender Reihenfolge. Es wird
	 * das Verfahren "InsertSort" genutzt.
	 * 
	 * @param daten
	 *            Array von Strings
	 */
	public static void insertionSort(int daten[]) {
		/*
		 * void insertionSort(double datan[]) Deklaration von i,j, hilfsVar
		 * wiederhole f�r i mit Anfangswert 1, Endwert L�ngeArray *(=10) und
		 * Schtittweite 1 j=i hilfsVar=daten[i] solange (j>0) oder
		 * (daten[j-1]>hilfsVar) daten[j] = daten[j-1] j-- daten[j] = hilfsVar
		 */
		int hilfsVar;
		int i, j;
		for (i = 1; i < daten.length; i++) {
			j = i;
			hilfsVar = daten[i];

			while ((j > 0) && (daten[j - 1] > hilfsVar)) {

				daten[j] = daten[j - 1];
				j--;

			}

			daten[j] = hilfsVar;

		}
	}

	/**
	 * Diese Methode sortiert ein Kunden-Array aufsteigend in der
	 * lexikographischen Reihenfolge der Nachnamen der Kunden.
	 * 
	 * @param kunden
	 *            Array mit Objekten vom Typ {@link Kunde}
	 */
	public static void bubbleSort(Kunde[] kunden) {
		for (int j = 0; j < kunden.length - 1; j++) {
			for (int i = 0; i < kunden.length - 1; i++) {
				if (kunden[i].getName().compareToIgnoreCase(
						kunden[i + 1].getName()) > 0) {
					Kunde hilfsVar = kunden[i];
					kunden[i] = kunden[i + 1];
					kunden[i + 1] = hilfsVar;
				}
			}
		}
	}
}
