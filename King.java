import java.util.ArrayList;

public class King extends Piece {
	int noMoves;

	public static King black;
	public static King white;

	public King(pieceType type, Game.colors color, int x, int y) {
		super(type, color, x, y);
		noMoves = 0;
	}

	public King(pieceType type, Game.colors color, int x, int y, int noMoves) {
		super(type, color, x, y);
		this.noMoves = noMoves;
	}

	public static boolean check(Board board, King piece) {
//		System.out.println("Culoare rege "+piece.color);
		int x = piece.x;
		int y = piece.y;

		if ((board.attackMove(x + 1, y + 2, piece.color) && board.boardConf[y + 2][x + 1] instanceof Knight)
				|| (board.attackMove(x + 1, y - 2, piece.color) && board.boardConf[y - 2][x + 1] instanceof Knight)
				|| (board.attackMove(x - 1, y + 2, piece.color) && board.boardConf[y + 2][x - 1] instanceof Knight)
				|| (board.attackMove(x - 1, y - 2, piece.color) && board.boardConf[y - 2][x - 1] instanceof Knight)
				|| (board.attackMove(x - 2, y + 1, piece.color) && board.boardConf[y + 1][x - 2] instanceof Knight)
				|| (board.attackMove(x - 2, y - 1, piece.color) && board.boardConf[y - 1][x - 2] instanceof Knight)
				|| (board.attackMove(x + 2, y + 1, piece.color) && board.boardConf[y + 1][x + 2] instanceof Knight)
				|| (board.attackMove(x + 2, y - 1, piece.color) && board.boardConf[y - 1][x + 2] instanceof Knight)) {
			return true;
		}

		for (int xi = x+1; xi <= 8; xi++) {
			if(board.friendlyPiece(xi, y, piece.color)) {
				break;
			} else 
		
			if (board.attackMove(xi, y, piece.color) && (board.boardConf[y][xi] instanceof Rook || board.boardConf[y][xi] instanceof Queen)) {
				return true;
			}
		}

		for (int xi = x-1; xi >= 1; xi--) {
			if(board.friendlyPiece(xi, y, piece.color)) {
				break;
			} else 
		
			if (board.attackMove(xi, y, piece.color) && (board.boardConf[y][xi] instanceof Rook || board.boardConf[y][xi] instanceof Queen)) {
				return true;
			}
		}
		

		for (int yi = y+1; yi <= 8 && (board.boardConf[yi][x] == null
				|| (board.boardConf[yi][x] != null && board.attackMove(x, yi, piece.color))); yi++) {
			if ((board.boardConf[yi][x] instanceof Rook || board.boardConf[yi][x] instanceof Queen)) {
				return true;
			}
		}

		for (int yi = y-1; yi >= 1 && (board.boardConf[yi][x] == null
				|| (board.boardConf[yi][x] != null && (board.attackMove(x, yi, piece.color)))); yi--) {
			if (board.boardConf[yi][x] instanceof Rook || board.boardConf[yi][x] instanceof Queen) {
				return true;
			}
		}

		int xi;
		int yi;

		for (xi = x-1, yi = y-1; xi >= 1 && yi >= 1 && (board.boardConf[yi][xi] == null
				|| (board.boardConf[yi][xi] != null && board.attackMove(xi, yi, piece.color))); xi--, yi--) {
			if (board.boardConf[yi][xi] instanceof Bishop || board.boardConf[yi][xi] instanceof Queen) {
				return true;
			}
		}

		for (xi = x-1, yi = y+1; xi >= 1 && yi <= 8 && (board.boardConf[yi][xi] == null
				|| (board.boardConf[yi][xi] != null && board.attackMove(xi, yi, piece.color))); xi--, yi++) {
			if (board.boardConf[yi][xi] instanceof Bishop || board.boardConf[yi][xi] instanceof Queen) {
				return true;
			}
		}

		for (xi = x+1, yi = y-1; xi <= 8 && yi >= 1 && (board.boardConf[yi][xi] == null
				|| (board.boardConf[yi][xi] != null && (board.attackMove(xi, yi, piece.color)))); xi++, yi--) {
			if (board.boardConf[yi][xi] instanceof Bishop || board.boardConf[yi][xi] instanceof Queen) {
				return true;
			}
		}

		for (xi = x+1, yi = y+1; xi <= 8 && yi <= 8 && (board.boardConf[yi][xi] == null
				|| (board.boardConf[yi][xi] != null && board.attackMove(xi, yi, piece.color))); xi++, yi++) {
			if (board.boardConf[yi][xi] instanceof Bishop || board.boardConf[yi][xi] instanceof Queen) {
				return true;
			}
		}

		if (piece.color == Game.colors.BLACK) {
			if (((board.attackMove(x - 1, y - 1, piece.color) && board.boardConf[y - 1][x - 1] instanceof Pawn)
					|| (board.attackMove(x + 1, y - 1, piece.color) && board.boardConf[y - 1][x + 1] instanceof Pawn))) {
				return true;
			}
		}

		if (piece.color == Game.colors.WHITE) {
			if (((board.attackMove(x - 1, y + 1, piece.color) && board.boardConf[y + 1][x - 1] instanceof Pawn)
					|| (board.attackMove(x + 1, y + 1, piece.color) && board.boardConf[y + 1][x + 1] instanceof Pawn))) {
				return true;
			}
		}

		return false;
	}

