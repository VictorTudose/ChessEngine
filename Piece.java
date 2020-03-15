package bpod;

import bpod.Game.colors;

public class Piece {

	enum pieceType {
		OPENINGPAWN, PAWN, ROOK, BISHOP, KNIGHT, QUEEN, KING
	};

	

	pieceType type;
	colors color;
	int x;
	int y;
	
	public Piece(pieceType type, colors color, int x, int y) {
		this.type = type;
		this.color = color;
		this.x = x;
		this.y = y;
	}

}