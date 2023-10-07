package chess.pieces;

public enum PieceSymbol {
	ROOK("R"), KING("K"), PAWN("P"), BISHOP("B");

	private String value;

	PieceSymbol(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
