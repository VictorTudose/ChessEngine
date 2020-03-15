package bpod;

public class Board {
	public static Board boardInstance = null;
	Piece[][] boardConf;

	private Board() {
		boardConf = new Piece[9][9];
		initBoard();
	}

	public static Board getBoardInstance() {
		if(boardInstance == null)
			boardInstance = new Board();
		return boardInstance;
	}
	
	public void initBoard() {
		boardConf[1][1] = new Piece(Piece.pieceType.ROOK, Piece.colors.WHITE, 1, 1);
		boardConf[1][2] = new Piece(Piece.pieceType.KNIGHT, Piece.colors.WHITE, 1, 2);
		boardConf[1][3] = new Piece(Piece.pieceType.BISHOP, Piece.colors.WHITE, 1, 3);
		boardConf[1][4] = new Piece(Piece.pieceType.QUEEN, Piece.colors.WHITE, 1, 4);
		boardConf[1][5] = new Piece(Piece.pieceType.KING, Piece.colors.WHITE, 1, 5);
		boardConf[1][6] = new Piece(Piece.pieceType.BISHOP, Piece.colors.WHITE, 1, 6);
		boardConf[1][7] = new Piece(Piece.pieceType.KNIGHT, Piece.colors.WHITE, 1, 7);
		boardConf[1][8] = new Piece(Piece.pieceType.ROOK, Piece.colors.WHITE, 1, 8);
		for(int j = 1; j <= 8; j++)
			boardConf[2][j] = new Piece(Piece.pieceType.OPENINGPAWN, Piece.colors.WHITE, 2, j);
		for(int j = 1; j <= 8; j++)
			boardConf[7][j] = new Piece(Piece.pieceType.OPENINGPAWN, Piece.colors.BLACK, 7, j);
		boardConf[8][1] = new Piece(Piece.pieceType.ROOK, Piece.colors.BLACK, 8, 1);
		boardConf[8][2] = new Piece(Piece.pieceType.KNIGHT, Piece.colors.BLACK, 8, 2);
		boardConf[8][3] = new Piece(Piece.pieceType.BISHOP, Piece.colors.BLACK, 8, 3);
		boardConf[8][4] = new Piece(Piece.pieceType.QUEEN, Piece.colors.BLACK, 8, 4);
		boardConf[8][5] = new Piece(Piece.pieceType.KING, Piece.colors.BLACK, 8, 5);
		boardConf[8][6] = new Piece(Piece.pieceType.BISHOP, Piece.colors.BLACK, 8, 6);
		boardConf[8][7] = new Piece(Piece.pieceType.KNIGHT, Piece.colors.BLACK, 8, 7);
		boardConf[8][8] = new Piece(Piece.pieceType.ROOK, Piece.colors.BLACK, 8, 8);
	}

	public void opponentMove(String move) {
		int[] mv = Utils.moveToPositions(move);
		if (boardConf[mv[2]][mv[3]] == null) {
			boardConf[mv[2]][mv[3]] = boardConf[mv[0]][mv[1]];
			boardConf[mv[0]][mv[1]] = null;
			boardConf[mv[2]][mv[3]].y = mv[2];
			boardConf[mv[2]][mv[3]].x = mv[3];
		}
	}

	public void makeMove(int oldY, int oldX, int newY, int newX) {
		boardConf[newY][newX] = boardConf[oldY][oldX];
		boardConf[oldY][oldX] = null;
		boardConf[newY][newX].y = newY;
		boardConf[newY][newX].x = newX;
	}
	
	public boolean possibleMove(int y, int x) {
		if((y >= 1 && y <= 8) && (x >= 1 && x <= 8) && boardConf[y][x] == null)
			return true;
		else
			return false;
	}
	
	public boolean attackMove(int y, int x) {
		if((y >= 1 && y <= 8) && (x >= 1 && x <= 8) && boardConf[y][x] != null)
			return true;
		else
			return false;
	}	
	
}