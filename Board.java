package bpod;

import bpod.Game.colors;

public class Board {
	public static Board boardInstance = null;
	Piece[][] boardConf;
	colors engineColor;

	private Board(colors engineColor) {
		this.engineColor = engineColor;
		boardConf = new Piece[9][9];
		initBoard();
	}

	public static Board getBoardInstance(colors engineColor) {
		if (boardInstance == null) {
			boardInstance = new Board(engineColor);
		}
		return boardInstance;
	}

	public void initBoard() {
		boardConf[1][1] = new Piece(Piece.pieceType.ROOK, colors.WHITE, 1, 1);
		boardConf[1][2] = new Piece(Piece.pieceType.KNIGHT, colors.WHITE, 2, 1);
		boardConf[1][3] = new Piece(Piece.pieceType.BISHOP, colors.WHITE, 3, 1);
		boardConf[1][4] = new Piece(Piece.pieceType.QUEEN, colors.WHITE, 4, 1);
		boardConf[1][5] = new Piece(Piece.pieceType.KING, colors.WHITE, 5, 1);
		boardConf[1][6] = new Piece(Piece.pieceType.BISHOP, colors.WHITE, 6, 1);
		boardConf[1][7] = new Piece(Piece.pieceType.KNIGHT, colors.WHITE, 7, 1);
		boardConf[1][8] = new Piece(Piece.pieceType.ROOK, colors.WHITE, 8, 1);
		for (int j = 1; j <= 8; j++) {
			boardConf[2][j] = new Piece(Piece.pieceType.OPENINGPAWN, colors.WHITE, j, 2);
			boardConf[7][j] = new Piece(Piece.pieceType.OPENINGPAWN, colors.BLACK, j, 7);
		}
		boardConf[8][1] = new Piece(Piece.pieceType.ROOK, colors.BLACK, 1, 8);
		boardConf[8][2] = new Piece(Piece.pieceType.KNIGHT, colors.BLACK, 2, 8);
		boardConf[8][3] = new Piece(Piece.pieceType.BISHOP, colors.BLACK, 3, 8);
		boardConf[8][4] = new Piece(Piece.pieceType.QUEEN, colors.BLACK, 4, 8);
		boardConf[8][5] = new Piece(Piece.pieceType.KING, colors.BLACK, 5, 8);
		boardConf[8][6] = new Piece(Piece.pieceType.BISHOP, colors.BLACK, 6, 8);
		boardConf[8][7] = new Piece(Piece.pieceType.KNIGHT, colors.BLACK, 7, 8);
		boardConf[8][8] = new Piece(Piece.pieceType.ROOK, colors.BLACK, 8, 8);
	}

	public void opponentMove(String move) {
		int[] mv = Utils.moveToPositions(move);
		if (boardConf[mv[3]][mv[2]] == null) {
			boardConf[mv[3]][mv[2]] = boardConf[mv[1]][mv[0]];
			boardConf[mv[1]][mv[0]] = null;
			boardConf[mv[3]][mv[2]].y = mv[3];
			boardConf[mv[3]][mv[2]].x = mv[2];
		}
	}

	public void makeMove(int oldX, int oldY, int newX, int newY) {
		boardConf[newY][newX] = boardConf[oldY][oldX];
		boardConf[oldY][oldX] = null;
		boardConf[newY][newX].y = newY;
		boardConf[newY][newX].x = newX;
		System.out.println(Utils.positionsToMove(oldX, oldY, newX, newY));
	}

	public boolean possibleMove(int x, int y) {
		if ((y >= 1 && y <= 8) && (x >= 1 && x <= 8) && (boardConf[y][x] == null))
			return true;
		else
			return false;
	}

	public boolean attackMove(int x, int y) {
		if ((y >= 1 && y <= 8) && (x >= 1 && x <= 8) && (boardConf[y][x] != null)
				&& (boardConf[y][x].color != engineColor))
			return true;
		else
			return false;
	}

}