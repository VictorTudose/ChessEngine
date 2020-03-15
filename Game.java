package bpod;

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
	long opTime;					//timpul oponentului in microsecunde
	long engineTime;				//timpul engineului nostru in microsecunde

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

	public Game(Board board, states state, colors engineColor, long opTime, long engineTime) {
		super();
		this.board = board;
		this.state = state;
		this.engineColor = engineColor;
		this.opTime = opTime;
		this.engineTime = engineTime;
	}
	
}
