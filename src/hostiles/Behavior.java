package hostiles;

/**
 * Sets the tag for behavior for hostiles. The fields are:
 * Hostiles have three basic behavior types:
 * <ul><p>
 * <li> {@code CHASING} type will chase the enemy using an intelligent pathfinding System seen in {@link Seek}
 * <li> {@code TIMID} type will chase a set distance from the player
 * <li> {@code CUSTOM} type will not chase the enemy, and by default, do nothing.
 * <li> {@code PHASE} type will ignore all obstacles
 * @author jlars
 *
 */

public enum Behavior {
	CHASING, TIMID, CUSTOM, PHASE
}
