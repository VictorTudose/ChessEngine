package bpod;

public class Piece {

	enum pieceType {
		OPENINGPAWN, PAWN, ROOK, BISHOP, KNIGHT, QUEEN, KING
	};

	enum colors {
		WHITE, BLACK
	}

	pieceType type;
	colors color;
	int y;
	int x;
	
	public Piece(pieceType type, Piece.colors color, int y, int x) {
		this.type = type;
		this.color = color;
		this.y = y;
		this.x = x;
	}

}