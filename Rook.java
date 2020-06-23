import java.util.ArrayList;


public class Rook extends Piece{
	int noMoves;
	public Rook(pieceType type, Game.colors color, int x, int y) {
		super(type, color, x, y);
		noMoves = 0;
	}
	
	public Rook(pieceType type, Game.colors color, int x, int y, int noMoves) {
		super(type, color, x, y);
		this.noMoves = noMoves;
	}

	public static ArrayList<Move> rookMoves(Board board, Rook piece)
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
        		if(board.attackMove(x, piece.y, piece.color) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, piece.y, 
							new Rook(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
    			
        		}
            	// deplasare
        		if(board.possibleMove(x, piece.y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, piece.y, 
							new Rook(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
    			
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
        		if(board.attackMove(x, piece.y, piece.color) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, piece.y, 
							new Rook(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
    			
        		}
            	// deplasare
        		if(board.possibleMove(x, piece.y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, x, piece.y, 
							new Rook(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
    			
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
        		if(board.attackMove(piece.x, y, piece.color) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, piece.x, y, 
							new Rook(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
    			
        		}
            	// deplasare
        		if(board.possibleMove(piece.x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, piece.x, y, 
							new Rook(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
    			
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
        		if(board.attackMove(piece.x, y, piece.color) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, piece.x, y, 
							new Rook(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));	
        		}
            	// deplasare
        		if(board.possibleMove(piece.x, y) == true && ok == 1) {
					moves.add(new Move(piece.x, piece.y, piece.x, y, 
							new Rook(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));	
        		}
        	}

        }
        return moves;
    }
	public static Rook clone (Rook piece) {
		return new Rook(piece.type, piece.color, piece.x, piece.y, piece.noMoves);
	}
}
