/**
 * 
 */
package tests;

import static org.junit.Assert.fail;
import model.Bus;
import model.Reiseziel;

import org.junit.Before;
import org.junit.Test;

/**
 * @author philipp
 * 
 */
public class BusTest {

	private Bus bus1;
	private Bus bus2;

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
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Bus#setAnzahlPlaetze(int)}.
	 */
	@Test
	public void testSetAnzahlPlaetze() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Bus#getAnzahlFreiePlaetze()}.
	 */
	@Test
	public void testGetAnzahlFreiePlaetze() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Bus#setAnzahlFreiePlaetze(int)}.
	 */
	@Test
	public void testSetAnzahlFreiePlaetze() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Bus#getWoche()}.
	 */
	@Test
	public void testGetWoche() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Bus#setWoche(int)}.
	 */
	@Test
	public void testSetWoche() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Bus#getReiseZiel()}.
	 */
	@Test
	public void testGetReiseZiel() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Bus#setReiseZiel(model.Reiseziel)}.
	 */
	@Test
	public void testSetReiseZiel() {
		fail("Not yet implemented");
	}

}
