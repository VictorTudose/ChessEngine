package bpod;


public class Utils {

	public static long fiveMinutes=300000000;

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

}