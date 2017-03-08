package SonicRaptor.LD29;

import SonicRaptor.LD29.screen.MainMenuScreen;
import SonicRaptor.LD29.screen.Screen;
import SonicRaptor.LD29.util.AssetLoader;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class TheSeaGame implements ApplicationListener {
	
	public int Width, Height;
	public Screen currentScreen;
	@Override
	public void create() {		
		AssetLoader.load();
		Width = Gdx.graphics.getWidth();
		Height = Gdx.graphics.getHeight();
		this.setScreen(new MainMenuScreen());
		
		
	}

	@Override
	public void dispose() 
	{
		currentScreen.dispose();
		AssetLoader.unload();
	}

	@Override
	public void render() 
	{	
		Gdx.graphics.getGL20().glClearColor(.435f, .564f, .517f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		currentScreen.update();
		currentScreen.render();
		
		
	}
	
	public void setScreen(Screen screen)
	{
		if(currentScreen != null){currentScreen.dispose();};
		currentScreen = screen;
		currentScreen.init(this);
	}

	@Override
	public void resize(int width, int height) 
	{
		Width = Gdx.graphics.getWidth();
		Height = Gdx.graphics.getHeight();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
