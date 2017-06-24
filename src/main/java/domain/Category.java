package domain;

public enum Category {
	graphics,
	motherboard,
	hdd,
	ram;
	
	public static Category fromString(final String s) {
	    return Category.valueOf(s);
	}
}
