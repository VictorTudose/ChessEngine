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

	// pot fi pioni langa, pe liniile de en passant dar mutarea sa nu fie valida
	public void enPassantIllusion(Board board) {

		if ((this.y == 4) && (this.color == Game.colors.BLACK)) {
			if (board.attackMove(this.x - 1, this.y, this.color) || board.attackMove(this.x - 1, this.y - 1, this.color)) {
				this.enPassantPossibilityLeft = false;
			}
			if (board.attackMove(this.x + 1, this.y, this.color) || board.attackMove(this.x + 1, this.y - 1, this.color)) {
				this.enPassantPossibilityRight = false;
			}
			return;
		}

		if ((this.y == 5) && (this.color == Game.colors.WHITE)) {
			if((board.attackMove(this.x - 1, this.y, this.color) || board.attackMove(this.x - 1, this.y + 1, this.color))) {
				this.enPassantPossibilityLeft = false;
			}
			if ((board.attackMove(this.x + 1, this.y, this.color) || board.attackMove(this.x + 1, this.y + 1, this.color))) {
				this.enPassantPossibilityRight = false;
			}
		}
	}

	public static ArrayList<Move> openingPawnMoves(Board board, Pawn piece) {
		ArrayList<Move> moves = new ArrayList<Move>();
		if (piece != null && board.engineColor == piece.color) {
			if (board.boardConf[piece.y][piece.x].color == Game.colors.BLACK) {
				// Cazuri in care pionul cucereste alta piesa
				if (board.attackMove(piece.x - 1, piece.y - 1, piece.color) == true) {
					moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y - 1,
							new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight)));
				}

				if (board.attackMove(piece.x + 1, piece.y - 1, piece.color) == true) {
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
				if (board.attackMove(piece.x - 1, piece.y + 1, piece.color) == true) {
					moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y + 1,
							new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight)));
				}

				if (board.attackMove(piece.x + 1, piece.y + 1, piece.color) == true) {
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
				if (board.attackMove(piece.x - 1, piece.y - 1, piece.color) ) {
					Pawn pawn = new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight);
					moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y - 1, pawn));
				}

				if (board.attackMove(piece.x + 1, piece.y - 1, piece.color) ) {
					Pawn pawn = new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight);
					moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y - 1, pawn));
				}

				// Cazuri EN PASSANT
				if ((piece.y == 4) && (board.attackMove(piece.x + 1, piece.y, piece.color) ) && (board.boardConf[piece.y][piece.x+1].type == Piece.pieceType.PAWN)
						&& ((Pawn) piece).enPassantPossibilityRight) {
					moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y -1,
							new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight),
							piece.x + 1, piece.y));
				}

				if ((piece.y == 4) && (board.attackMove(piece.x - 1, piece.y, piece.color) ) && (board.boardConf[piece.y][piece.x-1].type == Piece.pieceType.PAWN)
						&& ((Pawn) piece).enPassantPossibilityLeft) {
					moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y - 1,
							new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight),
							piece.x - 1, piece.y));
				}

				// Cazuri in care pionul avanseaza
				if (board.possibleMove(piece.x, piece.y - 1) ) {
					Pawn pawn = new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight);
					moves.add(new Move(piece.x, piece.y, piece.x, piece.y - 1, pawn));
				}
			} else {

				// Cazuri in care pionul cucereste alta piesa
				if (board.attackMove(piece.x - 1, piece.y + 1, piece.color) ) {
					Pawn pawn = new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight);
					moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y + 1, pawn));
				}

				if (board.attackMove(piece.x + 1, piece.y + 1, piece.color) ) {
					Pawn pawn = new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight);
					moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y + 1, pawn));
				}

				// Cazuri EN PASSANT
				if ((piece.y == 5) && (board.attackMove(piece.x + 1, piece.y, piece.color) ) && (board.boardConf[piece.y][piece.x+1].type == Piece.pieceType.PAWN)
						&& ((Pawn) piece).enPassantPossibilityRight) {
					moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y + 1,
							new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight),
							piece.x + 1, piece.y));
				}

				if ((piece.y == 5) && (board.attackMove(piece.x - 1, piece.y, piece.color) ) && (board.boardConf[piece.y][piece.x-1].type == Piece.pieceType.PAWN)
						&& ((Pawn) piece).enPassantPossibilityLeft) {
					moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y + 1,
							new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight),
							piece.x - 1, piece.y));
				}

				// Cazuri in care pionul avanseaza
				if (board.possibleMove(piece.x, piece.y + 1) == true) {
					Pawn pawn = new Pawn(pieceType.PAWN, piece.color, piece.x, piece.y, piece.enPassantPossibilityLeft, piece.enPassantPossibilityRight);
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