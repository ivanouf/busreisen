package model;

/**
 * Diese Klasse wird benutzt, um die Teilnehmer einer nach Woche und Ziel
 * spezifizierten Reise zu verwalten.
 * 
 * @author Philipp
 * @version 17.03.2012
 */
public class TeilnehmerListe {

	/**
	 * Instanz von {@link StringBuilder} zur effizienten Verkettung von Strings,
	 * die jeweils einen Teilnehmer modellieren.
	 */
	private StringBuilder teilnehmer;

	/**
	 * Aktuelle Menge der Teilnehmer
	 */
	private int menge;

	/**
	 * Der Konstruktor instanziiert einen StringBuilder, der die Teilnehmer
	 * einer Reise als Strings verkettet. Au�erdem wird die Menge an Teilnehmern
	 * mit 0 initialisiert.
	 */
	public TeilnehmerListe() {
		teilnehmer = new StringBuilder();
		this.menge = 0;
	}

	/**
	 * Diese Methode f�gt einen Teilnehmer zur Liste hinzu.
	 * 
	 * @param kunde
	 *            Instanz von {@link Kunde}, die die Daten des Teilnehmers
	 *            enth�lt
	 * @param aktuellePlaetze
	 *            Anzahl der Pl�tze, die der Kunde aktuell gebucht hat
	 */
	public void appendTeilnehmer(Kunde kunde, int aktuellePlaetze) {
		teilnehmer.append(kunde.toString() + ":"
				+ String.valueOf(aktuellePlaetze) + ";");
		menge++;
	}

	/**
	 * Diese Methode gibt die aktuellen gebuchten Pl�tze zum Reiseteilnehmer
	 * zur�ck.
	 * 
	 * @param kunde
	 *            Instanz von {@link Kunde}, zu dem die Pl�tze zur�ckgegeben
	 *            werden sollen
	 * @return Anzahl der Pl�tze als Integer <br>
	 *         -1, wenn der Kunde nicht in der Liste steht
	 */
	public int getPlaetzeZumKunden(Kunde kunde) {
		String teilnehmerListe = teilnehmer.toString();
		if (!(teilnehmerListe.equals(""))) {
			String[] entries = teilnehmerListe.split(";");
			for (int i = 0; i < entries.length; i++) {
				String[] aktKunde = entries[i].split(":");
				int kdNummer = Integer.parseInt(aktKunde[0]);
				if (kdNummer == kunde.getNummer()) {
					return Integer.parseInt(aktKunde[5]);
				}
			}
		}
		return -1;
	}

	/**
	 * Diese Methode l�scht einen Teilnehmer aus der Liste.
	 * 
	 * @param kunde
	 *            Instanz von {@link Kunde}, die die Daten des zu l�schenden
	 *            Teilnehmer enth�lt.
	 * @param plaetze
	 *            Anzahl der Pl�tze, die der Teilnehmer gebucht hatte
	 */
	public void removeTeilnehmer(Kunde kunde, int plaetze) {
		String aktTeilnehmer = kunde.toString() + ":" + String.valueOf(plaetze)
				+ ";";
		int start = teilnehmer.indexOf(aktTeilnehmer);
		if (start >= 0) {
			teilnehmer.delete(start, start + aktTeilnehmer.length());
		}
		menge--;
	}

	/**
	 * Diese Methode pr�ft, ob ein spezifizierter Teilnehmer in der Liste
	 * enthalten ist.
	 * 
	 * @param kunde
	 *            Instanz von {@link Kunde}, die die Daten des Teilnehmers
	 *            enth�lt
	 * @return true or false
	 */
	public boolean containsTeilnehmer(Kunde kunde) {
		return (getPlaetzeZumKunden(kunde) != -1);
	}

	/**
	 * Diese Methode aktualisiert die gebuchten Pl�tze des spezifizierten
	 * Teilnehmers.
	 * 
	 * @param kunde
	 *            Instanz von {@link Kunde}, die die Daten des Teilnehmers
	 *            enth�lt
	 * @param neueAnzahlPlaetze
	 *            aktuelle Anzahl der gebuchten Pl�tze des Kunden
	 */
	public void reduziereGebuchtePlaetze(Kunde kunde, int neueAnzahlPlaetze) {
		removeTeilnehmer(kunde, getPlaetzeZumKunden(kunde));
		appendTeilnehmer(kunde, neueAnzahlPlaetze);
	}

	/**
	 * Diese Methode erstellt ein Objekt vom Typ {@link Kunde} und belegt es mit
	 * den Daten aus dem �bergebenen String.
	 * 
	 * @param s
	 *            String mit Kundendaten
	 * @return Instanz der Klasse {@link Kunde}
	 */
	private Kunde erstelleKundeAusString(String s) {
		String[] data = s.split(":");
		Kunde kunde = new Kunde();
		kunde.setNummer(Integer.parseInt(data[0]));
		kunde.setName(data[1]);
		kunde.setVorname(data[2]);
		kunde.setAdresse(data[3]);
		kunde.setTelefonnr(data[4]);
		return kunde;
	}

	/**
	 * Diese Methode gibt alle Teilnehmer der Reise als Array aus.
	 * 
	 * @return Array von Objekten vom Typ {@link Kunde}.
	 */
	public Kunde[] toArray() {
		Kunde[] kunden = new Kunde[menge];
		String[] kundenStrings = teilnehmer.toString().split(";");
		for (int i = 0; i < menge; i++) {
			kunden[i] = erstelleKundeAusString(kundenStrings[i]);
		}
		return kunden;
	}
}
