package bpod;

import bpod.Game.colors;

public class Pawn extends Piece {

	
	public Pawn(pieceType type, colors color, int x, int y) {
		super(type, color, x, y);
	}
	
	public static boolean openingPawnMove(Board board, Piece piece) {
		if(piece != null) {
			if (board.boardConf[piece.y][piece.x].color == colors.BLACK) {		
				//Cazuri in care pionul cucereste alta piesa
				if (board.attackMove(piece.x - 1, piece.y - 1) == true) {
					board.boardConf[piece.y][piece.x].type = Piece.pieceType.PAWN;
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y - 1);
					return true;
				}
				
				if (board.attackMove(piece.x + 1, piece.y - 1) == true) {
					board.boardConf[piece.y][piece.x].type = Piece.pieceType.PAWN;
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y - 1);
					return true;
				}
				
				//Cazuri in care pionul avanseaza
				if (board.possibleMove(piece.x, piece.y - 2) == true) {
					board.boardConf[piece.y][piece.x].type = Piece.pieceType.PAWN;
					board.makeMove(piece.x, piece.y, piece.x, piece.y - 2);
					return true;
				}
				
				if (board.possibleMove(piece.x, piece.y - 1) == true) {
					board.boardConf[piece.y][piece.x].type = Piece.pieceType.PAWN;
					board.makeMove(piece.x, piece.y, piece.x, piece.y - 1);
					return true;
				}
			} else {
				//Cazuri in care pionul cucereste alta piesa
				if (board.attackMove(piece.x - 1, piece.y + 1) == true) {
					board.boardConf[piece.y][piece.x].type = Piece.pieceType.PAWN;
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y + 1);
					return true;
				}
				
				if (board.attackMove(piece.x + 1, piece.y + 1) == true) {
					board.boardConf[piece.y][piece.x].type = Piece.pieceType.PAWN;
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y + 1);
					return true;
				} 
				
				//Cazuri in care pionul avanseaza
				if (board.possibleMove(piece.x, piece.y + 2) == true) {
					board.boardConf[piece.y][piece.x].type = Piece.pieceType.PAWN;
					board.makeMove(piece.x, piece.y, piece.x, piece.y + 2);
					return true;
				}
				
				if (board.possibleMove(piece.x, piece.y + 1) == true) {
					board.boardConf[piece.y][piece.x].type = Piece.pieceType.PAWN;
					board.makeMove(piece.x, piece.y, piece.x, piece.y + 1);
					return true;
				}
			}
			//nu a reusit sa faca miscare
			return false;
		}
		// piesa e null
		return false;
	}
	
	public static boolean basicPawnMove(Board board, Piece piece) {
		if(piece != null) {
			if (board.boardConf[piece.y][piece.x].color == colors.BLACK) {
				//Cazuri in care pionul cucereste alta piesa
				if (board.attackMove(piece.x - 1, piece.y - 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y - 1);
					// daca a promovat in regina, dam resign
					if (piece.y == 1) {
						board.boardConf[piece.y][piece.x].type = Piece.pieceType.QUEEN;
						return false;
					}
					return true;
				}  
				
				if (board.attackMove(piece.x + 1, piece.y - 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y - 1);
					// daca a promovat in regina, dam resign
					if (piece.y == 1) {
						board.boardConf[piece.y][piece.x].type = Piece.pieceType.QUEEN;
						return false;
					}
					return true;
				}
				
				//Cazuri EN PASSANT	
				if ((piece.y == 4) && (board.attackMove(piece.x + 1, piece.y) == true)) {
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y - 1);
					return true;
				}
				
				if ((piece.y == 4) && (board.attackMove(piece.x - 1, piece.y) == true)) {
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y - 1);
					return true;
				}
								
				//Cazuri in care pionul avanseaza
				if (board.possibleMove(piece.x, piece.y - 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x, piece.y - 1);
					// daca a promovat in regina, dam resign
					if (piece.y == 1) {
						board.boardConf[piece.y][piece.x].type = Piece.pieceType.QUEEN;
						return false;
					}
					return true;
				}
			} else {
				//Cazuri in care pionul cucereste alta piesa
				if (board.attackMove(piece.x - 1, piece.y + 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y + 1);
					// daca a promovat in regina, dam resign
					if (piece.y == 8) {
						board.boardConf[piece.y][piece.x].type = Piece.pieceType.QUEEN;
						return false;
					}
					return true;
				}
				
				if (board.attackMove(piece.x + 1, piece.y + 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y + 1);
					// daca a promovat in regina, dam resign
					if (piece.y == 8) {
						board.boardConf[piece.y][piece.x].type = Piece.pieceType.QUEEN;
						return false;
					}
					return true;
				}
				
				//Cazuri EN PASSANT	
				if ((piece.y == 5) && (board.attackMove(piece.x + 1, piece.y) == true)) {
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y + 1);
					return true;
				}
				
				if ((piece.y == 5) && (board.attackMove(piece.x - 1, piece.y) == true)) {
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y + 1);
					return true;
				}
				
				//Cazuri in care pionul avanseaza
				if (board.possibleMove(piece.x, piece.y + 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x, piece.y + 1);
					// daca a promovat in regina, dam resign
					if (piece.y == 8) {
						board.boardConf[piece.y][piece.x].type = Piece.pieceType.QUEEN;
						return false;
					}
					return true;
				}
			}
			//nu a reusit sa faca miscare
			return false;
		}
		// piesa e null
		return false;
	}

	public static boolean pawnMove(Board board, Piece piece) {
		if(piece.type == Piece.pieceType.OPENINGPAWN) {
			return openingPawnMove(board, piece);
		}
		return basicPawnMove(board, piece);
	}

}
