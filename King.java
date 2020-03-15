package bpod;

public class King extends Piece{
    public King(pieceType type, Game.colors color, int x, int y) {
        super(type, color, x, y);
    }

    public static boolean kingMove(Board board,King piece)
    {
        if(piece==null)
            return false;
        return board.attackMove(piece.x-1,piece.y-1)
                ||board.attackMove(piece.x-1,piece.y)
                ||board.attackMove(piece.x-1,piece.y+1)
                ||board.attackMove(piece.x+1,piece.y-1)
                ||board.attackMove(piece.x+1,piece.y)
                ||board.attackMove(piece.x+1,piece.y+1)
                ||board.attackMove(piece.x,piece.y-1)
                ||board.attackMove(piece.x,piece.y)
                ||board.attackMove(piece.x,piece.y+1);
    }
}
