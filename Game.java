package bpod;

public class Game {
	Board board;

	enum states {
		FORCE, GO
	};

	enum engineColors {
		WHITE, BLACK
	}

	states state;
	engineColors engineColor;

	public Game(Board board, states state, engineColors engineColor) {
		super();
		this.board = board;
		this.state = state;
		this.engineColor = engineColor;
	}
	
}
