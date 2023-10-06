package chess.pieces;

public enum PieceSymbol {
	HOOK("♜"), KING("♚");

	private String value;

	PieceSymbol(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
