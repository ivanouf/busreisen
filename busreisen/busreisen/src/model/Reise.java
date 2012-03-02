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

	
}
