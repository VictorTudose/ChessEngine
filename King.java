import java.util.ArrayList;


public class King extends Piece{
	int noMoves;
    public King(pieceType type, Game.colors color, int x, int y) {
        super(type, color, x, y);
        noMoves = 0;
    }
    
    public King(pieceType type, Game.colors color, int x, int y, int noMoves) {
		super(type, color, x, y);
		this.noMoves = noMoves;
	}
    //TODO
	//trebuie adaugata conditia de sah !!! @ Cata
    //nu poate muta, daca intra in sah
    //mai sunt niste cazuri speciale pentru en passant de tratat in privinta sahului
    
	//sunt rocadele + toate cele 8 pozitii in care poate ajunge un rege de orice culoare

	public static boolean check(Board board,King piece)
	{

		int x=piece.x;
		int y=piece.y;

		if( (board.attackMove(x+1,y+1) && board.boardConf[x+1][y+1] instanceof Knight )
			||(board.attackMove(x+1,y-1) && board.boardConf[x+1][y-1] instanceof Knight )
			|| (board.attackMove(x-1,y+1) && board.boardConf[x-1][y+1] instanceof Knight )
			|| (board.attackMove(x-1,y-1) && board.boardConf[x-1][y-1] instanceof Knight )
			|| (board.attackMove(x-2,y+1) && board.boardConf[x-2][y+1] instanceof Knight )
			|| (board.attackMove(x-2,y-1) && board.boardConf[x-2][y-1] instanceof Knight )
			|| (board.attackMove(x+2,y+1) && board.boardConf[x+2][y+1] instanceof Knight )
			|| (board.attackMove(x+2,y-1) && board.boardConf[x+2][y-1] instanceof Knight )
		)
			return true;

		for(int xi=x;xi<=8 && (board.boardConf[xi][y]!=null && board.boardConf[xi][y].color==piece.color); xi++) {
			if (board.attackMove(xi, y) && (board.boardConf[xi][y] instanceof Rook || board.boardConf[xi][y] instanceof Queen)) {
				return true;
			}
		}

		for(int xi=x;xi>=1 && (board.boardConf[xi][y]!=null && board.boardConf[xi][y].color==piece.color); xi--) {
			if (board.attackMove(xi, y) && (board.boardConf[xi][y] instanceof Rook || board.boardConf[xi][y] instanceof Queen)) {
				return true;
			}
		}

		for(int xi=x;xi<=8 && (board.boardConf[xi][y]!=null && board.boardConf[xi][y].color==piece.color); xi++) {
			if (board.attackMove(xi, y) && (board.boardConf[xi][y] instanceof Rook || board.boardConf[xi][y] instanceof Queen)) {
				return true;
			}
		}

		for(int xi=x;xi>=1 && (board.boardConf[xi][y]!=null && board.boardConf[xi][y].color==piece.color); xi--) {
			if (board.attackMove(xi, y) && (board.boardConf[xi][y] instanceof Rook || board.boardConf[xi][y] instanceof Queen)) {
				return true;
			}
		}

		for(int yi=x;yi<=8 && (board.boardConf[x][yi]!=null && board.boardConf[x][yi].color==piece.color); yi++) {
			if (board.attackMove(x, yi) && (board.boardConf[x][yi] instanceof Rook || board.boardConf[x][yi] instanceof Queen)) {
				return true;
			}
		}

		for(int yi=x;yi>=1 && (board.boardConf[x][yi]!=null && board.boardConf[x][yi].color==piece.color); yi--) {
			if (board.attackMove(x, yi) && (board.boardConf[x][yi] instanceof Rook || board.boardConf[x][yi] instanceof Queen)) {
				return true;
			}
		}

		int xi;
		int yi;

		for(xi=x, yi=y;xi>=1 && yi>=1;xi--,yi--)
		{
			if (board.attackMove(xi, yi) && (board.boardConf[xi][yi] instanceof Bishop || board.boardConf[xi][yi] instanceof Queen)) {
				return true;
			}
		}

		for(xi=x, yi=y;xi>=1 && yi<=8;xi--,yi++)
		{
			if (board.attackMove(xi, yi) && (board.boardConf[xi][yi] instanceof Bishop || board.boardConf[xi][yi] instanceof Queen)) {
				return true;
			}
		}

		for(xi=x, yi=y;xi<=8 && yi>=1;xi++,yi--)
		{
			if (board.attackMove(xi, yi) && (board.boardConf[xi][yi] instanceof Bishop || board.boardConf[xi][yi] instanceof Queen)) {
				return true;
			}
		}

		for(xi=x, yi=y;xi<=8 && yi<=8;xi++,yi++)
		{
			if (board.attackMove(xi, yi) && (board.boardConf[xi][yi] instanceof Bishop || board.boardConf[xi][yi] instanceof Queen)) {
				return true;
			}
		}

		if((board.attackMove(x+1,y+1) && board.boardConf[x+1][y+1] instanceof Knight )
			||(board.attackMove(x+1,y-1) && board.boardConf[x+1][y-1] instanceof Knight )
			||(board.attackMove(x-1,y+1) && board.boardConf[x-1][y+1] instanceof Knight )
			||(board.attackMove(x+1,y-1) && board.boardConf[x+1][y-1] instanceof Knight )
			||(board.attackMove(x,y+1) && board.boardConf[x][y+1] instanceof Knight )
			||(board.attackMove(x,y-1) && board.boardConf[x][y-1] instanceof Knight )
			||(board.attackMove(x+1,y) && board.boardConf[x+1][y] instanceof Knight )
			||(board.attackMove(x-1,y) && board.boardConf[x-1][y] instanceof Knight )
			||(board.attackMove(x-1,y+1) && board.boardConf[x+1][y+1] instanceof Pawn )
			||(board.attackMove(x-1,y-1) && board.boardConf[x+1][y-1] instanceof Pawn  )
		)
			return true;


		return false;
	}

