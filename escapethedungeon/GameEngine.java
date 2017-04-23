

import java.util.*;

public class GameEngine {
	
	private boolean gameWon = false;
	private boolean gameLost = false;
	private Gun gun= null;
	private ActiveAgents player = null;
	private ActiveAgents enemy = null;
	private BoardandStats hud =null;
	private ItemDrops item = null;
	
	public void newGame() {
		gameWon = false;
		gameLost = false;
		player= null;
		hud= new BoardandStats(0,10);
		chooseGun();
		runGame();
	}
	
	public void spawnPlayer() {
		player = new ActiveAgents(gun,20);
	}
	
	public boolean spawnEnemy() {
		int spawnChance = (int)(Math.random()*100);
		if(spawnChance<15) {
			int gunNum = (int)(Math.random()*3+1);
			Gun egun = gunOptions(gunNum);
			enemy = new ActiveAgents(egun,5);
			return true;
		}
		return false;
	}
	
	public void takeTurn() {
		hud.update(player.moveForward());
	}
	
	public void runGame() {
		Scanner input = new Scanner(System.in);
		spawnPlayer();
		while(!gameWon && !gameLost) {
			hud.printBoard();
			System.out.print("\nHit any key to move.");
			String n = input.next();
			takeTurn();
			if(spawnEnemy())
				battle();
			if(hud.getPosition()==9){
				hud.printBoard();
				System.out.println("\nYou escaped the dungeon!");
				gameWon=true;
			}
			else if(player.getHealth()<=0){
				System.out.println("You Died!");
				gameLost=true;
			}
		}
	}
	
	public void battle() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nPlayer has encountered an enemy with a "+enemy.getGun().printGun()+"!");
		boolean escapes = false;
		while(!escapes && player.getHealth()>0 && enemy.getHealth()>0) {
			boolean valid = false;
			System.out.println("You have "+player.getHealth()+" health");
			System.out.println("with "+player.getGun().getAmmo()+" bullets left.");
			System.out.println("Enemy has "+enemy.getHealth()+" health.");
			while(!valid){
				System.out.println("\nDo you want to"
						+ "\n1 : Shoot with "+ player.getGun().getName()
						+ "\n2 : Try to Escape");
				int action = input.nextInt();
				if(action==1 && player.getGun().getAmmo()>0){
					int num = player.playerShoot();
					if(num!=0){
						enemy.loseHealth(num);
					}
					if(enemy.getHealth()>0) {
						int eAttack = enemy.enemyShoot();
						if(eAttack!=0){
							player.loseHealth(eAttack);
						}
					}
					valid = true;
				}
				else if(action==1 && player.getGun().getAmmo()==0)
					System.out.println("Ran out of Ammo!"
							+ "\nPlayer must escape!");
				else if(action==2){
					escapes = player.tryToEscape();
					if(escapes){
						hud.update(-1);
						valid = true;
						break;
					}
					else {
						System.out.print("Enemys Turn:");
						int eAttack = enemy.enemyShoot();
						if(eAttack!=0){
							player.loseHealth(eAttack);
						}
						valid = true;
					}
				}
				else
					System.out.println("Invalid option. Choose again.");
			}
			if(enemy.getHealth()<=0){
				item = new ItemDrops();
				System.out.println("\nEnemy is Dead!"
						+ "\nEnemy dropped a "+item.getItemName());
				System.out.print("You picked up the item and got ");
				if(item.getItemName().equals("Health Pack")) {
					System.out.println("5 health back.");
					item.healthPack(player);
				}
				else {
					System.out.println("max ammunition.");
					item.maxAmmo(player);
				}
			} 
		}
	}
	
	public Gun gunOptions(int gunNum) {
		Gun tempGun = null;
		if(gunNum==1) {
			tempGun= new Gun(0,1,75,15);
		}
		else if(gunNum==2) {
			tempGun= new Gun(1,2,65,10);
		}
		else if(gunNum==3) {
			tempGun= new Gun(2,5,40,5);
		}
		return tempGun;
	}
	
	public void chooseGun() {
		Scanner input = new Scanner(System.in);
		int num;
		
		boolean valid = false;
		while(!valid) {
			System.out.println("Choose gun:"
					+ "\n1 : Pistol (Damage = 1, Accuracy = 75%, Ammo = 15)"
					+ "\n2 : Rifle (Damage = 2, Accuracy = 65%, Ammo = 10)"
					+ "\n3 : Shotgun (Damage = 5, Accuracy = 40%, Ammo =5)");
			num = input.nextInt();
			if(num==1||num==2||num==3) {
				gun=gunOptions(num);
				valid= true;
			}
			else {
				System.out.println("Invalid option. Choose again.");
			}
			System.out.println("You chose "+gun.printGun());
			System.out.println("1 : Keep gun"
					+ "\n2 : Change gun");
			int num2;
			
			boolean valid2 = false;
			while(!valid2) {
				num2 = input.nextInt();
				if(num2==1){
					valid2 = true;
				}
				else if(num2==2) {
					valid = false;
					valid2 = true;
				}
				else {
					System.out.println("Invalid option. Choose again.");
				}
			}
		}
	}
}