

public class Gun {
	
	private int damage;
	private int accuracy;
	private int name;
	private int ammo;
	
	public Gun(int name,int damage,int accuracy,int ammo) {
		this.damage=damage;
		this.accuracy=accuracy;
		this.name=name;
		this.ammo=ammo;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getAmmo() {
		return ammo;
	}
	
	public int getAccuracy() {
		return accuracy;
	}
	
	public String getName() {
		String gunName = null;
		if(name==0)
			gunName = "Pistol";
		if(name==1)
			gunName = "Rifle";
		if(name==2)
			gunName = "Shotgun";
		return gunName;
	}
	public String printGun() {
		return getName()+" (Damage : "+getDamage()+", Accuracy : "+getAccuracy()+"%, Ammo : "+getAmmo()+")";
	}

	public void loseAmmo() {
		ammo-=1;
	}
	public void maxAmmo(Gun gun) {
		if(gun.getName().equals("Pistol"))
			ammo=15;
		else if(gun.getName().equals("Rifle"))
			ammo=10;
		else
			ammo=5;
	}
}