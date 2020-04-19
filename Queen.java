import java.util.ArrayList;

public class Queen extends Piece{

	public Queen(pieceType type, Game.colors color, int x, int y) {
		super(type, color, x, y);
	}
	public static ArrayList<Move> queenMoves(Board board, Queen piece)
    {
    	ArrayList<Move> moves = new ArrayList<Move>();
        if(piece != null && board.engineColor == piece.color) {
        	// tratam mutarile pe orizontala(pe rand) in partea dreapta
        	for(int x = piece.x + 1; x <= 8; x++) {
     			int ok = 1;
    			for(int i = piece.x + 1; i < x; i++) {
    				if(board.boardConf[piece.y][i] != null) {
    					ok = 0;
    				}
    			}
        		// atac
        		if(board.attackMove(x, piece.y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, piece.y, 
							new Queen(piece.type, piece.color, piece.x, piece.y)));
        		}
            	// deplasare
        		if(board.possibleMove(x, piece.y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, piece.y, 
							new Queen(piece.type, piece.color, piece.x, piece.y)));
        		}
        	}
        	// tratam mutarile pe orizontala(pe rand) in partea stanga
        	for(int x = piece.x - 1; x >= 1; x--) {
				int ok = 1;
				for(int i = piece.x - 1; i > x; i--) {
    				if(board.boardConf[piece.y][i] != null) {
    					ok = 0;
    				}
				}
        		// atac
        		if(board.attackMove(x, piece.y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, piece.y, 
							new Queen(piece.type, piece.color, piece.x, piece.y)));
        		}
            	// deplasare
        		if(board.possibleMove(x, piece.y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, piece.y, 
							new Queen(piece.type, piece.color, piece.x, piece.y)));
        		}
        	}	
        	// tratam mutarile pe verticala(pe coloana) in sus
        	for(int y = piece.y + 1; y <= 8; y++) {
    			int ok = 1;
    			for(int j = piece.y + 1; j < y; j++) {
    				if(board.boardConf[j][piece.x] != null) {
    					ok = 0;
    				}
    			}
        		// atac
        		if(board.attackMove(piece.x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, piece.x, y, 
							new Queen(piece.type, piece.color, piece.x, piece.y)));
        		}
            	// deplasare
        		if(board.possibleMove(piece.x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, piece.x, y, 
							new Queen(piece.type, piece.color, piece.x, piece.y)));
        		}
        	}
        	// tratam mutarile pe verticala(pe coloana) in jos
        	for(int y = piece.y - 1; y >= 1; y--) {
    			int ok = 1;
    			for(int j = piece.y - 1; j > y; j--) {
    				if(board.boardConf[j][piece.x] != null) {
    					ok = 0;
    				}
    			}
        		// atac
        		if(board.attackMove(piece.x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, piece.x, y, 
							new Queen(piece.type, piece.color, piece.x, piece.y)));
        		}
            	// deplasare
        		if(board.possibleMove(piece.x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, piece.x, y, 
							new Queen(piece.type, piece.color, piece.x, piece.y)));
        		}
        	}

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
							new Queen(piece.type, piece.color, piece.x, piece.y)));
        		}
            	// deplasare
        		if(board.possibleMove(x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Queen(piece.type, piece.color, piece.x, piece.y)));
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
							new Queen(piece.type, piece.color, piece.x, piece.y)));
        		}
            	// deplasare
        		if(board.possibleMove(x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Queen(piece.type, piece.color, piece.x, piece.y)));
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
							new Queen(piece.type, piece.color, piece.x, piece.y)));
        		}
            	// deplasare
        		if(board.possibleMove(x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Queen(piece.type, piece.color, piece.x, piece.y)));
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
							new Queen(piece.type, piece.color, piece.x, piece.y)));
        		}
            	// deplasare
        		if(board.possibleMove(x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Queen(piece.type, piece.color, piece.x, piece.y)));
        		}
        	}  
        }
        return moves;
    }
	public static Queen clone(Queen piece) {
		return new Queen(piece.type, piece.color, piece.x, piece.y);
	}
}
