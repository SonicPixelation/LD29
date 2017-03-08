package SonicRaptor.LD29.screen;

import SonicRaptor.LD29.util.SeaInputProcessor;
import SonicRaptor.LD29.util.SeaKeys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class MainMenuScreen extends Screen {

	@Override
	protected void initScreen() 
	{	
		multi.addProcessor(new SeaInputProcessor());
	}

	@Override
	public void render() 
	{	
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		batch.begin();
		this.drawString("Press Enter to Play", game.Width / 2 - (8 * 4) * 9, 300, 4);
		batch.end();
	}
	@Override
	protected void updateScreen() 
	{		
		if(SeaKeys.isPressed(Keys.ENTER)){this.setScreen(new GameScreen());}
	}

	@Override
	protected void remove() 
	{	
	}

}
