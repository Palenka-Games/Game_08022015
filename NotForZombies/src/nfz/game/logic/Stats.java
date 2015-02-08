package nfz.game.logic;

public class Stats {
	
	private int maxHealth;
	private int currHealth;
	private int maxEnergy;
	private int currEnergy;
	private int armor;
	private int level;
	private int strength;
	private int agility;
	private int intelligence;
	private float speed;

	public int getMaxHealth() {
		return maxHealth;
	}

	public void incMaxHealth(int amt) {
		this.maxHealth += amt;
	}

	public int getCurrHealth() {
		return currHealth;
	}

	public void setCurrHealth(int currHealth) {
		this.currHealth = currHealth;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	public int getCurrEnergy() {
		return currEnergy;
	}

	public void setCurrEnergy(int currEnergy) {
		this.currEnergy = currEnergy;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getLevel() {
		return level;
	}

	public void incLevel() {
		this.level++;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int i) {
		this.strength = i;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int i) {
		this.agility = i;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int i) {
		this.intelligence = i;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed=speed;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth=maxHealth;
	}

	public void setLevel(int i) {
		this.level = i;
	}
}
