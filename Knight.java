package bpod;

import bpod.Game.colors;

public class Knight extends Piece {

	public Knight(pieceType type, colors color, int x, int y) {
		super(type, color, x, y);
	}
	
	//las aici toate variantele fiindca knightMove poate vrem sa il imbunatatim pentru algoritm ulterior si sa nu stam sa le scriem de la 0
	//sunt toate cele 8 pozitii in care poate ajunge un cal de orice culoare de la mijlocul tablei
	//nu folosesc nicaieri metoda asta tho
	public static boolean allPossibilitiesForAKnight(Board board, Piece piece) {
		if(piece != null) {
			//atacuri
			if (board.attackMove(piece.x + 2, piece.y - 1) == true) {
				board.makeMove(piece.x, piece.y, piece.x + 2, piece.y - 1);
				return true;
			}
			
			if (board.attackMove(piece.x + 1, piece.y - 2) == true) {
				board.makeMove(piece.x, piece.y, piece.x + 1, piece.y - 2);
				return true;
			}	
				
			if (board.attackMove(piece.x - 1, piece.y - 2) == true) {
				board.makeMove(piece.x, piece.y, piece.x - 1, piece.y - 2);
				return true;
			}
			
			if (board.attackMove(piece.x - 2, piece.y - 1) == true) {
				board.makeMove(piece.x, piece.y, piece.x - 2, piece.y - 1);
				return true;
			}
			
			if (board.attackMove(piece.x - 2, piece.y + 1) == true) {
				board.makeMove(piece.x, piece.y, piece.x - 2, piece.y + 1);
				return true;
			}
			
			if (board.attackMove(piece.x - 1, piece.y + 2) == true) {
				board.makeMove(piece.x, piece.y, piece.x - 1, piece.y + 2);
				return true;
			}
				
			if (board.attackMove(piece.x + 1, piece.y + 2) == true) {
				board.makeMove(piece.x, piece.y, piece.x + 1, piece.y + 2);
				return true;
			}	
				
			if (board.attackMove(piece.x + 2, piece.y + 1) == true) {
				board.makeMove(piece.x, piece.y, piece.x + 2, piece.y + 1);
				return true;
			}
			
			//miscari
			if (board.possibleMove(piece.x + 2, piece.y - 1) == true) {
				board.makeMove(piece.x, piece.y, piece.x + 2, piece.y - 1);
				return true;
			}
			
			if (board.possibleMove(piece.x + 1, piece.y - 2) == true) {
				board.makeMove(piece.x, piece.y, piece.x + 1, piece.y - 2);
				return true;
			}	
				
			if (board.possibleMove(piece.x - 1, piece.y - 2) == true) {
				board.makeMove(piece.x, piece.y, piece.x - 1, piece.y - 2);
				return true;
			}
			
			if (board.possibleMove(piece.x - 2, piece.y - 1) == true) {
				board.makeMove(piece.x, piece.y, piece.x - 2, piece.y - 1);
				return true;
			}
			
			if (board.possibleMove(piece.x - 2, piece.y + 1) == true) {
				board.makeMove(piece.x, piece.y, piece.x - 2, piece.y + 1);
				return true;
			}
			
			if (board.possibleMove(piece.x - 1, piece.y + 2) == true) {
				board.makeMove(piece.x, piece.y, piece.x - 1, piece.y + 2);
				return true;
			}
				
			if (board.possibleMove(piece.x + 1, piece.y + 2) == true) {
				board.makeMove(piece.x, piece.y, piece.x + 1, piece.y + 2);
				return true;
			}	
				
			if (board.possibleMove(piece.x + 2, piece.y + 1) == true) {
				board.makeMove(piece.x, piece.y, piece.x + 2, piece.y + 1);
				return true;
			}
			
			
		}
		// piesa e null
		return false;
	}
	
	
	//Sunt miscarile din allPossibilitiesForAKnight dar ordonate firesc pentru cum miscam calul de pe negru/alb
	public static boolean knightMove(Board board, Piece piece) {
		if(piece != null) {
			if (board.boardConf[piece.y][piece.x].color == colors.BLACK) {		
				//atacuri
				if (board.attackMove(piece.x + 2, piece.y - 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 2, piece.y - 1);
					return true;
				}
				
				if (board.attackMove(piece.x + 1, piece.y - 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y - 2);
					return true;
				}	
					
				if (board.attackMove(piece.x - 1, piece.y - 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y - 2);
					return true;
				}
				
				if (board.attackMove(piece.x - 2, piece.y - 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 2, piece.y - 1);
					return true;
				}
				
				if (board.attackMove(piece.x - 2, piece.y + 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 2, piece.y + 1);
					return true;
				}
				
				if (board.attackMove(piece.x - 1, piece.y + 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y + 2);
					return true;
				}
					
				if (board.attackMove(piece.x + 1, piece.y + 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y + 2);
					return true;
				}	
					
				if (board.attackMove(piece.x + 2, piece.y + 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 2, piece.y + 1);
					return true;
				}
				
				//miscari
				if (board.possibleMove(piece.x + 2, piece.y - 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 2, piece.y - 1);
					return true;
				}
				
				if (board.possibleMove(piece.x + 1, piece.y - 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y - 2);
					return true;
				}	
					
				if (board.possibleMove(piece.x - 1, piece.y - 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y - 2);
					return true;
				}
				
				if (board.possibleMove(piece.x - 2, piece.y - 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 2, piece.y - 1);
					return true;
				}
				
				if (board.possibleMove(piece.x - 2, piece.y + 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 2, piece.y + 1);
					return true;
				}
				
				if (board.possibleMove(piece.x - 1, piece.y + 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y + 2);
					return true;
				}
					
				if (board.possibleMove(piece.x + 1, piece.y + 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y + 2);
					return true;
				}	
					
				if (board.possibleMove(piece.x + 2, piece.y + 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 2, piece.y + 1);
					return true;
				}
				
				
				
			} else {
				//atacuri
				if (board.attackMove(piece.x + 1, piece.y + 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y + 2);
					return true;
				}	
					
				if (board.attackMove(piece.x + 2, piece.y + 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 2, piece.y + 1);
					return true;
				}
				
				if (board.attackMove(piece.x - 2, piece.y + 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 2, piece.y + 1);
					return true;
				}
				
				if (board.attackMove(piece.x - 1, piece.y + 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y + 2);
					return true;
				}
				
				
				if (board.attackMove(piece.x + 2, piece.y - 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 2, piece.y - 1);
					return true;
				}
				
				if (board.attackMove(piece.x + 1, piece.y - 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y - 2);
					return true;
				}	
					
				if (board.attackMove(piece.x - 1, piece.y - 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y - 2);
					return true;
				}
				
				if (board.attackMove(piece.x - 2, piece.y - 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 2, piece.y - 1);
					return true;
				}
				
				//miscari
				if (board.possibleMove(piece.x + 1, piece.y + 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y + 2);
					return true;
				}	
					
				if (board.possibleMove(piece.x + 2, piece.y + 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 2, piece.y + 1);
					return true;
				}
				
				if (board.possibleMove(piece.x + 1, piece.y - 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y - 2);
					return true;
				}	
				
				if (board.possibleMove(piece.x + 2, piece.y - 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 2, piece.y - 1);
					return true;
				}
					
				if (board.possibleMove(piece.x - 1, piece.y - 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y - 2);
					return true;
				}
				
				if (board.possibleMove(piece.x - 2, piece.y - 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 2, piece.y - 1);
					return true;
				}
				
				if (board.possibleMove(piece.x - 1, piece.y + 2) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y + 2);
					return true;
				}
				
				if (board.possibleMove(piece.x - 2, piece.y + 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 2, piece.y + 1);
					return true;
				}
			}
		}
		// piesa e null
		return false;
	}
	
	
	
}
