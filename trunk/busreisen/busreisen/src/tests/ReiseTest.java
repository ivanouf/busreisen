package tests;

import static org.junit.Assert.assertEquals;
import model.Buchung;
import model.Reise;
import model.Reiseziel;
import model.Wochentag;

import org.junit.Test;

public class ReiseTest {

	private Reise reise = new Reise(Reiseziel.WIEN, Wochentag.MONTAG);

	@Test
	public void testGetReiseziel() {
		assertEquals("Wien", reise.getReiseziel().toString());
	}

	@Test
	public void testGetStarttag() {
		assertEquals(Wochentag.MONTAG, reise.getStarttag());
	}

	@Test
	public void testBuchungOK() {
		Buchung buchung = new Buchung();
		buchung.setPlaetze(30);
		buchung.setWoche(2);
		assertEquals(reise.buchungOK(buchung), true);
	}

}
