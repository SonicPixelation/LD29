package SonicRaptor.LD29.screen;

import SonicRaptor.LD29.util.SeaInputProcessor;
import SonicRaptor.LD29.util.SeaKeys;

import com.badlogic.gdx.Input.Keys;

public class DeathScreen extends Screen {

	@Override
	protected void initScreen() 
	{
		multi.addProcessor(new SeaInputProcessor());
	}

	private int WaitCount = 0;
	@Override
	public void render() 
	{
		batch.begin();
		this.drawString("You Died", game.Width / 2 - 8 * 12, game.Height / 2, 4);
		
		if(WaitCount >= 100)
		{
			this.drawString("Press enter to continue", game.Width / 2 - 8 * 45, game.Height / 2 + 16*4, 4);	
		}
		batch.end();
		
		WaitCount++;
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
