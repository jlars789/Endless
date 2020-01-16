package main.list;

import enemyprojectile.EnemyProjectile;

public class EnemyProjectileList extends EntityList {

	public EnemyProjectileList() {
		super();
	}

	public EnemyProjectile get(int index) {
		return (EnemyProjectile)super.get(index);
	}

}
