import java.util.ArrayList;

// TODO ? IDK, poate la etapa 3
// sincer nu imi plac multe lucruri cum au fost gandite
// de exemplu, e aiurea ca trebuie sa verific mereu cu instanceof ce clasa e piesa
// si, de asemenea, ar fi trebuit sa generam mutarile posibile pentru o instanta a piesei
// nu ca o metoda statica care primeste o piesa...
// la fel si pentru clonarea pieselor

public class Board {
	// valoare cand muta in sah
	public static final int ILLEGAL_CHECK_VALUE = 1000000;
	// valoare cand suntem in sah
	public static final int CHECK_MATE_VALUE = -1000000;
	// valoare egalitate
	public static final int DRAW_VALUE = 0;
	// valoare piese(fara rege, deoarece el e tratat doar pentru sah)
	static final int PAWN_VALUE = 1000;
	static final int BISHOP_VALUE = 3 * PAWN_VALUE;
	static final int KNIGHT_VALUE = 3 * PAWN_VALUE;
	static final int ROOK_VALUE   = 5 * PAWN_VALUE;
	static final int QUEEN_VALUE  = 9 * PAWN_VALUE;
	static final int CHECK_VALUE  = 3 * PAWN_VALUE;


	Piece[][] boardConf;
	Piece blackKing;
	Piece whiteKing;
	Game.colors engineColor;

	public Board() {
		boardConf = new Piece[9][9];
	}

	public Board(Game.colors engineColor) {
		this.engineColor = engineColor;
		boardConf = new Piece[9][9];
		initBoard();
	}


	public void initBoard() {
		boardConf[1][1] = new Rook(Piece.pieceType.ROOK, Game.colors.WHITE, 1, 1);
		boardConf[1][2] = new Knight(Piece.pieceType.KNIGHT, Game.colors.WHITE, 2, 1);
		boardConf[1][3] = new Bishop(Piece.pieceType.BISHOP, Game.colors.WHITE, 3, 1);
		boardConf[1][4] = new Queen(Piece.pieceType.QUEEN, Game.colors.WHITE, 4, 1);
		boardConf[1][5] = new King(Piece.pieceType.KING, Game.colors.WHITE, 5, 1);
		boardConf[1][6] = new Bishop(Piece.pieceType.BISHOP, Game.colors.WHITE, 6, 1);
		boardConf[1][
				7] = new Knight(Piece.pieceType.KNIGHT, Game.colors.WHITE, 7, 1);
		boardConf[1][8] = new Rook(Piece.pieceType.ROOK, Game.colors.WHITE, 8, 1);
		for (int j = 1; j <= 8; j++) {
			boardConf[2][j] = new Pawn(Piece.pieceType.OPENINGPAWN, Game.colors.WHITE, j, 2);
			boardConf[7][j] = new Pawn(Piece.pieceType.OPENINGPAWN, Game.colors.BLACK, j, 7);
		}
		boardConf[8][1] = new Rook(Piece.pieceType.ROOK, Game.colors.BLACK, 1, 8);
		boardConf[8][2] = new Knight(Piece.pieceType.KNIGHT, Game.colors.BLACK, 2, 8);
		boardConf[8][3] = new Bishop(Piece.pieceType.BISHOP, Game.colors.BLACK, 3, 8);
		boardConf[8][4] = new Queen(Piece.pieceType.QUEEN, Game.colors.BLACK, 4, 8);
		boardConf[8][5] = new King(Piece.pieceType.KING, Game.colors.BLACK, 5, 8);
		boardConf[8][6] = new Bishop(Piece.pieceType.BISHOP, Game.colors.BLACK, 6, 8);
		boardConf[8][7] = new Knight(Piece.pieceType.KNIGHT, Game.colors.BLACK, 7, 8);
		boardConf[8][8] = new Rook(Piece.pieceType.ROOK, Game.colors.BLACK, 8, 8);
		whiteKing = boardConf[1][5];
		blackKing = boardConf[8][5];
	}

