package tests;

import static org.junit.Assert.assertEquals;
import model.Buchung;
import model.Kunde;
import model.Reiseziel;

import org.junit.Before;
import org.junit.Test;

public class BuchungTest {

	private static final int PLAETZE = 10;
	private Buchung buchung1;
	private Buchung buchung2;
	private Kunde kunde;

	@Before
	public void setUp() {
		kunde = new Kunde("Mueller", "Heinz", "Adresse", "1234");
		buchung2 = new Buchung(101, Reiseziel.WIEN, 1, kunde);
		buchung1 = new Buchung();
	}

	@Test
	public void testBuchung() {
		assertEquals(0, buchung1.getBuchungsnr());
	}

	@Test
	public void testBuchungIntReisezielIntKunde() {
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
		assertEquals(409, buchung1.getStornonr());
	}

	@Test
	public void testStorniere() {
		buchung2.storniere(202, PLAETZE);
		assertEquals(202, buchung2.getStornonr());
	}

	@Test
	public void testGetReiseZiel() {
		assertEquals("Wien", buchung2.getReiseZiel().toString());
	}

	@Test
	public void testSetReiseZiel() {
		buchung1.setReiseZiel(Reiseziel.MADRID);
		assertEquals("Madrid", buchung1.getReiseZiel().toString());
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
		// buchung2 muss nach dem Stornieren 0 Plaetze haben.
		assertEquals(0, buchung2.getPlaetze());
	}

	@Test
	public void testSetPlaetze() {
		buchung1.setPlaetze(PLAETZE);
		assertEquals(PLAETZE, buchung1.getPlaetze());
	}

}
