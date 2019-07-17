package main;

public class StatTracker {

	public static int killCount = 0;
	public static int shotsFired = 0;
	public static int abilitiesUsed = 0;
	public static int shotsHit = 0;
	public static int roomsCleared = 0;
	public static int damageDealt = 0;
	public static int damageTaken = 0;
	
	public static void reset() {
		killCount = 0;
		shotsFired = 0;
		abilitiesUsed = 0;
		shotsHit = 0;
		roomsCleared = 0;
		damageDealt = 0;
		damageTaken = 0;
	}

}
