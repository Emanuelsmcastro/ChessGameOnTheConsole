package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;

	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.BLUE;
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];

		for (int r = 0; r < board.getRows(); r++) {
			for (int c = 0; c < board.getColumns(); c++) {
				mat[r][c] = (ChessPiece) board.piece(r, c);
			}
		}
		return mat;
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);

		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check.");
		}

		check = testCheck(opponent(currentPlayer)) ? true : false;
		checkMate = testCheckMate(opponent(currentPlayer)) ? true: false;
		
		if(!checkMate)
			nextTurn();
		
		return (ChessPiece) capturedPiece;
	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}

	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece) board.removePiece(source);
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}

	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece) board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position))
			throw new ChessException("There is no piece on source position");
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor())
			throw new ChessException("The chosen piece is not yours.");
		if (!board.piece(position).isThereAnyPossibleMove())
			throw new ChessException("There is no possible moves for the chosen piece.");
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target))
			throw new ChessException("The chosen piece can't move to target position.");
	}

	private void initialSetup() {
		placeNewPiece('a', 1, new Rook(board, Color.BLUE));
		placeNewPiece('b', 1, new Knight(board, Color.BLUE));
		placeNewPiece('c', 1, new Bishop(board, Color.BLUE));
		placeNewPiece('d', 1, new Queen(board, Color.BLUE));
        placeNewPiece('e', 1, new King(board, Color.BLUE));
        placeNewPiece('f', 1, new Bishop(board, Color.BLUE));
        placeNewPiece('g', 1, new Knight(board, Color.BLUE));
        placeNewPiece('h', 1, new Rook(board, Color.BLUE));
        placeNewPiece('a', 2, new Pawn(board, Color.BLUE));
        placeNewPiece('b', 2, new Pawn(board, Color.BLUE));
        placeNewPiece('c', 2, new Pawn(board, Color.BLUE));
        placeNewPiece('d', 2, new Pawn(board, Color.BLUE));
        placeNewPiece('e', 2, new Pawn(board, Color.BLUE));
        placeNewPiece('f', 2, new Pawn(board, Color.BLUE));
        placeNewPiece('g', 2, new Pawn(board, Color.BLUE));
        placeNewPiece('h', 2, new Pawn(board, Color.BLUE));

        placeNewPiece('a', 8, new Rook(board, Color.RED));
        placeNewPiece('b', 8, new Knight(board, Color.RED));
        placeNewPiece('c', 8, new Bishop(board, Color.RED));
        placeNewPiece('d', 8, new Queen(board, Color.RED));
        placeNewPiece('e', 8, new King(board, Color.RED));
        placeNewPiece('f', 8, new Bishop(board, Color.RED));
        placeNewPiece('g', 8, new Knight(board, Color.RED));
        placeNewPiece('h', 8, new Rook(board, Color.RED));
        placeNewPiece('a', 7, new Pawn(board, Color.RED));
        placeNewPiece('b', 7, new Pawn(board, Color.RED));
        placeNewPiece('c', 7, new Pawn(board, Color.RED));
        placeNewPiece('d', 7, new Pawn(board, Color.RED));
        placeNewPiece('e', 7, new Pawn(board, Color.RED));
        placeNewPiece('f', 7, new Pawn(board, Color.RED));
        placeNewPiece('g', 7, new Pawn(board, Color.RED));
        placeNewPiece('h', 7, new Pawn(board, Color.RED));
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.BLUE) ? Color.RED : Color.BLUE;
	}

	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if(!testCheck(color))
			return false;
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			for(int r = 0; r < mat.length; r++) {
				for(int c = 0; c < mat.length; c++) {
					if(mat[r][c]) {
						Position source = ((ChessPiece) p).getChessPosition().toPosition();
						Position target = new Position(r, c);
						Piece capturedPiece = makeMove(source, target);
						
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if(!testCheck)
							return false;
					}
				}
			}
		}
		return true;
	}

	private Color opponent(Color color) {
		if (color == Color.BLUE)
			return Color.RED;
		else if (color == Color.RED)
			return Color.BLUE;
		return null;
	}

	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece piece : list) {
			if (piece instanceof King)
				return (ChessPiece) piece;
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}

}
