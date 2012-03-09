package tests;

import static org.junit.Assert.assertEquals;
import model.Buchung;
import model.Kunde;
import model.Reiseziel;

import org.junit.Test;

public class BuchungTest {

	private static final int PLAETZE = 10;
	private Buchung buchung1;
	private Buchung buchung2;
	private Kunde kunde = new Kunde("Mueller", "Heinz", "Adresse", "1234");

	@Test
	public void testBuchung() {
		buchung1 = new Buchung();
		assertEquals(0, buchung1.getBuchungsnr());
	}

	@Test
	public void testBuchungIntReisezielIntKunde() {
		buchung2 = new Buchung(101, Reiseziel.WIEN, 1, kunde);
		assertEquals(101, buchung2.getBuchungsnr());
	}

	@Test
	public void testGetKunde() {
		assertEquals(kunde, buchung2.getKunde());
	}

	@Test
	public void testSetKunde() {
		buchung1.setKunde(kunde);
		assertEquals(kunde, buchung1.getKunde());
	}

	@Test
	public void testGetBuchungsnr() {
		assertEquals(101, buchung2.getBuchungsnr());
	}

	@Test
	public void testSetBuchungsnr() {
		buchung1.setBuchungsnr(302);
		assertEquals(302, buchung1.getBuchungsnr());
	}

	@Test
	public void testGetStornonr() {
		buchung1.storniere(409, PLAETZE);
		assertEquals(309, buchung1.getStornonr());
	}

	@Test
	public void testStorniere() {
		buchung2.storniere(202, PLAETZE);
		assertEquals(302, buchung2.getStornonr());
	}

	@Test
	public void testGetReiseZiel() {
		String expected = "Wien";
		String actual = Reiseziel.WIEN.toString(buchung2.getReiseZiel());
		assertEquals(expected, actual);
	}

	@Test
	public void testSetReiseZiel() {
		buchung1.setReiseZiel(Reiseziel.MADRID);
		String expected = "Madrid";
		String actual = Reiseziel.MADRID.toString(buchung1.getReiseZiel());
		assertEquals(expected, actual);
	}

	@Test
	public void testGetWoche() {
		assertEquals(1, buchung2.getWoche());
	}

	@Test
	public void testSetWoche() {
		buchung1.setWoche(3);
		assertEquals(3, buchung1.getWoche());
	}

	@Test
	public void testGetPlaetze() {
		assertEquals(PLAETZE, buchung2.getPlaetze());
	}

	@Test
	public void testSetPlaetze() {
		buchung1.setPlaetze(PLAETZE);
		assertEquals(PLAETZE, buchung1.getPlaetze());
	}

}
