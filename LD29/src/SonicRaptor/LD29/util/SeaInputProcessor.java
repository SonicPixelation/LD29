package SonicRaptor.LD29.util;

import com.badlogic.gdx.InputAdapter;

public class SeaInputProcessor extends InputAdapter{

	
	@Override
	public boolean keyUp(int k)
	{
		SeaKeys.setKey(k, false);
		return true;
	}


	@Override
	public boolean keyDown(int k)
	{
		SeaKeys.setKey(k, true);
		return true;
	}
}
