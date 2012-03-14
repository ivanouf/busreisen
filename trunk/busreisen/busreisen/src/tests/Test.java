package tests;

import model.Reiseverwaltung;

public class Test {

	public static void main(String[] args) {

		Reiseverwaltung reiseverwaltung = new Reiseverwaltung();
		reiseverwaltung.zeigeFreiePlaetzeEinesBusses();
		reiseverwaltung.zeigeTeilnehmerEinerReise();
		reiseverwaltung.speichereDatenDerSitzung();
	}
}
