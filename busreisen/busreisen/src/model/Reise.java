package model;
/**
 * Diese Klasse verwaltet die Reisedaten.
 * @author Thomas
 * @version 02.03.2012
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
	 * Der Konstrukter setzt die Startvariablen der Reise
	 * @param ziel
	 * 				Ziel der Reise
	 * @param start
	 * 				Starttag der Reise
	 */
	public Reise(Reiseziel ziel, Wochentag start){
		busse = new Bus[3];
		this.ziel = ziel;
		this.starttag = start;
		richteBusseEin();
		
	}
	/**
	 * Diese Methode gibt das Ziel der Reise zurück.
	 * @return ziel
	 */
	public Reiseziel getReiseziel(){
		return ziel;
	}
	/**
	 * Diese Methode gibt den Starttag zurück.
	 * @return starttag
	 */
	public Wochentag getStarttag(){
		return starttag;
	}
	
	/**
	 * Diese Methode überprüft, ob die Buchung in Ordnung ist.
	 * @param starttag
	 * 				Tag an dem die Reise startet
	 * @param buchung
	 * 				Instanz der Klasse {@link Buchung}
	 * @return Error-Code: <br>
	 * 0 --> Bus nicht überbucht und Starttag OK
	 * 1 --> Bus überbucht und Starttag OK
	 * 2 --> Bus nicht überbucht und Starttag falsch
	 * 3 --> Bus überbucht und Starttag falsch
	 */
	public int buchungOK(Wochentag starttag, Buchung buchung) {
		int rueckgabeTag = 100;
//		switch (starttag) {
//		case MONTAG:
//			rueckgabeTag = 0;
//			break;
//		case FREITAG:
//			rueckgabeTag = 0;
//			break;
//		case SAMSTAG:
//			rueckgabeTag = 0;
//			break;
//		case SONNTAG:
//			rueckgabeTag = 0;
//			break;
//		default:
//			rueckgabeTag = 2;
//			break;
//		}
		
		if (starttag.equals(this.starttag)) {
			rueckgabeTag = 0;
		}
		
		int rueckgabeUeberbucht = 100;
		if(rueckgabeTag == 0) {
			Bus gebuchter_bus = busse[buchung.getWoche()-1];
			if ( gebuchter_bus.getAnzahlFreiePlaetze() >= buchung.getPlaetze() ) {
				rueckgabeUeberbucht = 0;
			}
		}
		int rueckgabe = 10;
		if((rueckgabeTag == 0) && (rueckgabeUeberbucht == 0)){
			return 0;
		}
		else if ((rueckgabeTag > 0) && (rueckgabeUeberbucht == 0)){
			return 2;
		}
		else if (( rueckgabeTag == 0) && (rueckgabeUeberbucht > 0)){
			return 1;
		}
		else {
			return 3;
		}	
	}

	
}
