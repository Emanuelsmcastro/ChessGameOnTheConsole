package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChessMatch chessMatch = new ChessMatch();
		Scanner scanner = new Scanner(System.in);
		while(true) {
			try {
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces());
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(scanner);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.println("Target: ");
				ChessPosition target = UI.readChessPosition(scanner);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			} catch(ChessException e) {
				System.out.println(e.getMessage());
				scanner.nextLine();
			} catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				scanner.nextLine();
			}
		}
	}

}
