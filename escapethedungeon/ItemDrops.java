

public class ItemDrops{
	
	private String item = null;
	
	public ItemDrops(){
		int chance= (int)(Math.random()*100);
		if(chance<30) {
			item= "Health Pack";
			}
		else
			item = "maxAmmo";
	}

	public String getItemName() {
		return item;
	}
	
	public void healthPack(ActiveAgents player) {
		player.addHealth(5);
	}
	
	public void maxAmmo(ActiveAgents player) {
		player.getGun().maxAmmo(player.getGun());
	}
}