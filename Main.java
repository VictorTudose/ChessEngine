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
		
		//int array[] = Utils.moveToPositions("a2b3");
		//System.out.println(array[0] + " " + array[1] + " " + array[2] + " " + array[3]);
		//String string = Utils.positionsToMove(7, 7, 7, 8);
		//System.out.println(string);
		//System.exit(0);
		String line = null;
		Game game = null;
		//imi aleg o piesa pe care sa fac mutari
		Piece piece = null;
		while (true) {
			line = input.readLine();
			if (line.contains("protover 2")) {
				Utils.writeCommand("feature sigint=0");
			}
			else
			if (line.contains("new")) {
				game = new Game(Board.getBoardInstance(), states.GO, engineColors.BLACK);
				// imi aleg un pion
				piece = game.board.boardConf[7][4];
			}
			else
			if (line.matches("^([a-h][1-8]+)+$")) {
				// metoda actualizeaza board
				game.board.opponentMove(line);
				// metoda calculeaza mutare(cauta mutare valida, altfel da resign)
				boolean ok = Utils.pawnMove(game.board, piece);
				
				if(ok == false)
					Utils.writeCommand("resign");
			}
		}

	}

}
