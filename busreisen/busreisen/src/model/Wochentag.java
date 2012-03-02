package model;

public enum Wochentag {

	MONTAG,
	FREITAG,
	SAMSTAG,
	SONNTAG;

	public String test(Wochentag tag){
		switch(tag){
		case MONTAG:
			return "Montag";
		case FREITAG:
			return "Freitag";
		case SAMSTAG:
			return "Samstag";
		case SONNTAG:
			return "Sonntag";
		default:
			return "";
		}
	}
}
