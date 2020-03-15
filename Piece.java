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
	int x;
	int y;
	
	public Piece(pieceType type, Piece.colors color, int x, int y) {
		this.type = type;
		this.color = color;
		this.x = x;
		this.y = y;
	}

}