	// functie de afisare a tablei pentru debug
	public void printTable() {
		for (int x = 1; x <= 8; x++) {
			for (int y = 1; y <= 8; y++) {
				if (boardConf[y][x] != null) {
					if (boardConf[y][x] instanceof Rook) {
						System.out.print("T ");
						continue;
					}
					if (boardConf[y][x] instanceof Knight) {
						System.out.print("C ");
						continue;
					}
					if (boardConf[y][x] instanceof Bishop) {
						System.out.print("N ");
						continue;
					}
					if (boardConf[y][x] instanceof Queen) {
						System.out.print("Q ");
						continue;
					}
					if (boardConf[y][x] instanceof King) {
						System.out.print("K ");
						continue;
					}
					if (boardConf[y][x] instanceof Pawn) {
						if (boardConf[y][x].type == Piece.pieceType.OPENINGPAWN) {
							System.out.print("S ");
							continue;
						} else {
							System.out.print("P ");
							continue;
						}
					}
				} else {System.out.print("_ ");}
			}
			System.out.println();
		}
	}
	public boolean friendlyPiece(int x, int y, Game.colors color) {
		if ((y >= 1 && y <= 8) && (x >= 1 && x <= 8) && (boardConf[y][x] != null) && (boardConf[y][x].color == color))
			return true;
		else
			return false;
	}
	
	public boolean opponentMove(String move) {
		System.out.println("POZITIE REGE ALB " + whiteKing.y + "   " + whiteKing.x);
		System.out.println("BLACK KING POSITION " + blackKing.y + "   " + blackKing.x);
		int[] mv = Utils.moveToPositions(move);
		int newX = mv[2], oldX = mv[0], newY = mv[3], oldY = mv[1];
		if (boardConf[oldY][oldX] != null) {

			boardConf[newY][newX] = boardConf[oldY][oldX];
			boardConf[oldY][oldX] = null;
			boardConf[newY][newX].y = newY;
			boardConf[newY][newX].x = newX;
			
			// conditii necesara pentru mutari de pioni in Force mode
			if (boardConf[newY][newX].type == Piece.pieceType.OPENINGPAWN) {
				boardConf[newY][newX].type = Piece.pieceType.PAWN;
			}

			// pawn promotion
			if ((newY == 8) && (boardConf[newY][newX].type == Piece.pieceType.PAWN)
					&& (boardConf[newY][newX].color == Game.colors.WHITE)) {
				boardConf[newY][newX] = new Queen(Piece.pieceType.QUEEN, boardConf[newY][newX].color, boardConf[newY][newX].x, boardConf[newY][newX].y);
			}

			// pawn promotion
			if ((newY == 1) && (boardConf[newY][newX].type == Piece.pieceType.PAWN)
					&& (boardConf[newY][newX].color == Game.colors.BLACK)) {
				boardConf[newY][newX] = new Queen(Piece.pieceType.QUEEN, boardConf[newY][newX].color, boardConf[newY][newX].x, boardConf[newY][newX].y);
			}

			// verificam daca oponentul a facut en-passant
			//vom avea un pion alb pe linia 6, si unul negru pe aceeasi coloana dar pe linia 5
			if((newY==6)&&(boardConf[newY][newX].color == Game.colors.WHITE)&&(boardConf[newY][newX].type == Piece.pieceType.PAWN)&&(boardConf[5][newX]!=null)) {
				if (boardConf[5][newX].type==Piece.pieceType.PAWN) {
					boardConf[5][newX] = null;
				}
			}

			//vom avea un pion negru pe linia 3, si unul alb pe aceeasi coloana dar linia 4
			if((newY==3)&&(boardConf[newY][newX].color == Game.colors.BLACK)&&(boardConf[newY][newX].type == Piece.pieceType.PAWN)&&(boardConf[4][newX]!=null)) {
				if (boardConf[4][newX].type==Piece.pieceType.PAWN) {
					boardConf[4][newX] = null;
				}
			}

			//facem tracing pe rege
			if(boardConf[newY][newX].type == Piece.pieceType.KING ) {
				if (boardConf[newY][newX].color == Game.colors.WHITE ) {
					whiteKing = boardConf[newY][newX];
				} else {
					blackKing = boardConf[newY][newX];
				}
			}

			// verificam daca oponentul a facut rocada mare/mica
			// Pentru a ne da seama daca a facut rocada, trebuie sa vedem daca regele s-a mutat cu 2 casute(el in mod normal se muta 1)
			// aceasta conditie e necesara si suficienta
			// se actualizeaza pozitia turei(o luam din colt) si o punem langa rege
			if(boardConf[newY][newX].type == Piece.pieceType.KING && Math.abs(newX - oldX) == 2) {
				// rocada mica alb
				if(newY == 1 && newX == 7) {
					boardConf[1][6] = boardConf[1][8];
					boardConf[1][8] = null;
				}
				// rocada mare alb
				if(newY == 1 && newX == 3) {
					boardConf[1][4] = boardConf[1][1];
					boardConf[1][1] = null;
				}
				// rocada mica negru
				if(newY == 8 && newX == 7) {
					boardConf[8][6] = boardConf[8][8];
					boardConf[8][8] = null;
				}
				// rocada mare negru
				if(newY == 8 && newX == 3) {
					boardConf[8][4] = boardConf[8][1];
					boardConf[8][1] = null;
				}
			}
			return true;
		}
		return false;
	}

