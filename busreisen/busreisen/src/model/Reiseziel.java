package model;

public enum Reiseziel {
			
	MADRID,
	WIEN,
	ROM,
	BERLIN;
	public String test(Reiseziel ziel){
		switch(ziel){
		case MADRID:
			return "Madrid";
		case WIEN:
			return "Wien";
		case ROM:
			return "Rom";
		case BERLIN:
			return "Berlin";
		default:
			return "";
		}
	}
}
