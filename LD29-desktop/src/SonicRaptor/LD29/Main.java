package SonicRaptor.LD29;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "The Sea";
		cfg.vSyncEnabled = true;
		cfg.width = 1600;
		cfg.height = 900;
		
		new LwjglApplication(new  TheSeaGame(), cfg);
	}
}
