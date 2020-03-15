package bpod;

import java.util.*;

public class Utils {
	public static String positionsToMove(int oldX, int oldY, int newX, int newY) {
		StringBuilder move = new StringBuilder("move ");
		move.append(Character.toString(((char) oldX) + 'a' - 1))
			.append(Character.toString(((char) oldY) + '0'))
			.append(Character.toString(((char) newX) + 'a' - 1))
			.append(Character.toString(((char) newY) + '0'));
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

	public static void writeCommand(String command) {
		System.out.println(command);
	}

	public static boolean pawnMove(Board board, Piece piece) {
		if(piece != null) {
			if (board.boardConf[piece.y][piece.x].color == Piece.colors.BLACK) {
				if (board.attackMove(piece.x - 1, piece.y - 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y - 1);
					// daca a promovat in regina, dam resign
					if (piece.y == 1) {
						board.boardConf[piece.y][piece.x].type = Piece.pieceType.QUEEN;
						return false;
					}
					return true;
				} else if (board.attackMove(piece.x + 1, piece.y - 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y - 1);
					// daca a promovat in regina, dam resign
					if (piece.y == 1) {
						board.boardConf[piece.y][piece.x].type = Piece.pieceType.QUEEN;
						return false;
					}
					return true;
				} else if (board.possibleMove(piece.x, piece.y - 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x, piece.y - 1);
					// daca a promovat in regina, dam resign
					if (piece.y == 1) {
						board.boardConf[piece.y][piece.x].type = Piece.pieceType.QUEEN;
						return false;
					}
					return true;
				}
			} else {
				if (board.attackMove(piece.x - 1, piece.y + 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x - 1, piece.y + 1);
					// daca a promovat in regina, dam resign
					if (piece.y == 8) {
						board.boardConf[piece.y][piece.x].type = Piece.pieceType.QUEEN;
						return false;
					}
					return true;
				} else if (board.attackMove(piece.x + 1, piece.y + 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x + 1, piece.y + 1);
					// daca a promovat in regina, dam resign
					if (piece.y == 8) {
						board.boardConf[piece.y][piece.x].type = Piece.pieceType.QUEEN;
						return false;
					}
					return true;
				} else if (board.possibleMove(piece.x, piece.y + 1) == true) {
					board.makeMove(piece.x, piece.y, piece.x, piece.y + 1);
					// daca a promovat in regina, dam resign
					if (piece.y == 8) {
						board.boardConf[piece.y][piece.x].type = Piece.pieceType.QUEEN;
						return false;
					}
					return true;
				}
			}
			//nu a reusit sa faca miscare
			return false;
		}
		// piesa e null
		else
			return false;
	}
}