package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Diese Klasse ist für die Ein- und die Ausgaben auf der Konsole zuständig.
 * 
 * @author Philipp
 * @version 06.03.2012
 * 
 */
public class KonsoleIO {

	/**
	 * Fehlermeldung, wenn das Einlesen von der Konsole nicht funktioniert hat
	 */
	private static String IO_ERROR = "Beim Einlesen ist ein Fehler aufgetreten!";

	/**
	 * Fehlermeldung, wenn die Eingabe nicht zum Integer konvertiert werden kann
	 */
	private static String INTEGER_ERROR = "Das eingegebene Zeichen ist keine ganze Zahl!";

	/**
	 * Fehlermeldung, wenn die Eingabe nicht zum Double konvertiert werden kann
	 */
	private static String DOUBLE_ERROR = "Das eingegebene Zeichen ist keine Gleitkommazahl!";

	/**
	 * Diese Methode gibt eine einfache Meldung auf der Konsole aus.
	 * 
	 * @param msg
	 *            Nachricht, die den Benutzer �ber den Erfolg einer Operation
	 *            informiert
	 */
	public static void printErfolgsmeldung(String msg) {
		System.out.println(msg);
	}

	/**
	 * Diese Methode gibt eine Fehlermeldung auf der Konsole aus.
	 * 
	 * @param msg
	 *            Nachricht, die den Benutzer �ber die Art des Fehlers
	 *            informiert
	 */
	public static void printFehlermeldung(String msg) {
		System.err.println("\nFehler: " + msg);
	}

	/**
	 * Diese Methode liest eine ganze Zahl von der Konsole ein.
	 * 
	 * @param msg
	 *            Nachricht, die den Eingabedialog einleitet.
	 * @return ganze Zahl
	 */
	public static int readIntegerFromConsole(String msg) {
		System.out.print(msg);
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					System.in));
			String s = input.readLine();
			return Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			KonsoleIO.printFehlermeldung(INTEGER_ERROR);
			return 0;
		} catch (IOException ioe) {
			KonsoleIO.printFehlermeldung(IO_ERROR);
			return 0;
		}
	}

	/**
	 * Diese Methode liest eine Gleitkommazahl von der Konsole ein.
	 * 
	 * @param msg
	 *            Nachricht, die den Eingabedialog einleitet.
	 * @return Gleitkommazahl
	 */
	public static double readDoubleFromConsole(String msg) {
		System.out.print(msg);
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					System.in));
			String s = input.readLine();
			return Double.parseDouble(s);
		} catch (NumberFormatException nfe) {
			KonsoleIO.printFehlermeldung(DOUBLE_ERROR);
			return 0;
		} catch (IOException ioe) {
			KonsoleIO.printFehlermeldung(IO_ERROR);
			return 0;
		}
	}

	/**
	 * Diese Methode liest ein Zeichen von der Konsole ein.
	 * 
	 * @param msg
	 *            Nachricht, die den Eingabedialog einleitet.
	 * @return Zeichen als Character
	 */
	public static char readCharFromConsole(String msg) {
		System.out.print(msg);
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					System.in));
			String s = input.readLine();
			return s.charAt(0);
		} catch (IOException ioe) {
			KonsoleIO.printFehlermeldung(IO_ERROR);
			return ' ';
		}
	}

	/**
	 * Diese Methode liest eine Zeichenkette von der Konsole ein.
	 * 
	 * @param msg
	 *            Nachricht, die den Eingabedialog einleitet.
	 * @return Zeichenkette als String
	 */
	public static String readStringFromConsole(String msg) {
		System.out.print(msg);
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					System.in));
			return input.readLine();
		} catch (IOException ioe) {
			KonsoleIO.printFehlermeldung(IO_ERROR);
			return " ";
		}
	}
}
