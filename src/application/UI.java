package application;

import chess.ChessPiece;

public class UI {
	
	public static void printBoard(ChessPiece[][] pieces) {
		System.out.println();
		for(int r = 0; r < pieces.length; r++) {
			System.out.print(" " + (8 - r) + " ");
			for(int c = 0; c < pieces.length; c++) {
				printPiece(pieces[r][c]);
			}
			System.out.println();
		}
		System.out.println("   a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece piece) {
		if (piece == null)
			System.out.print("-");
		else
			System.out.print(piece);
		System.out.print(" ");
	}
}
