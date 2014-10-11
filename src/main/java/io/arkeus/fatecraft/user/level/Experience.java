package io.arkeus.fatecraft.user.level;

public class Experience {
	private int level;
	private double xp;
	private double xpm;

	public Experience() {
		this.level = 1;
		this.xp = 0;
		this.xpm = 10;
	}

	public boolean gainExperience(final double amount) {
		boolean leveled = false;
		xp += amount;
		while (xp >= xpm) {
			level++;
			xp -= xpm;
			xpm *= 1.1;
			leveled = true;
		}
		return leveled;
	}

	public int getLevel() {
		return level;
	}

	public int getExperience() {
		return (int) Math.floor(xp);
	}

	public int getMaxExperience() {
		return (int) Math.floor(xpm);
	}

	public double getProgress() {
		return (xp / xpm) * 100;
	}
}
