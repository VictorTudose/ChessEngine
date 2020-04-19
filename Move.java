
public class Move {
	int oldX;
	int oldY;
	int newX;
	int newY;
	Piece piece;
	int enPassantX;
	int enPassantY;
	int castlingRookX;
	int castlingRookY;
	// mutare normala
	public Move(int oldX, int oldY, int newX, int newY, Piece piece) {
		super();
		this.oldX = oldX;
		this.oldY = oldY;
		this.newX = newX;
		this.newY = newY;
		this.piece = piece;
		this.enPassantX = 0;
		this.enPassantY = 0;
		this.castlingRookX = 0;
		this.castlingRookY = 0;
	}
	
	// mutare care implica en passant
	public Move(int oldX, int oldY, int newX, int newY, Piece piece, int enPassantX, int enPassantY) {
		super();
		this.oldX = oldX;
		this.oldY = oldY;
		this.newX = newX;
		this.newY = newY;
		this.piece = piece;
		this.enPassantX = enPassantX;
		this.enPassantY = enPassantY;
		this.castlingRookX = 0;
		this.castlingRookY = 0;
	}
	
	// am schimbat ordinea parametrilor ca sa nu se incurce cu mutarea care implica en passant
	public Move(int oldX, int oldY, int newX, int newY, int castlingRookX, int castlingRookY, Piece piece) {
		super();
		this.oldX = oldX;
		this.oldY = oldY;
		this.newX = newX;
		this.newY = newY;
		this.enPassantX = 0;
		this.enPassantY = 0;
		this.castlingRookX = castlingRookX;
		this.castlingRookY = castlingRookY;
		this.piece = piece;
	}
	
	public Move cloneMove() {
		Piece clone = null;
		if(this.piece != null) {
			if(this.piece instanceof Pawn) {
				clone = Pawn.clone((Pawn)this.piece);
			} else
			if(this.piece instanceof Rook) {
				clone = Rook.clone((Rook)this.piece);
			} else
			if(this.piece instanceof Knight) {
				clone = Knight.clone((Knight)this.piece);
			} else
			if(this.piece instanceof Bishop) {
				clone = Bishop.clone((Bishop)this.piece);
			} else
			if(this.piece instanceof Queen) {
				clone = Queen.clone((Queen)this.piece);
			} else
			if(this.piece instanceof King) {
				clone = King.clone((King)this.piece);
			}
		}
		Move move = new Move(this.oldX, this.oldY, this.newX, this.newY, clone);
		move.enPassantX = this.enPassantX;
		move.enPassantY = this.enPassantY;
		move.castlingRookX = this.castlingRookX;
		move.castlingRookY = this.castlingRookY;
		return move;
	}
}
