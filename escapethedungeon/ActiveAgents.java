
public class ActiveAgents {
	
	private Gun gun= null;
	private int health;
	
	public ActiveAgents(Gun gun,int health) {
		this.gun=gun;
		this.health=health;
	}
	
	public int getHealth() {
		return health;
	}
	
	public Gun getGun() {
		return gun;
	}
	
	public int moveForward() {
		return 1;
	}
	
	public boolean tryToEscape() {
		int num = (int)(Math.random()*100);
		if(num<10) {
			System.out.println("Escaped! (Moves 1 step back)");
			return true;
		}
		else {
			System.out.println("Escape fails, Enemy attacks");
			return false;
		}
	}
	
	public int playerShoot() {
		if((int)(Math.random()*100)<getGun().getAccuracy()){
			System.out.println("\nPlayer shoots and it hits!");
			System.out.println("Enemy loses "+ getGun().getDamage()+ " amount of health");
			gun.loseAmmo();
			return getGun().getDamage();
		}
		System.out.println("\nPlayer shoots and misses.");
		gun.loseAmmo();
		return 0;
	}
	
	public int enemyShoot() {
		if((int)(Math.random()*100)<getGun().getAccuracy()){
			System.out.println("Enemy shoots and it hits!");
			System.out.println("Player loses "+ getGun().getDamage()+ " amount of health");
			gun.loseAmmo();
			return getGun().getDamage();
		}
		System.out.println("Enemy shoots and misses.");
		return 0;
	}
	
	public void loseHealth(int num) {
		health -=num;
	}
	
	public void addHealth(int num) {
		health += num;
		if(health>20)
			health -= health -20;
	}
}