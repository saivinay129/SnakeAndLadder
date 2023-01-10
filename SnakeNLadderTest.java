import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class SnakeNLadderTest {

	public static void main(String[] args) {
		SnakeNLadder s = new SnakeNLadder();
		s.startGame();

	}

}

class SnakeNLadder {

	final static int WINPOINT = 100;

	static Map<Integer, Integer> snake = new HashMap<Integer, Integer>();
	static Map<Integer, Integer> ladder = new HashMap<Integer, Integer>();

	{
		snake.put(99, 54);
		snake.put(70, 55);
		snake.put(52, 42);
		snake.put(25, 2);
		snake.put(95, 72);

		ladder.put(9, 25);
		ladder.put(11, 40);
		ladder.put(60, 85);
		ladder.put(46, 90);
		ladder.put(17, 69);
	}

	public int rollDice() {
		int n = 0;
		Random r = new Random();
		n = r.nextInt(7);
		return (n == 0 ? 1 : n);
	}

	public void startGame() {
		int player1 = 0, player2 = 0;
		int currentPlayer = -1;
		Scanner S = new Scanner(System.in);
		String str;
		int diceValue = 0;
		int afterGetsSixToMoveOutFirstPlayer = 0;
		int afterGetsSixToMoveOutSecondPlayer = 0;
		int cannotRollMoreThan = 0;
		int FirstPlayerSteps = 0;
		int SecondPlayerSteps = 0;

		do {
			System.out.println("------------------");
			System.out.println(currentPlayer == -1 ? "\n\nFIRST PLAYER TURN" : "\n\nSECOND PLAYER TURN");
			System.out.println("------------------");
			System.out.println("Press r to roll Dice");
			str = S.next();
			diceValue = rollDice();

			if (currentPlayer == -1) {
				System.out.println("------------------");
				System.out.println(diceValue);
				if (diceValue != 6 && afterGetsSixToMoveOutFirstPlayer == 0) {
					System.out.println("------------------");
					System.out.println("First Player :: " + player1);
					System.out.println("Second Player :: " + player2);
					System.out.println("------------------");
					System.out.println(
							"First player :: At the starting of the game, players must obtain a 6 to move out ");

				} else {
					player1 = calculatePlayerValue(player1, diceValue);
					System.out.println("------------------");
					System.out.println("First Player :: " + player1);
					System.out.println("Second Player :: " + player2);
					System.out.println("------------------");
					afterGetsSixToMoveOutFirstPlayer = 1;
					FirstPlayerSteps = 1;
					

				}
				if (isWin(player1)) {
					System.out.println("------------------");
					System.out.println("First player wins");
					System.out.println("------------------");
					System.out.println("------------------");
					return;
				}
			} else {
				System.out.println("------------------");
				System.out.println(diceValue);
				if (diceValue != 6 && afterGetsSixToMoveOutSecondPlayer == 0) {
					System.out.println("------------------");
					System.out.println("First Player :: " + player1);
					System.out.println("Second Player :: " + player2);
					System.out.println("------------------");
					System.out.println(
							"Second player :: At the starting of the game, players must obtain a 6 to move out ");

				} else {
					player2 = calculatePlayerValue(player2, diceValue);
					System.out.println("------------------");
					System.out.println("First Player :: " + player1);
					System.out.println("Second Player :: " + player2);
					System.out.println("------------------");
					afterGetsSixToMoveOutSecondPlayer = 1;
					SecondPlayerSteps = 1;

				}
				if (isWin(player2)) {
					System.out.println("------------------");
					System.out.println("Second player wins");
					System.out.println("------------------");
					System.out.println("------------------");
					return;
				}
			}
			if((player1 == player2) && SecondPlayerSteps == 1 && FirstPlayerSteps == 1){
				
				if(currentPlayer == -1){
					player2 = 0;
				}else{
					player1 = 0;
				}
				System.out.print(currentPlayer == -1 ? "\n\nFIRST PLAYER " : "\n\nSECOND PLAYER ");
				System.out.println("steps on another player, the another player should go to 0.");
			}
			if (cannotRollMoreThan == 1 && diceValue == 6) {
				System.out.println("you cannot roll more than 2 times");

			}
			if (diceValue == 6 && cannotRollMoreThan == 0) {
				System.out.println("------------------");
				System.out.print(currentPlayer == -1 ? "\n\nFIRST PLAYER  " : "\n\nSECOND PLAYER ");
				System.out.println("gets a 6.So,you  get another chance to roll the dice ");
				cannotRollMoreThan = 1;
			} else {
				currentPlayer = -currentPlayer;
				cannotRollMoreThan = 0;
			}

		} while ("r".equals(str));
	}

	public int calculatePlayerValue(int player, int diceValue) {
		player = player + diceValue;

		if (player > WINPOINT) {
			player = player - diceValue;
			return player;
		}

		if (null != snake.get(player)) {
			System.out.println("------------------");
			System.out.println("swallowed by snake");
			System.out.println("------------------");
			player = snake.get(player);
		}

		if (null != ladder.get(player)) {
			System.out.println("------------------");
			System.out.println("climb up the ladder");
			System.out.println("------------------");
			player = ladder.get(player);
		}
		return player;
	}

	public boolean isWin(int player) {
		return WINPOINT == player;
	}

}