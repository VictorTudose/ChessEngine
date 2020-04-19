
public class Utils {

	public static long fiveMinutes = 30000;

	public static String positionsToMove(int oldX, int oldY, int newX, int newY) {
		StringBuilder move = new StringBuilder("move ");
		move.append(Character.toString(((char) oldX) + 'a' - 1)).append(Character.toString(((char) oldY) + '0'))
				.append(Character.toString(((char) newX) + 'a' - 1)).append(Character.toString(((char) newY) + '0'));
		return move.toString();
	}

	public static int[] moveToPositions(String move) {
		int[] retee = new int[4];
		retee[0] = move.charAt(0) - 'a' + 1;
		retee[1] = move.charAt(1) - '0';
		retee[2] = move.charAt(2) - 'a' + 1;
		retee[3] = move.charAt(3) - '0';
		return retee;
	}
	
	public static Game.colors oppColors(Game.colors player){
		if(player == Game.colors.BLACK) {
			return Game.colors.WHITE;
		} else {
			return Game.colors.BLACK;
		}
	}
	
	public static void showTypeAndColor(Piece.pieceType pt, Game.colors color) {
		if(pt == Piece.pieceType.BISHOP)
			System.out.println("BISHOP");
		if(pt == Piece.pieceType.PAWN)
			System.out.println("PAWN");
		if(pt == Piece.pieceType.KNIGHT)
			System.out.println("KNIGHT");
		if(pt == Piece.pieceType.KING)
			System.out.println("KING");
		if(pt == Piece.pieceType.QUEEN)
			System.out.println("QUEEN");
		if(pt == Piece.pieceType.OPENINGPAWN)
			System.out.println("OPENINGPAWN");
		if(pt == Piece.pieceType.ROOK)
			System.out.println("ROOK");
		if(color == Game.colors.BLACK)
			System.out.println("BLACK");
		if(color == Game.colors.WHITE)
			System.out.println("WHITE");		
	}
	
}