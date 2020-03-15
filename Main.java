package bpod;

import java.io.*;
import java.util.*;

import bpod.Game.colors;
import bpod.Game.states;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		InputStreamReader in = new InputStreamReader(System.in);

		BufferedReader input = new BufferedReader(in);

		int array[] = Utils.moveToPositions("d7d6");
		String line = null;
		Game game = null;
		long startingTime = 0, currentTime = 0;
		int pause = 0;

		// imi aleg o piesa cu care fac mutari cat timp este posibil
		Piece blackPiece = null, whitePiece = null;
		while (true) {
			line = input.readLine();
			if (line.contains("protover 2")) {
				Utils.writeCommand("feature sigint=0");
			}

			if (line.contains("new")) {
				game = new Game(Board.getBoardInstance(colors.BLACK), states.GO, colors.BLACK, Utils.fiveMinutes, Utils.fiveMinutes);
				
				startingTime = System.currentTimeMillis();
				currentTime = startingTime;
				// imi aleg un pion pe care o sa-l mut pana nu mai am mutare valida
				blackPiece = game.board.boardConf[8][2];
				whitePiece = game.board.boardConf[2][8];
			}

			if (line.contains("white")) {
				game.engineColor = colors.BLACK;
				pause = 1;
			}

			if (line.contains("black")) {
				game.engineColor = colors.WHITE;
				pause = 1;
			}

			if (line.contains("go")) {
				pause = 0;
				currentTime = System.currentTimeMillis();
				game.setEngineTime(currentTime);
			}

			if (line.contains("force")) {
				line = input.readLine();
				while(!line.equals("go"))
				{
					int[] move=Utils.moveToPositions(line);
					game.board.makeMove(move[0],move[1],move[2],move[3]);
					line=input.readLine();
				}
				pause=0;
				game.setOpTime(Utils.fiveMinutes);
				game.setEngineTime(Utils.fiveMinutes);
			}

			if (line.matches("^([a-h][1-8]+)+$")) {
				// metoda actualizeaza board
				game.board.opponentMove(line);
				if(game.board.boardConf[blackPiece.y][blackPiece.x].color!=blackPiece.color) {
					Utils.writeCommand("resign");
				}
				// metoda calculeaza mutare(cauta mutare valida, altfel da resign)
				boolean ok = Knight.knightMove(game.board, blackPiece);
				if (ok == false)
					Utils.writeCommand("resign");
			}

			if (game != null) {
				if (game.engineTime <= 0) {
					Utils.writeCommand("resign");
				}

				if (pause == 0) {
					game.engineTime -= (currentTime - System.currentTimeMillis());
				}
			}

		}
	}
}