package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

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

	public static void printMatch(ChessMatch chessMatch) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		System.out.println("Turn: " + chessMatch.getTurn());
		if (chessMatch.getCheckMate()) {
			System.out.println("CHECKMATE!");
			System.out.println("Winner: " + chessMatch.getCurrentPlayer());
		} else {
			System.out.println(chessMatch.getCurrentPlayer().getValue() + "Waiting player: " + chessMatch.getCurrentPlayer()
			+ Color.RESET.getValue());
			if (chessMatch.getCheck()) {
				System.out.println("CHECK!");
			}
		}
	}

	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printMatch(chessMatch);
		printCapturedPieces(captured);
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

	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		System.out.println();
		for (int r = 0; r < pieces.length; r++) {
			System.out.print(" " + (8 - r) + " ");
			for (int c = 0; c < pieces.length; c++) {
				printPiece(pieces[r][c], possibleMoves[r][c]);
			}
			System.out.println();
		}
		System.out.println("   a b c d e f g h");
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private static void printPiece(ChessPiece piece) {
		if (piece == null)
			System.out.print("-");
		else
			System.out.print(piece);
		System.out.print(" ");
	}

	private static void printPiece(ChessPiece piece, boolean possibleMove) {
		if (piece == null && possibleMove)
			System.out.print(Color.GREEN_BACKGROUND.getValue() + "-" + Color.RESET.getValue());
		else if (piece == null)
			System.out.print("-");
		else if (piece != null && possibleMove)
			System.out.print(Color.GREEN_BACKGROUND.getValue() + piece + Color.RESET.getValue());
		else
			System.out.print(piece);
		System.out.print(" ");
	}

	private static void printCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> p1 = captured.stream().filter(cap -> cap.getColor() == Color.BLUE)
				.collect(Collectors.toList());
		List<ChessPiece> p2 = captured.stream().filter(cap -> cap.getColor() == Color.RED).collect(Collectors.toList());

		System.out.println("\nCaptured pieces:");
		System.out.print(Color.BLUE.getValue());
		System.out.println("Blue: ");
		System.out.print(Color.RESET.getValue());
		System.out.println(Arrays.toString(p1.toArray()));

		System.out.print(Color.RED.getValue());
		System.out.println("Red: ");
		System.out.print(Color.RESET.getValue());
		System.out.println(Arrays.toString(p2.toArray()));
	}
}