	public boolean makeMove(Move move) {
		int oldX = move.oldX, oldY = move.oldY, newX = move.newX, newY = move.newY;
		// daca piesa nu e nula
		if (boardConf[oldY][oldX] != null) {
			
			Piece oldPiece = Piece.clone(boardConf[oldY][oldX]), 
				  newPiece = Piece.clone(boardConf[newY][newX]); 
					
			// actualizam coordonatele noii piese
			move.piece.x = move.newX;
			move.piece.y = move.newY;

			// dupa actualizam tabla cu piesa
			boardConf[newY][newX] = move.piece;
			boardConf[oldY][oldX] = null;

			if((boardConf[newY][newX].type == Piece.pieceType.KING) && ((move.castlingRookX == 0) || (move.castlingRookY == 0))) {
				//daca prin miscarea curenta regele se muta in sah invalidam
				if (King.check(this, (King)boardConf[newY][newX])) {

					//readucem tabla in stateul ei anterior
					//(Regele putea sa captureze o piesa, mutare care ca il baga in sah, atunci trebuie intors regele la locul sau si la fel si piesa
					boardConf[oldY][oldX] = oldPiece;
					boardConf[newY][newX] = newPiece;
					return false;
				} else {
					if(boardConf[newY][newX].color == Game.colors.WHITE) {
						whiteKing = boardConf[newY][newX];
					}else {
						blackKing = boardConf[newY][newX];
					}
				}
			}
			
			
			//daca in urma miscarii curente regele e in sah, restituim tabla si invalidam miscarea
			if ((move.piece.color == Game.colors.WHITE) && (King.check(this, (King)whiteKing))) {
				boardConf[oldY][oldX] = oldPiece;
				boardConf[newY][newX] = newPiece;
				return false;
			}
			
			if ((move.piece.color == Game.colors.BLACK) && (King.check(this, (King)blackKing))) {
				boardConf[oldY][oldX] = oldPiece;
				boardConf[newY][newX] = newPiece;
				return false;
			}
			
			
			// intai verificam daca trebuie sa modificam piesa(la pion se intampla asta)
			// conditia necesara pentru mutari de pioni in Force mode
			if (boardConf[newY][newX].type == Piece.pieceType.OPENINGPAWN) {
				move.piece.type = Piece.pieceType.PAWN;
				boardConf[newY][newX].type = move.piece.type;
			}

			// pawn promotion
			if ((newY == 8) && (boardConf[newY][newX].type == Piece.pieceType.PAWN)
					&& (boardConf[newY][newX].color == Game.colors.WHITE)) {
				move.piece = new Queen(Piece.pieceType.QUEEN, boardConf[newY][newX].color, boardConf[newY][newX].x, boardConf[newY][newX].y);
				boardConf[newY][newX] = move.piece;
			}

			// pawn promotion
			if ((newY == 1) && (boardConf[newY][newX].type == Piece.pieceType.PAWN)
					&& (boardConf[newY][newX].color == Game.colors.BLACK)) {
				move.piece = new Queen(Piece.pieceType.QUEEN, boardConf[newY][newX].color, boardConf[newY][newX].x, boardConf[newY][newX].y);
				boardConf[newY][newX] = move.piece;
			}



			// trebuie sa verificam daca am avut o mutare en passant
			// daca da, trebuie sa eliberam locul unde se afla piesa care a fost capturata
			if(move.enPassantX != 0 && move.enPassantY != 0) {
				boardConf[move.enPassantY][move.enPassantX] = null;
			}

			// trebuie sa verificam daca am avut o rocada
			// daca da, trebuie sa eliberam locul unde a fost tura si sa punem tura unde trebuie
			if(move.castlingRookX != 0 && move.castlingRookY != 0) {
				// tratam rocadele pentru alb
				if(move.castlingRookY == 1 && move.castlingRookX == 6) {
					boardConf[move.castlingRookY][move.castlingRookX] = boardConf[1][8];
					boardConf[1][8] = null;
				}
				if(move.castlingRookX == 1 && move.castlingRookY == 4) {
					boardConf[move.castlingRookY][move.castlingRookX] = boardConf[1][1];
					boardConf[1][1] = null;
				}
				// tratam rocadele pentru negru
				if(move.castlingRookX == 8 && move.castlingRookY == 6) {
					boardConf[move.castlingRookY][move.castlingRookX] = boardConf[8][8];
					boardConf[8][8] = null;
				}
				if(move.castlingRookX == 8 && move.castlingRookY == 4) {
					boardConf[move.castlingRookY][move.castlingRookX] = boardConf[8][1];
					boardConf[8][1] = null;
				}

			}
			
			for (int i = 1; i <= 8; i++) {
				if(boardConf[4][i]!= null) {
					if (boardConf[4][i] instanceof Pawn) {
						((Pawn)boardConf[4][i]).enPassantIllusion(this);
					}
				}
				
				if(boardConf[5][i]!= null) {
					if (boardConf[5][i] instanceof Pawn) {
						((Pawn)boardConf[5][i]).enPassantIllusion(this);
					}
				}
			}
			return true;
		}
		return false;
	}

