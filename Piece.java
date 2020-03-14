package bpod;
public class Piece {

    enum pieceType
    {
        OPENINGPAWN,
        PAWN,
        ROOK,
        BISHOP,
        KNIGHT,
        QUEEN,
        KING
    };

    enum colors
    {
        WHITE,
        BLACK
    }

    pieceType type;
    colors color;
    int x;
    int y;

    public Piece(pieceType type, Piece.colors color, int x, int y) {
        this.type = type;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public boolean move(int x, int y)
    {
        if(!valid(x,y))
            return false;
        this.x=x;
        this.y=y;
        return true;
    }
    public boolean valid(int x,int y)
    {
        return false;
    }
}