	public static boolean checkmate(Board board,King piece)
	{
		boolean ok=true;
		if(check(board,piece))
		{

			if(board.boardConf[piece.x][piece.y+1]==null)
			{
				board.boardConf[piece.x][piece.y+1]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.y+1;
				ok=check(board,piece);
				board.boardConf[piece.x][piece.y-1]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.y-1;
			}

			if(ok&& board.boardConf[piece.x][piece.y-1]==null)
			{
				board.boardConf[piece.x][piece.y-1]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.y-1;
				ok=check(board,piece);
				board.boardConf[piece.x][piece.y+1]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.y+1;
			}

			if(ok&& board.boardConf[piece.x+1][piece.y]==null)
			{
				board.boardConf[piece.x+1][piece.y]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.x-1;
				ok=check(board,piece);
				board.boardConf[piece.x-1][piece.y]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.x+1;
			}

			if(ok&& board.boardConf[piece.x-1][piece.y]==null)
			{
				board.boardConf[piece.x-1][piece.y]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.x-1;
				ok=check(board,piece);
				board.boardConf[piece.x+1][piece.y]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.x+1;
			}

			if(board.boardConf[piece.x+1][piece.y+1]==null)
			{
				board.boardConf[piece.x+1][piece.y+1]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.y+1;
				piece.x=piece.x+1;
				ok=check(board,piece);
				board.boardConf[piece.x-1][piece.y-1]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.y-1;
				piece.x=piece.x-1;
			}
			if(board.boardConf[piece.x+1][piece.y-1]==null)
			{
				board.boardConf[piece.x+1][piece.y-1]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.y-1;
				piece.x=piece.x+1;
				ok=check(board,piece);
				board.boardConf[piece.x-1][piece.y+1]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.y+1;
				piece.x=piece.x-1;
			}
			if(board.boardConf[piece.x-1][piece.y+1]==null)
			{
				board.boardConf[piece.x-1][piece.y+1]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.y+1;
				piece.x=piece.x-1;
				ok=check(board,piece);
				board.boardConf[piece.x+1][piece.y-1]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.y-1;
				piece.x=piece.x+1;
			}
			if(board.boardConf[piece.x-1][piece.y-1]==null)
			{
				board.boardConf[piece.x-1][piece.y-1]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.y-1;
				piece.x=piece.x-1;
				ok=check(board,piece);
				board.boardConf[piece.x+1][piece.y+1]=piece;
				board.boardConf[piece.x][piece.y]=null;
				piece.y=piece.y+1;
				piece.x=piece.x+1;
			}


		}
		return ok;
	}

