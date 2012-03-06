package tests;

import static org.junit.Assert.assertEquals;
import model.Kunde;

import org.junit.Before;
import org.junit.Test;

/**
 * Dieser TestCase testet die Klasse {@link Kunde}.
 * 
 * @author Philipp
 * @version 06.03.2012
 */
public class KundenTest {

	private Kunde kunde1;
	private Kunde kunde2;
	private static final String ERROR = "Werte stimmen nicht überein.";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		kunde1 = new Kunde();
		kunde2 = new Kunde("Mueller", "Heinz", "Hauptstraße 2", "022454202");
	}

	/**
	 * Test method for {@link model.Kunde#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals(ERROR, "Mueller", kunde2.getName());
	}

	/**
	 * Test method for {@link model.Kunde#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		kunde1.setName("Schmitz");
		assertEquals(ERROR, "Schmitz", kunde1.getName());
	}

	/**
	 * Test method for {@link model.Kunde#getVorname()}.
	 */
	@Test
	public void testGetVorname() {
		assertEquals(ERROR, "Mueller", kunde2.getName());
	}

	/**
	 * Test method for {@link model.Kunde#setVorname(java.lang.String)}.
	 */
	@Test
	public void testSetVorname() {
		kunde1.setVorname("Ralf");
		assertEquals(ERROR, "Ralf", kunde1.getVorname());
	}

	/**
	 * Test method for {@link model.Kunde#getAdresse()}.
	 */
	@Test
	public void testGetAdresse() {
		assertEquals(ERROR, "Hauptstraße 2", kunde2.getAdresse());
	}

	/**
	 * Test method for {@link model.Kunde#setAdresse(java.lang.String)}.
	 */
	@Test
	public void testSetAdresse() {
		kunde1.setAdresse("Holzgasse 5");
		assertEquals(ERROR, "Holzgasse 5", kunde1.getAdresse());
	}

	/**
	 * Test method for {@link model.Kunde#getTelefonnr()}.
	 */
	@Test
	public void testGetTelefonnr() {
		assertEquals(ERROR, "022454202", kunde2.getTelefonnr());
	}

	/**
	 * Test method for {@link model.Kunde#setTelefonnr(java.lang.String)}.
	 */
	@Test
	public void testSetTelefonnr() {
		kunde1.setTelefonnr("12345");
		assertEquals(ERROR, "12345", kunde1.getTelefonnr());
	}

}
