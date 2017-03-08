package SonicRaptor.LD29.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import SonicRaptor.LD29.util.SeaInputProcessor;
import SonicRaptor.LD29.util.SeaKeys;
import SonicRaptor.LD29.world.World;

public class GameScreen extends Screen {

	private World world;
	
	@Override
	protected void initScreen() 
	{	world = new World();
		world.init(this);
		multi.addProcessor(new SeaInputProcessor());
	}

	@Override
	public void render() 
	{
		Gdx.graphics.getGL20().glClearColor(.435f, .564f, .517f, 1f);
		world.render();
		
		
	}

	@Override
	protected void updateScreen() 
	{	
		float xt = (world.player.x - world.player.width / 2);
		float yt = (world.player.y - world.player.height / 2);
		camera.position.set(xt, yt, 0);
		
		world.update();
		
		if(world.PlayerDead)this.setScreen(new DeathScreen());
		
		if(SeaKeys.isPressed(Keys.F11)){Gdx.graphics.setDisplayMode(game.Width, game.Height, !Gdx.graphics.isFullscreen());}
		
		if(SeaKeys.isPressed(Keys.ESCAPE)){Gdx.app.exit();}
	}

	@Override
	protected void remove() 
	{	
		world.dispose();
	}

}
