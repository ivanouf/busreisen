package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Kunde;
import model.Reiseziel;

/**
 * Diese Klasse ist für die Ein- und die Ausgaben auf der Konsole zuständig.
 * 
 * @author Philipp
 * @version 12.03.2012
 * 
 */
public class KonsoleIO {

	/**
	 * Fehlermeldung, wenn das Einlesen von der Konsole nicht funktioniert hat
	 */
	private static final String IO_ERROR = "Beim Einlesen ist ein Fehler aufgetreten!";

	/**
	 * Fehlermeldung, wenn die Eingabe nicht zum Integer konvertiert werden kann
	 */
	private static final String INTEGER_ERROR = "Das eingegebene Zeichen ist keine ganze Zahl!";

	/**
	 * Fehlermeldung, wenn die Eingabe nicht zum Double konvertiert werden kann
	 */
	private static final String DOUBLE_ERROR = "Das eingegebene Zeichen ist keine Gleitkommazahl!";

	/**
	 * Diese Methode gibt eine einfache Meldung auf der Konsole aus.
	 * 
	 * @param msg
	 *            Nachricht, die den Benutzer über den Erfolg einer Operation
	 *            informiert
	 */
	public static void printErfolgsmeldung(String msg) {
		System.out.println("[System] " + msg);
	}

	/**
	 * Diese Methode gibt eine Fehlermeldung auf der Konsole aus.
	 * 
	 * @param msg
	 *            Nachricht, die den Benutzer über die Art des Fehlers
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

	/**
	 * Diese Methode filtert aus einer Auswahl von Kunden einen spezifizierten
	 * Kunden heraus.
	 * 
	 * @param kunden
	 *            Array von Instanzen vom Typ {@link Kunde}
	 * @return Position des gewünschten Kunden im Array
	 */
	public static int readGewuenschterKunde(Kunde[] kunden) {
		int menge = kunden.length;
		System.out.println("Spezifizieren Sie Ihre Auswahl:");
		for (int i = 0; i < menge; i++) {
			System.out.println(kunden[i].getVorname() + " "
					+ kunden[i].getName() + ", " + kunden[i].getAdresse()
					+ ", " + kunden[i].getTelefonnr() + " [" + (i + 1) + "]");
		}
		return readIntegerFromConsole("Geben Sie die entsprechende Nummer hinter dem Datensatz ein: ") - 1;
	}

	/**
	 * Diese Methode ermöglicht es dem Benutzer, das Reiseziel über die Konsole
	 * zu bestimmen.
	 * 
	 * @param msg
	 *            Nachricht, die den Eingabedialog einleitet
	 * @return Wert von {@link Reiseziel}
	 */
	public static Reiseziel readGewuenschtesReiseziel(String msg) {
		System.out.println(msg);
		System.out.println("Madrid: [1]");
		System.out.println("Berlin: [2]");
		System.out.println("Wien:   [3]");
		System.out.println("Rom:    [4]");
		int antwort = 0;
		do {
			antwort = readIntegerFromConsole("Geben Sie die entsprechende Nummer an:");
			switch (antwort) {
			case 1:
				return Reiseziel.MADRID;
			case 2:
				return Reiseziel.BERLIN;
			case 3:
				return Reiseziel.WIEN;
			case 4:
				return Reiseziel.ROM;
			default:
				printFehlermeldung("Diese Zahl ist ungültig!");
			}
		} while ((antwort < 1) || (antwort > 4));
		return null;
	}

}
