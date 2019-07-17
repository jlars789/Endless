package projectiles;

/**
 * Defines basic properties of the Projectile. Later fields will have all privileges as previous Fields. Fields include:
 * <ul>
 * <li> NORMAL_PROJECTILE: Basic projectile actions, no specific privileges
 * <li> BEAM_PROJECTILE: Projectile can hit the same enemies multiple times and does not lose pierce upon impact
 * <li> PERSISTENT_PROJECTILE: Projectile will not be deleted upon changing rooms.
 * <li> ORBITAL_PROJECTILE: Projectile will orbit the character. 
 * </ul>
 * @author jlars
 */

//TODO: Create type that can move through obstacles

public enum ProjectileType {
	NORMAL_PROJECTILE, BEAM_PROJECTILE, PERSISTENT_PROJECTILE, ORBITAL_PROJECTILE
}
