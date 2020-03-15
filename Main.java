package bpod;

import java.io.*;
import java.util.*;

import bpod.Game.engineColors;
import bpod.Game.states;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		InputStreamReader in = new InputStreamReader(System.in);

		BufferedReader input = new BufferedReader(in);
		
		int array[] = Utils.moveToPositions("d7d6");
		String line = null;
		Game game = null;
		//imi aleg o piesa cu care fac mutari cat timp este posibil
		Piece blackPiece = null, whitePiece = null;
		while (true) {
			line = input.readLine();
			if (line.contains("protover 2")) {
				Utils.writeCommand("feature sigint=0");
			}
			else
			if (line.contains("new")) {
				game = new Game(Board.getBoardInstance(), states.GO, engineColors.BLACK);
				// imi aleg un pion pe care o sa-l mut pana nu mai am mutare valida
				blackPiece = game.board.boardConf[7][1];
				whitePiece = game.board.boardConf[2][8];
			}
			else
			if (line.matches("^([a-h][1-8]+)+$")) {
				// metoda actualizeaza board
				game.board.opponentMove(line);
				// metoda calculeaza mutare(cauta mutare valida, altfel da resign)
				boolean ok = Utils.pawnMove(game.board, blackPiece);
				if(ok == false)
					Utils.writeCommand("resign");
			}
		}
	}
}