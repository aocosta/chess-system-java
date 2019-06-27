package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
	
	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		if (getColor() == Color.WHITE) {
			Position p1 = new Position(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
				mat[p1.getRow()][p1.getColumn()] = true;
			}
			
			Position p2 = new Position(position.getRow() - 2, position.getColumn());
			if (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p2.getRow()][p2.getColumn()] = true;
			}

			p1.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
				mat[p1.getRow()][p1.getColumn()] = true;
			}
			
			p1.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
				mat[p1.getRow()][p1.getColumn()] = true;
			}
			
			// Movimento especial en Passant
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn()-1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow()-1][left.getColumn()] = true;
				}
				
				Position right = new Position(position.getRow(), position.getColumn()+1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow()-1][right.getColumn()] = true;
				}
			}
		}
		else {
			Position p1 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
				mat[p1.getRow()][p1.getColumn()] = true;
			}
			
			Position p2 = new Position(position.getRow() + 2, position.getColumn());
			if (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p2.getRow()][p2.getColumn()] = true;
			}

			p1.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
				mat[p1.getRow()][p1.getColumn()] = true;
			}
			
			p1.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
				mat[p1.getRow()][p1.getColumn()] = true;
			}			

			// Movimento especial en Passant
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn()-1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow()+1][left.getColumn()] = true;
				}
				
				Position right = new Position(position.getRow(), position.getColumn()+1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow()+1][right.getColumn()] = true;
				}
			}

		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
}