	public boolean possibleMove(int x, int y) {
		if ((y >= 1 && y <= 8) && (x >= 1 && x <= 8) && (boardConf[y][x] == null))
			return true;
		else
			return false;
	}

	public boolean attackMove(int x, int y, Game.colors color) {
		if ((y >= 1 && y <= 8) && (x >= 1 && x <= 8) && (boardConf[y][x] != null)
				&& (boardConf[y][x].color != color))
			return true;
		else
			return false;
	}

	public ArrayList<Move> getAllMoves(){
		ArrayList<Move> moves = new ArrayList<Move>();
		for(int x = 1; x <= 8; x++) {
			for(int y = 1 ; y <= 8; y++) {
				if(boardConf[y][x] != null) {
					if(boardConf[y][x] instanceof Rook) {
						moves.addAll(Rook.rookMoves(this, (Rook)boardConf[y][x]));
					}
					if(boardConf[y][x] instanceof Knight) {
						moves.addAll(Knight.knightMoves(this, (Knight)boardConf[y][x]));
					}
					if(boardConf[y][x] instanceof Bishop) {
						moves.addAll(Bishop.bishopMoves(this, (Bishop)boardConf[y][x]));
					}
					if(boardConf[y][x] instanceof Queen) {
						moves.addAll(Queen.queenMoves(this, (Queen)boardConf[y][x]));
					}
					if(boardConf[y][x] instanceof King) {
						moves.addAll(King.kingMoves(this, (King)boardConf[y][x]));
					}
					if(boardConf[y][x] instanceof Pawn) {
						moves.addAll(Pawn.pawnMoves(this, (Pawn)boardConf[y][x]));
					}
				}
			}
		}
		return moves;
	}
	//TODO
	// functia intoarce true daca jocul s-a terminat, altfel false
	public boolean ended() {
		return false;
	}
	public int winner(Game.colors player) {
		if(engineColor== Game.colors.BLACK){
			if (King.checkmate(this,((King)blackKing)))
				return -1;
			if (King.checkmate(this,((King)whiteKing)))
				return 1;
		}
		if(engineColor== Game.colors.WHITE){
			if (King.checkmate(this,((King)whiteKing)))
				return -1;
			if (King.checkmate(this,((King)blackKing)))
				return 1;
		}

		return 0;
	}
	public int eval() {
		int result = 0;
		int def;
				System.out.println("CHECKMATE:"+ King.checkmate(this,((King)blackKing)));
		int noPawn = 0, noBishop = 0, noKnight = 0, noRook = 0, noQueen = 0;
		int noOppPawn = 0, noOppBishop = 0, noOppKnight = 0, noOppRook = 0, noOppQueen = 0;
		for(int x = 1; x <= 8; x++) {
			for(int y = 1; y <= 8; y++) {
				if(this.boardConf[y][x] != null) {
					if(this.boardConf[y][x].color == engineColor) {
						if(this.boardConf[y][x] instanceof Pawn) {
							noPawn++;
						} else
						if(this.boardConf[y][x] instanceof Rook) {
							noRook++;
						} else
						if(this.boardConf[y][x] instanceof Knight) {
							noKnight++;
						} else
						if(this.boardConf[y][x] instanceof Bishop) {
							noBishop++;
						} else
						if(this.boardConf[y][x] instanceof Queen) {
							noQueen++;
						}
						if (this.boardConf[y][x] instanceof King) {
							if (King.check(this, ((King) boardConf[y][x]))) {
								result += CHECK_MATE_VALUE ;
							}
						}
					} else {
						if(this.boardConf[y][x] instanceof Pawn) {
							noOppPawn++;
						} else
						if(this.boardConf[y][x] instanceof Rook) {
							noOppRook++;
						} else
						if(this.boardConf[y][x] instanceof Knight) {
							noOppKnight++;
						} else
						if(this.boardConf[y][x] instanceof Bishop) {
							noOppBishop++;
						} else
						if(this.boardConf[y][x] instanceof Queen) {
							noOppQueen++;
						}
						if (this.boardConf[y][x] instanceof King) {
							if (King.check(this, ((King) boardConf[y][x]))) {
								result += CHECK_VALUE ;
							}
						}
					}
				}
			}
			
		}

		result += (noPawn - noOppPawn) * PAWN_VALUE + (noBishop - noOppBishop) * BISHOP_VALUE + 
				(noKnight - noOppKnight) * KNIGHT_VALUE + (noRook -noOppRook) * ROOK_VALUE + (noQueen - noOppQueen) * QUEEN_VALUE;
						

		return result;
	}

