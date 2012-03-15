package tests;

import model.Reiseverwaltung;

/**
 * Diese Klasse deint dazu, die verschiedenen Funktionen der Buchungssoftware
 * für das Busreise-Unternehmen zu testen.
 * 
 * @author Philipp
 * @version 14.03.2012
 */
public class Test {

	public static void main(String[] args) {
		Reiseverwaltung reiseverwaltung = new Reiseverwaltung();

		// System.out.println("\n [Test] Anlegen eines Kunden");
		// reiseverwaltung.kundeAnlegen();

		System.out.println("\n [Test] Durchführung einer Buchung");
		reiseverwaltung.buchen();

		System.out.println("\n [Test] Anzeige der freien Plätze eines Busses");
		reiseverwaltung.zeigeFreiePlaetzeEinesBusses();

		System.out.println("\n [Test] Ausgabe der Teilnehmerliste einer Reise");
		reiseverwaltung.zeigeTeilnehmerEinerReise();

		System.out.println("\n [Test] Durchführung einer Stornierung");
		reiseverwaltung.stornieren();
		reiseverwaltung.zeigeFreiePlaetzeEinesBusses();

		System.out.println("\n [Test] Ändern von Kundendaten");
		reiseverwaltung.kundenDatenKorrektur();

		System.out.println("\n [Test] Ändern von Buchungsdaten");
		reiseverwaltung.buchungsDatenKorrektur();

		reiseverwaltung.speichereDatenDerSitzung();
	}
}