	public static boolean wrap_check(Board board, King piece, int dx, int dy) {
		boolean ok;

		if (piece.x + dx < 1 || piece.x + dx > 8 || piece.y + dy < 1 || piece.y + dy > 8)
			return false;

		board.boardConf[piece.y + dy][piece.x + dx] = piece;
		board.boardConf[piece.y][piece.x] = null;
		piece.x = piece.x + dx;
		piece.y = piece.y + dy;
		ok = check(board, piece);
		board.boardConf[piece.y - dy][piece.x - dx] = piece;
		board.boardConf[piece.y][piece.x] = null;
		piece.x = piece.x - dx;
		piece.y = piece.y - dy;

		return ok;
	}

	public static boolean checkmate(Board board, King piece) {
		boolean ok = true;
		if (check(board, piece)) {

			if (ok && board.possibleMove(piece.x, piece.y+1)) {
				ok = wrap_check(board, piece, 0, 1);
			}

			if (ok && board.possibleMove(piece.x, piece.y-1)) {
				ok = wrap_check(board, piece, 0, -1);
			}

			if (ok && board.possibleMove(piece.x + 1, piece.y)) {
				ok = wrap_check(board, piece, 1, 0);
			}

			if (ok && board.possibleMove(piece.x - 1,piece.y)){
				ok = wrap_check(board, piece, -1, 0);
			}

			if (ok && board.possibleMove(piece.x + 1, piece.y + 1)) {
				ok = wrap_check(board, piece, 1, 1);
			}

			if (ok && board.possibleMove(piece.x + 1, piece.y - 1)) {
				ok = wrap_check(board, piece, 1, -1);
			}

			if (ok && board.possibleMove(piece.x - 1, piece.y + 1)) {
				ok = wrap_check(board, piece, -1, 1);
			}

			if (ok && board.possibleMove(piece.x - 1, piece.y - 1)) {
				ok = wrap_check(board, piece, -1, -1);
			}
		}

		return ok;
	}

