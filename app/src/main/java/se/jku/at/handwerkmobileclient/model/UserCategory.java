package se.jku.at.handwerkmobileclient.model;

public enum UserCategory {
	// do not alter the order of the values
	GUEST(0), USER(1), ADMINISTRATOR(2);

	int value;
	
	UserCategory(int i) {
		this.value = i;
	}
	
	public int getValue() {
		return this.value;
	}
	
	private static UserCategory[] allValues = values();
    public static UserCategory fromOrdinal(int n) {return allValues[n];}
}
