package chess;

public enum Color {
	BLACK("\u001B[30m"), WHITE("\u001B[37m"), RESET("\u001B[0m");

	private String color;

	Color(String color) {
		this.color = color;
	}

	public String getValue() {
		return color;
	}
}
