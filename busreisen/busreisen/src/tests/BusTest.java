/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;
import model.Bus;
import model.Reiseziel;

import org.junit.Before;
import org.junit.Test;

/**
 * Dieser TestCase testet die Klasse {@link Bus}.
 * 
 * @author Philipp
 * @version 06.03.2012
 */
public class BusTest {

	private Bus bus1;
	private Bus bus2;
	private static final String ERROR = "Werte stimmen nicht überein.";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bus1 = new Bus();
		bus2 = new Bus(40, 1, Reiseziel.BERLIN);
	}

	/**
	 * Test method for {@link model.Bus#getAnzahlPlaetze()}.
	 */
	@Test
	public void testGetAnzahlPlaetze() {
		assertEquals(ERROR, 40, bus2.getAnzahlPlaetze());
	}

	/**
	 * Test method for {@link model.Bus#setAnzahlPlaetze(int)}.
	 */
	@Test
	public void testSetAnzahlPlaetze() {
		bus1.setAnzahlPlaetze(50);
		assertEquals(ERROR, 50, bus1.getAnzahlPlaetze());
	}

	/**
	 * Test method for {@link model.Bus#getAnzahlFreiePlaetze()}.
	 */
	@Test
	public void testGetAnzahlFreiePlaetze() {
		assertEquals(ERROR, 40, bus2.getAnzahlFreiePlaetze());
	}

	/**
	 * Test method for {@link model.Bus#setAnzahlFreiePlaetze(int)}.
	 */
	@Test
	public void testSetAnzahlFreiePlaetze() {
		bus1.setAnzahlFreiePlaetze(20);
		assertEquals(ERROR, 20, bus1.getAnzahlFreiePlaetze());
	}

	/**
	 * Test method for {@link model.Bus#getWoche()}.
	 */
	@Test
	public void testGetWoche() {
		assertEquals(ERROR, 1, bus2.getWoche());
	}

	/**
	 * Test method for {@link model.Bus#setWoche(int)}.
	 */
	@Test
	public void testSetWoche() {
		bus1.setWoche(2);
		assertEquals(ERROR, 2, bus1.getWoche());
	}

	/**
	 * Test method for {@link model.Bus#getReiseZiel()}.
	 */
	@Test
	public void testGetReiseZiel() {
		assertEquals(ERROR, Reiseziel.BERLIN, bus2.getReiseZiel());
	}

	/**
	 * Test method for {@link model.Bus#setReiseZiel(model.Reiseziel)}.
	 */
	@Test
	public void testSetReiseZiel() {
		bus1.setReiseZiel(Reiseziel.MADRID);
		assertEquals(ERROR, Reiseziel.MADRID, bus1.getReiseZiel());
	}

}