	public static ArrayList<Move> kingMoves(Board board, King piece) {
		ArrayList<Move> moves = new ArrayList<Move>();

		boolean check=false;
		if (check(board, piece))
			check=true;

		if (piece != null && board.engineColor == piece.color) {
			// rocade
			// tratam rocadele pentru alb
			if (piece.color == Game.colors.WHITE && piece.y == 1 && piece.x == 5 && piece.noMoves == 0) {
				// tratam rocada mica
				if ((board.boardConf[1][8] instanceof Rook) && ((Rook) board.boardConf[1][8]).noMoves == 0
						&& board.boardConf[1][8].color == Game.colors.WHITE && board.boardConf[1][6] == null
						&& board.boardConf[1][7] == null) {

					if (wrap_check(board, piece, 1, 0) && wrap_check(board, piece, 2, 0))
						moves.add(new Move(piece.x, piece.y, 1, 7, 1, 6,
								new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
				}
				// tratam rocada mare
				if ((board.boardConf[1][1] instanceof Rook) && ((Rook) board.boardConf[1][1]).noMoves == 0
						&& board.boardConf[1][1].color == Game.colors.WHITE && board.boardConf[1][2] == null
						&& board.boardConf[1][3] == null && board.boardConf[1][4] == null) {
					if (wrap_check(board, piece, -1, 0) && wrap_check(board, piece, -2, 0))
						moves.add(new Move(piece.x, piece.y, 1, 3, 1, 4,
								new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
				}
			}

			// tratam rocadele pentru negru
			if (piece.color == Game.colors.BLACK && piece.y == 8 && piece.x == 5 && piece.noMoves == 0) {
				// tratam rocada mica
				if ((board.boardConf[8][8] instanceof Rook) && ((Rook) board.boardConf[8][8]).noMoves == 0
						&& board.boardConf[8][8].color == Game.colors.BLACK && board.boardConf[8][6] == null
						&& board.boardConf[8][7] == null) {
					if (wrap_check(board, piece, 1, 0) && wrap_check(board, piece, 2, 0))
						moves.add(new Move(piece.x, piece.y, 8, 7, 8, 6,
								new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
				}
				// tratam rocada mare
				if ((board.boardConf[8][1] instanceof Rook) && ((Rook) board.boardConf[8][1]).noMoves == 0
						&& board.boardConf[8][1].color == Game.colors.BLACK && board.boardConf[8][2] == null
						&& board.boardConf[8][3] == null && board.boardConf[8][4] == null) {
					if (wrap_check(board, piece, -1, 0) && wrap_check(board, piece, -2, 0))
						moves.add(new Move(piece.x, piece.y, 8, 3, 8, 4,
								new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
				}
			}

			// miscari

			// TODO verify

			if (board.possibleMove(piece.x, piece.y - 1) || board.attackMove(piece.x, piece.y-1,piece.color)) {
				if(!check || !wrap_check(board,piece,0,-1) )
				moves.add(new Move(piece.x, piece.y, piece.x, piece.y-1,
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}

			if (board.possibleMove(piece.x, piece.y + 1) || board.attackMove(piece.x, piece.y + 1,piece.color)) {
				if(!check || !wrap_check(board,piece,0,1) )
				moves.add(new Move(piece.x, piece.y, piece.x, piece.y + 1,
						new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}

			if (board.possibleMove(piece.x+1, piece.y) || board.attackMove(piece.x+1, piece.y,piece.color)) {
				if(!check || !wrap_check(board,piece,1,0) )
					moves.add(new Move(piece.x, piece.y, piece.x+1, piece.y ,
							new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}

			if (board.possibleMove(piece.x-1, piece.y) || board.attackMove(piece.x-1, piece.y,piece.color)) {
				if(!check || !wrap_check(board,piece,-1,0) )
					moves.add(new Move(piece.x, piece.y, piece.x-1, piece.y ,
							new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}

			if (board.possibleMove(piece.x+1, piece.y - 1) || board.attackMove(piece.x+1, piece.y-1,piece.color)) {
				if(!check || !wrap_check(board,piece,1,-1) )
					moves.add(new Move(piece.x, piece.y, piece.x+1, piece.y-1,
							new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}

			if (board.possibleMove(piece.x-1, piece.y + 1) || board.attackMove(piece.x-1, piece.y + 1,piece.color)) {
				if(!check || !wrap_check(board,piece,-1,1) )
					moves.add(new Move(piece.x, piece.y, piece.x-1, piece.y + 1,
							new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}

			if (board.possibleMove(piece.x+1, piece.y-1) || board.attackMove(piece.x+1, piece.y-1,piece.color)) {
				if(!check || !wrap_check(board,piece,1,-1) )
					moves.add(new Move(piece.x, piece.y, piece.x+1, piece.y-1 ,
							new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}

			if (board.possibleMove(piece.x-1, piece.y-1) || board.attackMove(piece.x-1, piece.y-1,piece.color)) {
				if(!check || !wrap_check(board,piece,-1,-1) )
					moves.add(new Move(piece.x, piece.y, piece.x-1, piece.y-1 ,
							new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves)));
			}

		}
		// returnam posibilele mutari
		return moves;
	}

	public static King clone(King piece) {
		return new King(piece.type, piece.color, piece.x, piece.y, piece.noMoves);
	}

	public int num_of_defences(Board board) {
		int x = this.x;
		int y = this.y;

		int res = 0;

		for (int xi = x; xi <= 8; xi++) {
			if (board.boardConf[y][xi] != null && board.boardConf[y][xi].color == this.color) {
				res++;
				break;
			}
		}
		for (int xi = x; xi >= 1; xi--) {
			if (board.boardConf[y][xi] != null && board.boardConf[y][xi].color == this.color) {
				res++;
				break;
			}
		}
		for (int yi = y; yi <= 8; yi++) {
			if (board.boardConf[yi][x] != null && board.boardConf[yi][x].color == this.color) {
				res++;
				break;
			}
		}
		for (int yi = y; yi >= 1; yi--) {
			if (board.boardConf[yi][x] != null && board.boardConf[yi][x].color == this.color) {
				res++;
				break;
			}
		}

		int xi;
		int yi;

		for (xi = x, yi = y; xi <= 8 && yi <= 8; xi++, yi++) {
			if (board.boardConf[yi][xi] != null && board.boardConf[yi][xi].color == this.color) {
				res++;
				break;
			}
		}
		for (xi = x, yi = y; xi <= 8 && yi >= 1; xi++, yi--) {
			if (board.boardConf[yi][xi] != null && board.boardConf[yi][xi].color == this.color) {
				res++;
				break;
			}
		}
		for (xi = x, yi = y; xi >= 1 && yi <= 8; xi--, yi++) {
			if (board.boardConf[yi][xi] != null && board.boardConf[yi][xi].color == this.color) {
				res++;
				break;
			}
		}
		for (xi = x, yi = y; xi >= 1 && yi >= 1; xi--, yi--) {
			if (board.boardConf[yi][xi] != null && board.boardConf[yi][xi].color == this.color) {
				res++;
				break;
			}
		}

		for (int dx = -2; dx <= 2; dx++) {
			x += dx;
			if (!(1 <= x && x <= 8)) {
				x -= dx;
				continue;
			}
			if (dx == -2 || dx == 2) {
				y += 1;
				if (1 <= y && y <= 8) {
					if (board.boardConf[y][x] != null && board.boardConf[y][x].color == this.color) {
						res++;
					}
				}
				y -= 2;
				if (1 <= y && y <= 8) {
					if (board.boardConf[y][x] != null && board.boardConf[y][x].color == this.color) {
						res++;
					}
				}
				y += 2;
			}
			if (dx == -1 || dx == 1) {
				y += 2;
				if (1 <= y && y <= 8) {
					if (board.boardConf[y][x] != null && board.boardConf[y][x].color == this.color) {
						res++;
					}
				}
				y -= 4;
				if (1 <= y && y <= 8) {
					if (board.boardConf[y][x] != null && board.boardConf[y][x].color == this.color) {
						res++;
					}
				}
				y += 4;
			}
			x -= dx;
		}

		return res;

	}

}
