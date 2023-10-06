package chess;

public enum Color {
	BLACK("\u001B[30m"),
	WHITE("\u001B[37m"),
	BLUE("\u001B[34m"),
	RED("\u001B[31m"),
	ANSI_GREEN("\u001B[32m"),
	ANSI_YELLOW("\u001B[33m"),
	ANSI_PURPLE("\u001B[35m"),
	ANSI_CYAN("\u001B[36m"),
	BLACK_BACKGROUND("\u001B[40m"),
	RED_BACKGROUND("\u001B[41m"),
	GREEN_BACKGROUND("\u001B[42m"),
	YELLOW_BACKGROUND("\u001B[43m"),
	BLUE_BACKGROUND("\u001B[44m"),
	PURPLE_BACKGROUND("\u001B[45m"),
	CYAN_BACKGROUND("\u001B[46m"),
	WHITE_BACKGROUND("\u001B[47m"),
	RESET("\u001B[0m");

	private String color;

	Color(String color) {
		this.color = color;
	}

	public String getValue() {
		return color;
	}
}
