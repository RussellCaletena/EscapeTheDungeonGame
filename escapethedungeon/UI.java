

import java.util.*;

public class UI{
	
	private GameEngine game = null;
	
	public UI(GameEngine game) {
		this.game=game;
	}
	
	public void startGame() {
		Scanner input = new Scanner(System.in);
		printWelcomeMessage();
		boolean quit = false;
		int num ;
		while(!quit) {
			System.out.println("Choose an option");
			System.out.println("1 : Start Game");
			System.out.println("2 : Quit");
			num = input.nextInt();
			if(num==1) {
				game();
				break;
			}
			else if(num==2) {
				quit = true;
				break;
			}
			else {
				System.out.println("Invalid option. Choose again.");
				break;
			}
		}
		System.out.print("Game Over!");
	}

	private void game() {
		System.out.println("New game started.");
		game.newGame();
	}

	private void printWelcomeMessage() {
		// TODO Auto-generated method stub
		System.out.println("Welcome to Escape the Dungeon!\n");
	}
}