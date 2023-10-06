package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;

public class UI {

	public static ChessPosition readChessPosition(Scanner scanner) {
		try {
			String s = scanner.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
		}
	}

	public static void printBoard(ChessPiece[][] pieces) {
		System.out.println();
		for (int r = 0; r < pieces.length; r++) {
			System.out.print(" " + (8 - r) + " ");
			for (int c = 0; c < pieces.length; c++) {
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
