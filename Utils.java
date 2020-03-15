package bpod;

import java.util.*;

public class Utils {
	public static String positionsToMove(int oldY, int oldX, int newY, int newX) {
		StringBuilder move = new StringBuilder("move ");
		move.append(Character.toString(((char) oldX) + 'a' - 1));
		move.append(Character.toString(((char) oldY) + '0'));
		move.append(Character.toString(((char) newX) + 'a' - 1));
		move.append(Character.toString(((char) newY) + '0'));
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
				if (board.attackMove(piece.y - 1, piece.x - 1) == true) {
					board.makeMove(piece.y, piece.x, piece.y - 1, piece.x - 1);
					Utils.writeCommand(positionsToMove(piece.y, piece.x, piece.y - 1, piece.x - 1));
				} else if (board.attackMove(piece.y - 1, piece.x + 1) == true) {
					board.makeMove(piece.y, piece.x, piece.y - 1, piece.x + 1);
					Utils.writeCommand(positionsToMove(piece.y, piece.x, piece.y - 1, piece.x + 1));
				} else if (board.possibleMove(piece.y - 1, piece.x) == true) {
					board.makeMove(piece.y, piece.x, piece.y - 1, piece.x);
					Utils.writeCommand(positionsToMove(piece.y, piece.x, piece.y - 1, piece.x));
				}
				// avansare
				else if (piece.y == 1) {
					board.boardConf[piece.y][piece.x] = new Piece(Piece.pieceType.QUEEN, Piece.colors.BLACK, piece.y, piece.x);
				} else {
					return false;
				}
			} else {
				if (board.attackMove(piece.y + 1, piece.x - 1) == true) {
					board.makeMove(piece.y, piece.x, piece.y + 1, piece.x - 1);
					Utils.writeCommand(positionsToMove(piece.y, piece.x, piece.y + 1, piece.x - 1));
				} else if (board.attackMove(piece.y + 1, piece.x + 1) == true) {
					board.makeMove(piece.y, piece.x, piece.y + 1, piece.x + 1);
					Utils.writeCommand(positionsToMove(piece.y, piece.x, piece.y + 1, piece.x + 1));
				} else if (board.possibleMove(piece.y + 1, piece.x) == true) {
					board.makeMove(piece.y, piece.x, piece.y + 1, piece.x);
					Utils.writeCommand(positionsToMove(piece.y, piece.x, piece.y + 1, piece.x));
				}
				// avansare
				else if (piece.y == 8) {
					board.boardConf[piece.y][piece.x] = new Piece(Piece.pieceType.QUEEN, Piece.colors.BLACK, piece.y, piece.x);
				} else {
					return false;
				}
			}
			return true;
		}
		else
			return false;
	}
}