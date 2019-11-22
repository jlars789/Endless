package statuseffect;

import sprite.HeavySprite;

public class AnimLoader {
	
	protected static HeavySprite loadSEAnim(String name, int frames) {
		return new HeavySprite("imgs/statuseffects/" + name + ".png", 8, 8, 1, frames, 16, true);
	}

}
