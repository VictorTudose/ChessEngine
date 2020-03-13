public class Board {
    public static Board boardInstance;
    Piece[][] board=new Piece[8][8];

    private Board(){}

    public static Board getBoardInstance() {
        return boardInstance;
    }

    public boolean move(String move)
    {
        int[] mv= Utils.moveToPosition(move);
        if(board[mv[0]][mv[1]].move(mv[2], mv[3]))
        {
            board[mv[2]][mv[3]]=board[mv[0]][mv[1]];
            board[mv[0]][mv[1]]=null;
        }
        return false;

    }

    public void init()
    {
        board[0][0]=new Piece(Piece.pieceType.ROOK,Piece.colors.WHITE,0,0);
    }

}
