import java.util.ArrayList;


public class Pawn extends Piece {

	boolean enPassantPossibilityLeft;
	boolean enPassantPossibilityRight;

	public Pawn(pieceType type, Game.colors color, int x, int y) {
		super(type, color, x, y);
		enPassantPossibilityLeft = true;
		enPassantPossibilityRight = true;
	}
	

	public Pawn(pieceType type, Game.colors color, int x, int y, boolean enPassantPossibilityLeft,
			boolean enPassantPossibilityRight) {
		super(type, color, x, y);
		this.enPassantPossibilityLeft = enPassantPossibilityLeft;
		this.enPassantPossibilityRight = enPassantPossibilityRight;
	}

	// TODO, nu garantez pentru enPassant
	// stiu sigur ca daca face oponentul en Passant, noi nu il tratam
	// sa te mai gandesti la En Passant, poate il faci mai simplu, dar cel mai important e sa functioneze
	
	// pot fi pioni langa pe liniile de en passant dar mutarea sa nu fie valida
	public static void enPassantIllusion(Board board, Pawn pwn, int newX, int newY) {

		if ((newY == 4) && (pwn.color == Game.colors.BLACK)
				&& (board.attackMove(newX - 1, newY) || board.attackMove(newX - 1, newY - 1))) {
			pwn.enPassantPossibilityRight = false;
		}

		if ((newY == 5) && (pwn.color == Game.colors.WHITE)
				&& (board.attackMove(newX + 1, newY) || board.attackMove(newX + 1, newY + 1))) {
			pwn.enPassantPossibilityRight = false;
		}

		if ((newY == 4) && (pwn.color == Game.colors.BLACK)
				&& (board.attackMove(newX + 1, newY) || board.attackMove(newX + 1, newY - 1))) {
			pwn.enPassantPossibilityLeft = false;
		}

		if ((newY == 5) && (pwn.color == Game.colors.WHITE)
				&& (board.attackMove(newX - 1, newY) || board.attackMove(newX - 1, newY + 1))) {
			pwn.enPassantPossibilityLeft = false;
		}
	}

	public static ArrayList<Move> openingPawnMoves(Board board, Pawn piece) {
		ArrayList<Move> moves = new ArrayList<Move>();
		if (piece != null && board.engineColor == piece.color) {
			if (board.boardConf[piece.y][piece.x].color == Game.colors.BLACK) {
				// Cazuri in care pionul cucereste alta piesa
				if (board.attackMove(piece.x - 1, piece.y - 1) == true) {
					moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y - 1, 
					new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight)));
				}

				if (board.attackMove(piece.x + 1, piece.y - 1) == true) {
					moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y - 1, 
					new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight)));
				}

				// Cazuri in care pionul avanseaza
				if (board.possibleMove(piece.x, piece.y - 2) == true && board.possibleMove(piece.x, piece.y - 1) == true) {
					moves.add(new Move(piece.x, piece.y, piece.x, piece.y - 2, 
					new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight)));
				}

				if (board.possibleMove(piece.x, piece.y - 1) == true) {
					moves.add(new Move(piece.x, piece.y, piece.x, piece.y - 1, 
					new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight)));
				}
			} else {
				// Cazuri in care pionul cucereste alta piesa
				if (board.attackMove(piece.x - 1, piece.y + 1) == true) {
					moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y + 1, 
					new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight)));
				}

				if (board.attackMove(piece.x + 1, piece.y + 1) == true) {
					moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y + 1, 
					new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight)));
				}

				// Cazuri in care pionul avanseaza
				if (board.possibleMove(piece.x, piece.y + 2) == true && board.possibleMove(piece.x, piece.y + 1) == true) {
					moves.add(new Move(piece.x, piece.y, piece.x, piece.y + 2, 
					new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight)));
				}

				if (board.possibleMove(piece.x, piece.y + 1) == true) {
					moves.add(new Move(piece.x, piece.y, piece.x, piece.y + 1, 
					new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight)));
				}
			}
		}
		return moves;
	}

	public static ArrayList<Move> basicPawnMoves(Board board, Pawn piece) {
		ArrayList<Move> moves = new ArrayList<Move>();
		if (piece != null && board.engineColor == piece.color) {
			if (board.boardConf[piece.y][piece.x].color == Game.colors.BLACK) {
				// Cazuri in care pionul cucereste alta piesa
				if (board.attackMove(piece.x - 1, piece.y - 1) == true) {
					Pawn pawn = new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight);
					enPassantIllusion(board, pawn, piece.x -1, piece.y - 1);
					moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y - 1, pawn));
				}

				if (board.attackMove(piece.x + 1, piece.y - 1) == true) {
					Pawn pawn = new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight);
					enPassantIllusion(board, pawn, piece.x +1, piece.y - 1);
					moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y - 1, pawn));
				}

				// Cazuri EN PASSANT
				if ((piece.y == 4) && (board.attackMove(piece.x + 1, piece.y) == true)
						&& ((Pawn) piece).enPassantPossibilityLeft) {
					moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y, 
					new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight),
					piece.x + 1, piece.y));
				}

				if ((piece.y == 4) && (board.attackMove(piece.x - 1, piece.y) == true)
						&& ((Pawn) piece).enPassantPossibilityRight) {
					moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y - 1, 
					new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight),
					piece.x - 1, piece.y));
				}

				// Cazuri in care pionul avanseaza
				if (board.possibleMove(piece.x, piece.y - 1) == true) {
					Pawn pawn = new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight);
					enPassantIllusion(board, pawn, piece.x, piece.y - 1);
					moves.add(new Move(piece.x, piece.y, piece.x, piece.y - 1, pawn));
				}
			} else {

				// Cazuri in care pionul cucereste alta piesa
				if (board.attackMove(piece.x - 1, piece.y + 1) == true) {
					Pawn pawn = new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight);
					enPassantIllusion(board, pawn, piece.x - 1, piece.y + 1);
					moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y + 1, pawn));
				}

				if (board.attackMove(piece.x + 1, piece.y + 1) == true) {
					Pawn pawn = new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight);
					enPassantIllusion(board, pawn, piece.x + 1, piece.y + 1);
					moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y + 1, pawn));
				}

				// Cazuri EN PASSANT
				if ((piece.y == 5) && (board.attackMove(piece.x + 1, piece.y) == true)
						&& ((Pawn) piece).enPassantPossibilityRight) {
					moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y + 1, 
					new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight),
					piece.x + 1, piece.y));
				}

				if ((piece.y == 5) && (board.attackMove(piece.x - 1, piece.y) == true)
						&& ((Pawn) piece).enPassantPossibilityLeft) {
					moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y + 1, 
					new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight),
					piece.x - 1, piece.y));
				}

				// Cazuri in care pionul avanseaza
				if (board.possibleMove(piece.x, piece.y + 1) == true) {
					Pawn pawn = new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight);
					enPassantIllusion(board, pawn, piece.x, piece.y + 1);
					moves.add(new Move(piece.x, piece.y, piece.x, piece.y + 1, pawn));
				}
			}
		}
		// returnam posibilele mutari
		return moves;
	}

	public static ArrayList<Move> pawnMoves(Board board, Pawn piece) {
		if (piece.type == Piece.pieceType.OPENINGPAWN) {
			return openingPawnMoves(board, piece);
		}
		return basicPawnMoves(board, piece);
	}
	
	public static Pawn clone(Pawn piece) {
		return new Pawn(piece.type, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft,
			piece.enPassantPossibilityRight);
	}
}
