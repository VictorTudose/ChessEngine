
import java.io.*;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) throws Exception {

		InputStreamReader in = new InputStreamReader(System.in);

		BufferedReader input = new BufferedReader(in);
		
		String line = null;
		Game game = null;		
		while (true) {
			line = input.readLine();
			if (line.contains("protover 2")) {
				System.out.println("feature sigterm=0 sigint=0 san=0");
			}

			if (line.contains("new")) {
				game = new Game(new Board(Game.colors.BLACK), Game.states.GO, Game.colors.BLACK, Utils.fiveMinutes,
						Utils.fiveMinutes);
			}

			if(game != null) {
				if (line.contains("black")) {
					game.setColor(Game.colors.BLACK);
				}

				if (line.contains("white")) {
					game.setColor(Game.colors.WHITE);
				}
				
				if (line.contains("go")) {
					game.state = Game.states.GO;
					// imi aleg o mutare din cele valide(cea mai buna, folosind negamax cu taietura alpha-beta)
					// daca nu am mutari, dau resign
					Pair<Integer, Move> p = negamaxAlphabeta(game.board, 5, Integer.MIN_VALUE, Integer.MAX_VALUE);
					Move move = p.second;
					if(move != null) {
						int oldX = move.oldX, oldY = move.oldY, newX = move.newX, newY = move.newY;
						game.board.makeMove(move);
						System.out.println(Utils.positionsToMove(oldX, oldY, newX, newY));	
					} else {
						System.out.println("resign");
					}
					game.setOpTime(Utils.fiveMinutes);
					game.setEngineTime(Utils.fiveMinutes);
				}
				
				if(game.state == Game.states.GO) {
					if (line.matches("^([a-h][1-8]+)+$")) {
						// metoda actualizeaza board
						game.board.opponentMove(line);
						/*
						ArrayList<Move> moves = game.board.getAllMoves();
						for(int i = 0; i < moves.size(); i++) {
							Move mv = moves.get(i);
							Utils.showTypeAndColor(mv.piece.type, mv.piece.color);
							System.out.println("MUTARE" + Utils.positionsToMove(mv.oldX, mv.oldY, mv.newX, mv.newY));
						}
						*/
						Pair<Integer, Move> p = negamaxAlphabeta(game.board, 5, Integer.MIN_VALUE, Integer.MAX_VALUE);
						Move move = p.second;
						if(move != null) {
							int oldX = move.oldX, oldY = move.oldY, newX = move.newX, newY = move.newY;
							game.board.makeMove(move);
							System.out.println(Utils.positionsToMove(oldX, oldY, newX, newY));	
						} else {
							System.out.println("resign");
						}
					}
					if (line.contains("force")) {
						game.state = Game.states.FORCE;
					}
				} else {
					if (line.matches("^([a-h][1-8]+)+$")) {
						game.board.opponentMove(line);
					}
				}
				if (line.contains("time")) {
					String[] aux = line.split(" ");
					/*340198 <first : Exception in thread "main" java.lang.NumberFormatException: For input string: "1-0"
						340199 <first : 	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
						340200 <first : 	at Main.main(Main.java:81)->linia de mai jos*/
					game.setEngineTime(Long.parseLong(aux[1]));
				}
				if (line.contains("otim")) {
					String[] aux = line.split(" ");
					game.setOpTime(Long.parseLong(aux[1]));
				}
				if (game.engineTime <= 0) {
					System.out.println("resign");
				}
				if (line.contains("quit")) {
					System.out.println("resign");
				}
			}
		}
	}
	// Am observat ca uneori se blocheaza pe tura(se duce inainte si inapoi)
	// Trebuie facute teste
	// Cum e acum, nu e prea inteligent, dar nici functia de evaluare nu e super buna
	// Mai ales ca nu avem sahul implementat
	// Dupa ce o sa fie sahul implementat, cred ca o sa mearga bine
	// TODO imbunatit/reparat negamax alphabeta
	
	// Implementarea de negamax cu alpha-beta pruning 
	// Intoarce o pereche <x, y> unde
	// x este cel mai bun scor care poate fi obtinut de jucatorul aflat la mutare,
	// pe care il recunoastem dupa culoarea care se afla in structura tablei,
	// iar y este mutarea propriu-zisa
	static Pair<Integer, Move> negamaxAlphabeta(Board init, int depth, int alfa, int beta) {
		if (init.ended() || depth == 0) {
			return new Pair<Integer, Move>(init.eval(init.engineColor), null);
		}
		Board clone = init.clone();
		ArrayList<Move> moves = clone.getAllMoves();
		Move alfaMove = null;
		if(moves.size() > 0) {
			alfaMove = moves.get(moves.size()-1);
		}
		for (Move move : moves) {
			clone.makeMove(move);
			Board opResponse = clone.clone();
			opResponse.engineColor = Utils.oppColors(clone.engineColor);
			int score = -negamaxAlphabeta(opResponse, depth - 1, -beta, -alfa).first;
			if (score > alfa) {
				alfa = score;
				alfaMove = move;
			}
			if (alfa >= beta) {
				break;
			}
		}
		if(alfaMove != null)
			return new Pair<Integer, Move>(alfa, alfaMove.cloneMove());
		else
			return new Pair<Integer, Move>(alfa, null);
	}
}