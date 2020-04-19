
public class Game {
	Board board;

	enum states {
		FORCE, GO
	};

	enum colors {
		WHITE, BLACK
	}

	states state;
	colors engineColor;
	long opTime; // timpul oponentului in microsecunde
	long engineTime; // timpul engineului nostru in microsecunde

	public Game(Board board, states state, colors engineColor, long opTime, long engineTime) {
		super();
		this.board = board;
		this.state = state;
		this.engineColor = engineColor;
		this.opTime = opTime;
		this.engineTime = engineTime;
	}

	public void setColor(colors Color) {
		engineColor = Color;
		board.engineColor = Color;
	}

	public void changeColor() {
		if (engineColor == colors.BLACK) {
			engineColor = colors.WHITE;
			board.engineColor = colors.WHITE;
		} else {
			engineColor = colors.BLACK;
			board.engineColor = colors.BLACK;
		}

	}

	public long getOpTime() {
		return opTime;
	}

	public void setOpTime(long opTime) {
		this.opTime = opTime;
	}

	public long getEngineTime() {
		return engineTime;
	}

	public void setEngineTime(long engineTime) {
		this.engineTime = engineTime;
	}

}
