import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(pieceType type, Game.colors color, int x, int y){
        super(type, color, x, y);
    }

    public static ArrayList<Move> bishopMoves(Board board,Bishop piece)
    {
        
        ArrayList<Move> moves = new ArrayList<Move>();
    	
        if(piece==null || board==null )
            return moves;

        if(board.engineColor == piece.color) {
        	// tratam partea de jos a diagonalei stanga->dreapta
        	for(int x = piece.x + 1, y = piece.y - 1; x <= 8 && y >= 1; x++, y--) {
        		boolean ok = true;

        		for(int i = piece.x + 1, j = piece.y - 1; i < x && j > y  && ok ; i++, j--) {
        			if(board.boardConf[j][i] != null) {
        				ok = false;
        			}
        		}
                
        		if((board.attackMove(x, y, piece.color) || board.possibleMove(x, y) ) && ok) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Bishop(piece.type, piece.color, piece.x, piece.y)));
        		}
        	}
        	// tratam partea de sus a diagonalei stanga->dreapta
        	for(int x = piece.x - 1, y = piece.y + 1; x >= 1 && y <= 8; x--, y++) {
            	boolean ok = true;
            	for(int i = piece.x - 1, j = piece.y + 1; i > x && j < y && ok; i--, j++) {
        			if(board.boardConf[j][i] != null) {
        				ok = false;
        			}
            	}
        		// atac
        		if( (board.attackMove(x, y, piece.color) || board.possibleMove(x, y) ) && ok ) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Bishop(piece.type, piece.color, piece.x, piece.y)));
        		}
        	}  		
        	
        	// tratam partea de jos a diagonalei dreapta->stanga
        	for(int x = piece.x - 1, y = piece.y - 1; x >= 1 && y >= 1; x--, y--) {
            	boolean ok = true;
            	for(int i = piece.x - 1, j = piece.y - 1; i > x && j > x && ok; i--, j--) {
        			if(board.boardConf[j][i] != null) {
        				ok = false;
        			}
            	}
        		if(( board.attackMove(x, y, piece.color) || board.possibleMove(x, y) ) && ok ) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Bishop(piece.type, piece.color, piece.x, piece.y)));
        		}
        	}
        	
            for(int x = piece.x + 1, y = piece.y + 1; x <= 8 && y <= 8; x++, y++) {
            	boolean ok = true;
            	
                for(int i = piece.x + 1, j = piece.y + 1; i < x && j < y && ok; i++, j++) {
        			if(board.boardConf[j][i] != null) {
        				ok = false;
        			}
            	}
        		
                if( (board.attackMove(x, y, piece.color) || board.possibleMove(x, y) ) && ok ) {
					moves.add(new Move(piece.x, piece.y, x, y, 
							new Bishop(piece.type, piece.color, piece.x, piece.y)));
        		}
        	}        	

        }
        return moves;
    }
    public static Bishop clone(Bishop piece) {
    	return new Bishop(piece.type, piece.color, piece.x, piece.y);
    }
}
