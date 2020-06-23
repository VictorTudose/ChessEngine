import java.util.ArrayList;


public class Knight extends Piece {

	public Knight(pieceType type, Game.colors color, int x, int y) {
		super(type, color, x, y);
	}
	
	
	//sunt toate cele 8 pozitii in care poate ajunge un cal de orice culoare	
	//dar ordonate firesc pentru cum miscam calul de pe negru/alb
	public static ArrayList<Move> knightMoves(Board board, Knight piece) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		if(piece != null && board.engineColor == piece.color) {	
			
			if (board.attackMove(piece.x + 2, piece.y - 1, piece.color) ) {
				moves.add(new Move(piece.x, piece.y, piece.x + 2, piece.y - 1,
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}
			
			if (board.attackMove(piece.x + 1, piece.y - 2, piece.color) ) {
				moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y - 2, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}	
				
			if (board.attackMove(piece.x - 1, piece.y - 2, piece.color) ) {
				moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y - 2, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}
			
			if (board.attackMove(piece.x - 2, piece.y - 1, piece.color) ) {
				moves.add(new Move(piece.x, piece.y, piece.x - 2, piece.y - 1, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}
			
			if (board.attackMove(piece.x - 2, piece.y + 1, piece.color) ) {
				moves.add(new Move(piece.x, piece.y, piece.x - 2, piece.y + 1, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}
			
			if (board.attackMove(piece.x - 1, piece.y + 2, piece.color) ) {
				moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y + 2, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}
				
			if (board.attackMove(piece.x + 1, piece.y + 2, piece.color) ) {
				moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y + 2, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}	
				
			if (board.attackMove(piece.x + 2, piece.y + 1, piece.color) ) {
				moves.add(new Move(piece.x, piece.y, piece.x + 2, piece.y + 1, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}
			
			//miscari
			if (board.possibleMove(piece.x + 2, piece.y - 1) ) {
				moves.add(new Move(piece.x, piece.y, piece.x + 2, piece.y - 1, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}
			
			if (board.possibleMove(piece.x + 1, piece.y - 2) ) {
				moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y - 2, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}	
				
			if (board.possibleMove(piece.x - 1, piece.y - 2) ) {
				moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y - 2, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}
			
			if (board.possibleMove(piece.x - 2, piece.y - 1) ) {
				moves.add(new Move(piece.x, piece.y, piece.x - 2, piece.y - 1, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}
			
			if (board.possibleMove(piece.x - 2, piece.y + 1) ) {
				moves.add(new Move(piece.x, piece.y, piece.x - 2, piece.y + 1, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}
			
			if (board.possibleMove(piece.x - 1, piece.y + 2) ) {
				moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y + 2, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}
				
			if (board.possibleMove(piece.x + 1, piece.y + 2) ) {
				moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y + 2, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}	
				
			if (board.possibleMove(piece.x + 2, piece.y + 1) ) {
				moves.add(new Move(piece.x, piece.y, piece.x + 2, piece.y + 1, 
						new Knight(piece.type, piece.color, piece.x, piece.y)));
			}
				
		}
		// returnam posibilele mutari
		return moves;
	}
	public static Knight clone(Knight piece) {
		return new Knight(piece.type, piece.color, piece.x, piece.y);
	}
}