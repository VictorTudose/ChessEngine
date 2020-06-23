
public abstract class Piece {

	enum pieceType {
		OPENINGPAWN, PAWN, ROOK, BISHOP, KNIGHT, QUEEN, KING
	};

	pieceType type;
	Game.colors color;
	int x;
	int y;
	
	public Piece(pieceType type, Game.colors color, int x, int y) {
		this.type = type;
		this.color = color;
		this.x = x;
		this.y = y;
	}
	
	public static Piece clone(Piece piece) {
		if(piece instanceof Rook) {
			return new Rook(piece.type, piece.color, piece.x, piece.y, ((Rook)piece).noMoves);
		}
		if(piece instanceof Knight) {
			return new Knight(piece.type, piece.color, piece.x, piece.y);
		}
		if(piece instanceof Bishop) {
	    	return new Bishop(piece.type, piece.color, piece.x, piece.y);
		}
		if(piece instanceof Queen) {
			return new Queen(piece.type, piece.color, piece.x, piece.y);
		}
		if(piece instanceof King) {
			return new King(piece.type, piece.color, piece.x, piece.y, ((King)piece).noMoves);
		}
		if(piece instanceof Pawn) {
			return new Pawn(piece.type, piece.color, piece.x, piece.y, ((Pawn)piece).enPassantPossibilityLeft,
					((Pawn)piece).enPassantPossibilityRight);
		} else return null;
	}
	
	public int dist()
	{
		if(this.color==Game.colors.WHITE)
		{
			int dx=x-King.black.x;
			int dy=y-King.black.y;
			dx = dx>0? dx:-dx;
			dy = dy>0? dy:-dy;
			return dx+dy;
		}
		if(this.color==Game.colors.BLACK)
		{
			int dx=x-King.white.x;
			int dy=y-King.white.y;
			dx = dx>0? dx:-dx;
			dy = dy>0? dy:-dy;
			return dx+dy;
		}
		return 0;
	}
	
	public int opDist() {
		int opDist;
		if(this.color== Game.colors.BLACK)
		{
			this.color= Game.colors.WHITE;
			opDist=dist();
			this.color= Game.colors.BLACK;
		}
		else
		{
			this.color= Game.colors.BLACK;
			opDist=dist();
			this.color= Game.colors.WHITE;
		}
		return opDist;
	}


	public int value() {

		int dist=dist();
		int opDist=opDist();

		int pieceVal=Board.PAWN_VALUE;
		if(this instanceof Pawn)
			pieceVal=Board.PAWN_VALUE;
		if(this instanceof Bishop)
			pieceVal=Board.BISHOP_VALUE;
		if(this instanceof Knight)
			pieceVal=Board.KNIGHT_VALUE;
		if(this instanceof Queen)
			pieceVal=Board.QUEEN_VALUE;

		boolean square= (4<=x&&x<=5)&&((4<=y&&y<=5)) &&
				(
				(this instanceof Rook)||
				(this instanceof Bishop)||
				(this instanceof Queen)
				);

		return pieceVal*(-dist-opDist+ (square?1:0) );

	}
	
}