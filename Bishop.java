import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(pieceType type, Game.colors color, int x, int y) {
        super(type, color, x, y);
    }

    public static ArrayList<Move> bishopMoves(Board board,Bishop piece)
    {
    	ArrayList<Move> moves = new ArrayList<Move>();
        if(piece != null && board.engineColor == piece.color) {
        	// tratam partea de jos a diagonalei stanga->dreapta
        	for(int x = piece.x + 1, y = piece.y - 1; x <= 8 && y >= 1; x++, y--) {
        		int ok = 1;
        		for(int i = piece.x + 1, j = piece.y - 1; i < x && j > y; i++, j--) {
        			if(board.boardConf[j][i] != null) {
        				ok = 0;
        			}
        		}
        		// atac
        		if(board.attackMove(x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Bishop(piece.type, piece.color, piece.x, piece.y)));
        		}
            	// deplasare
        		if(board.possibleMove(x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Bishop(piece.type, piece.color, piece.x, piece.y)));
        		}
        	}
        	// tratam partea de sus a diagonalei stanga->dreapta
        	for(int x = piece.x - 1, y = piece.y + 1; x >= 1 && y <= 8; x--, y++) {
            	int ok = 1;
            	for(int i = piece.x - 1, j = piece.y + 1; i > x && j < y; i--, j++) {
        			if(board.boardConf[j][i] != null) {
        				ok = 0;
        			}
            	}
        		// atac
        		if(board.attackMove(x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Bishop(piece.type, piece.color, piece.x, piece.y)));
        		}
            	// deplasare
        		if(board.possibleMove(x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Bishop(piece.type, piece.color, piece.x, piece.y)));
        		}
        	}        		
        	
        	// tratam partea de jos a diagonalei dreapta->stanga
        	for(int x = piece.x - 1, y = piece.y - 1; x >= 1 && y >= 1; x--, y--) {
            	int ok = 1;
            	for(int i = piece.x - 1, j = piece.y - 1; i > x && j > x; i--, j--) {
            		ok = 0;
            	}
        		// atac
        		if(board.attackMove(x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Bishop(piece.type, piece.color, piece.x, piece.y)));
        		}
            	// deplasare
        		if(board.possibleMove(x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Bishop(piece.type, piece.color, piece.x, piece.y)));
        		}
        	}
        	// tratam partea de sus a diagonalei dreapta->stanga
        	for(int x = piece.x + 1, y = piece.y + 1; x <= 8 && y <= 8; x++, y++) {
            	int ok = 1;
            	for(int i = piece.x + 1, j = piece.y + 1; i < x && j < y; i++, j++) {
        			if(board.boardConf[j][i] != null) {
        				ok = 0;
        			}
            	}
        		// atac
        		if(board.attackMove(x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Bishop(piece.type, piece.color, piece.x, piece.y)));
        		}
            	// deplasare
        		if(board.possibleMove(x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Bishop(piece.type, piece.color, piece.x, piece.y)));
        		}
        	}        	

        }
        return moves;
    }
    public static Bishop clone(Bishop piece) {
    	Bishop bishop = new Bishop(piece.type, piece.color, piece.x, piece.y);
    	return bishop;
    }
}
