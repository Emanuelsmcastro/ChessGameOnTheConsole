package chess;

import boardgame.Board;
import boardgame.Piece;

public class ChessPiece extends Piece {
	private Color color;

	public ChessPiece(Board board, Color color, int moveCount) {
		super(board);
		this.color = color;
	}

	@Override
	public String toString() {
		return "ChessPiece [color=" + color + "]";
	}

	public Color getColor() {
		return color;
	}

}
