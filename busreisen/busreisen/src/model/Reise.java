package model;

/**
 * Diese Klasse verwaltet die Reisedaten.
 * 
 * @author Thomas
 * @version 09.03.2012
 */
public class Reise {
	/**
	 * Item aus der Enumeration {@link Reiseziel}
	 */
	private Reiseziel ziel;
	/**
	 * Item aus der Enumeration {@link Wochentag}
	 */
	private Wochentag starttag;
	/**
	 * Array Busse vom Typ Bus, in dem die Busse verwaltet werden
	 */
	private Bus busse[];

	/**
	 * Diese Methode richtet die Busse ein.
	 */
	private void richteBusseEin() {
		switch (ziel) {
		case MADRID:
			busse[0] = new Bus(30, 1, Reiseziel.MADRID);
			busse[1] = new Bus(30, 2, Reiseziel.MADRID);
			busse[2] = new Bus(30, 3, Reiseziel.MADRID);
			break;
		case WIEN:
			busse[0] = new Bus(40, 1, Reiseziel.WIEN);
			busse[1] = new Bus(40, 2, Reiseziel.WIEN);
			busse[2] = new Bus(40, 3, Reiseziel.WIEN);
			break;
		case ROM:
			busse[0] = new Bus(50, 1, Reiseziel.ROM);
			busse[1] = new Bus(50, 2, Reiseziel.ROM);
			busse[2] = new Bus(50, 3, Reiseziel.ROM);
			break;
		case BERLIN:
			busse[0] = new Bus(60, 1, Reiseziel.BERLIN);
			busse[1] = new Bus(60, 2, Reiseziel.BERLIN);
			busse[2] = new Bus(60, 3, Reiseziel.BERLIN);
			break;
		}
	}

	/**
	 * Der Konstrukter setzt die Startvariablen der Reise.
	 * 
	 * @param ziel
	 *            Ziel der Reise
	 * @param start
	 *            Starttag der Reise
	 */
	public Reise(Reiseziel ziel, Wochentag start) {
		busse = new Bus[3];
		this.ziel = ziel;
		this.starttag = start;
		richteBusseEin();
	}

	/**
	 * Diese Methode gibt das Ziel der Reise zurueck.
	 * 
	 * @return ziel
	 */
	public Reiseziel getReiseziel() {
		return ziel;
	}

	/**
	 * Diese Methode gibt den Starttag zurueck.
	 * 
	 * @return starttag
	 */
	public Wochentag getStarttag() {
		return starttag;
	}

	/**
	 * Diese Methode ueberprueft, ob die Buchung für diese Reise in Ordnung ist.
	 * Dazu wird die Anzahl der freien Plaetze im jeweiligen Bus mit der Anzahl
	 * der gebuchten Plaetze verglichen.
	 * 
	 * @param buchung
	 *            Instanz der Klasse {@link Buchung}
	 * @return true or false
	 * 
	 */
	public boolean buchungOK(Buchung buchung) {
		Bus gebuchter_bus = busse[buchung.getWoche() - 1];
		if (gebuchter_bus.getAnzahlFreiePlaetze() >= buchung.getPlaetze()) {
			return true;
		} else
			return false;
	}

	/**
	 * Diese Methode wird aufgerufen, um bei einer Buchung oder Stornierung die
	 * Busbelegung zu aktualisieren. Bei einer Buchung wird die Anzahl der
	 * freien Plaetze im entsprechenden Bus vermindert. Bei einer Stornierung
	 * werden die belegten Plaetze im entsprechenden Bus freigegeben.
	 * 
	 * @param buchung
	 *            Instanz von {@link Buchung}
	 */
	public void aktualisiereNachBuchung(Buchung buchung) {
		int plaetze;
		Bus bus = busse[buchung.getWoche() - 1];

		// Wenn eine Stornierung vorliegt, ist die Stornonummer > 0.
		if (buchung.getStornonr() > 0) {
			plaetze = bus.getAnzahlFreiePlaetze() + buchung.getPlaetze();
		} else {
			plaetze = bus.getAnzahlFreiePlaetze() - buchung.getPlaetze();
		}

		bus.setAnzahlFreiePlaetze(plaetze);
	}

	/**
	 * Diese Methode liefert den Bus, der in der spezifizierten Woche fährt.
	 * 
	 * @param woche
	 *            Gesuchte Woche als Integer
	 * @return Instanz der Klasse {@link Bus}
	 */
	public Bus getBusZurReisewoche(int woche) {
		if ((woche >= 1) && (woche <= 3))
			return busse[woche - 1];
		else
			return null;
	}
}