	public Board clone() {
		Board board = new Board();
		if(this.engineColor == Game.colors.BLACK) {
			board.engineColor = Game.colors.BLACK;
		} else {
			board.engineColor = Game.colors.WHITE;
		}
		board.blackKing = this.blackKing;
		board.whiteKing = this.whiteKing;
		for(int x = 1; x <= 8; x++) {
			for(int y = 1 ; y <= 8; y++) {
				if(this.boardConf[y][x] == null) {
					board.boardConf[y][x] = null;
				} else {
					if(this.boardConf[y][x] instanceof Pawn) {
						board.boardConf[y][x] = Pawn.clone((Pawn)this.boardConf[y][x]);
					} else
					if(this.boardConf[y][x] instanceof Rook) {
						board.boardConf[y][x] = Rook.clone((Rook)this.boardConf[y][x]);
					} else
					if(this.boardConf[y][x] instanceof Knight) {
						board.boardConf[y][x] = Knight.clone((Knight)this.boardConf[y][x]);
					} else
					if(this.boardConf[y][x] instanceof Bishop) {
						board.boardConf[y][x] = Bishop.clone((Bishop)this.boardConf[y][x]);
					} else
					if(this.boardConf[y][x] instanceof Queen) {
						board.boardConf[y][x] = Queen.clone((Queen)this.boardConf[y][x]);
					} else
					if(this.boardConf[y][x] instanceof King) {
						board.boardConf[y][x] = King.clone((King)this.boardConf[y][x]);
					}
				}
			}
		}
		return board;
	}
}
