package bpod;

public class Bishop extends Piece {
    public Bishop(pieceType type, Game.colors color, int x, int y) {
        super(type, color, x, y);
    }

    public static boolean bishopMove(Board board,Bishop piece)
    {
        if(piece==null)
            return false;
        return board.attackMove(piece.x-1,piece.y-1)
                ||board.attackMove(piece.x+1,piece.y-1)
                ||board.attackMove(piece.x-1,piece.y+1)
                ||board.attackMove(piece.x+1,piece.y+1);
    }
}