	public static ArrayList<Move> kingMoves(Board board, King piece) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		if(piece != null && board.engineColor == piece.color) {	
			// rocade
			// tratam rocadele pentru alb
			if(piece.color == Game.colors.WHITE && piece.y == 1 && piece.x == 5 && piece.noMoves == 0) {
				// tratam rocada mica
				if((board.boardConf[1][8] instanceof Rook) && ((Rook)board.boardConf[1][8]).noMoves == 0
						&& board.boardConf[1][8].color == Game.colors.WHITE
						&& board.boardConf[1][6] == null && board.boardConf[1][7] == null) {
					moves.add(new Move(piece.x, piece.y, 1, 7, 1, 6,
							new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
				}
				// tratam rocada mare
				if((board.boardConf[1][1] instanceof Rook) && ((Rook)board.boardConf[1][1]).noMoves == 0
						&& board.boardConf[1][1].color == Game.colors.WHITE
						&& board.boardConf[1][2] == null && board.boardConf[1][3] == null && board.boardConf[1][4] == null) {
					moves.add(new Move(piece.x, piece.y, 1, 3, 1, 4,
							new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
				}	
			}
			
			// tratam rocadele pentru negru
			if(piece.color == Game.colors.BLACK && piece.y == 8 && piece.x == 5 && piece.noMoves == 0) {
				// tratam rocada mica
				if((board.boardConf[8][8] instanceof Rook) && ((Rook)board.boardConf[8][8]).noMoves == 0
						&& board.boardConf[8][8].color == Game.colors.BLACK
						&& board.boardConf[8][6] == null && board.boardConf[8][7] == null) {
					moves.add(new Move(piece.x, piece.y, 8, 7, 8, 6,
							new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
				}
				// tratam rocada mare
				if((board.boardConf[8][1] instanceof Rook) && ((Rook)board.boardConf[8][1]).noMoves == 0
						&& board.boardConf[8][1].color == Game.colors.BLACK
						&& board.boardConf[8][2] == null && board.boardConf[8][3] == null && board.boardConf[8][4] == null) {
					moves.add(new Move(piece.x, piece.y, 8, 3, 8, 4,
							new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
				}
			}
			
			//atacuri
			if (board.attackMove(piece.x, piece.y - 1) == true) {
				moves.add(new Move(piece.x, piece.y, piece.x, piece.y - 1, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}
			
			if (board.attackMove(piece.x, piece.y + 1) == true) {
				moves.add(new Move(piece.x, piece.y, piece.x, piece.y + 1, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}	
				
			if (board.attackMove(piece.x - 1, piece.y) == true) {
				moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}
			
			if (board.attackMove(piece.x - 1, piece.y - 1) == true) {
				moves.add(new Move(piece.x, piece.y, piece.x, piece.y - 1, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}
			
			if (board.attackMove(piece.x - 1, piece.y + 1) == true) {
				moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y + 1, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}
			
			if (board.attackMove(piece.x + 1, piece.y) == true) {
				moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}
				
			if (board.attackMove(piece.x + 1, piece.y - 1) == true) {
				moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y - 1, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}	
				
			if (board.attackMove(piece.x + 1, piece.y + 1) == true) {
				moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y + 1, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}
			
			//miscari
			if (board.possibleMove(piece.x, piece.y - 1) == true) {
				moves.add(new Move(piece.x, piece.y, piece.x, piece.y - 1, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}
			
			if (board.possibleMove(piece.x, piece.y + 1) == true) {
				moves.add(new Move(piece.x, piece.y, piece.x, piece.y + 1, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}	
				
			if (board.possibleMove(piece.x - 1, piece.y) == true) {
				moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}
			
			if (board.possibleMove(piece.x - 1, piece.y - 1) == true) {
				moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y - 1, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}
			
			if (board.possibleMove(piece.x - 1, piece.y + 1) == true) {
				moves.add(new Move(piece.x, piece.y, piece.x - 1, piece.y + 1, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}
			
			if (board.possibleMove(piece.x + 1, piece.y)) {
				moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}
				
			if (board.possibleMove(piece.x + 1, piece.y - 1)) {
				moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y - 1, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}	
				
			if (board.possibleMove(piece.x + 1, piece.y + 1) == true) {
				moves.add(new Move(piece.x, piece.y, piece.x + 1, piece.y + 1, 
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}	
		}
		// returnam posibilele mutari
		return moves;
	}
	public static King clone (King piece) {
		return new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves);
	}
}
