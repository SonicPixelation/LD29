package SonicRaptor.LD29.screen;

import SonicRaptor.LD29.TheSeaGame;
import SonicRaptor.LD29.util.AssetLoader;
import SonicRaptor.LD29.util.SeaKeys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

public abstract class Screen  {

	public TheSeaGame game;
	public SpriteBatch batch;
	public SpriteBatch staticBatch;
	public OrthographicCamera camera;
	public InputMultiplexer multi;
	
	private final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public float deltaTime;
	
	public void init(TheSeaGame game)
	{
		this.game = game;
		batch = new SpriteBatch();
		staticBatch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, game.Width, game.Height);
		multi = new InputMultiplexer();
		initScreen();
	}
	
	
	public void update()
	{
		
		
		Gdx.input.setInputProcessor(multi);
		deltaTime = Gdx.graphics.getDeltaTime();
		
		camera.update();
		batch.enableBlending();
		batch.setProjectionMatrix(camera.projection);
		batch.setTransformMatrix(camera.view);
		
		Matrix4 projection = new Matrix4();
		projection.setToOrtho(0, game.Width, game.Height, 0, -1, 1);
		staticBatch.setProjectionMatrix(projection);
		
		updateScreen();
		
		SeaKeys.update();
	}
	
	public void dispose()
	{
		batch.dispose();
		staticBatch.dispose();
		remove();
	}
	
	public void setScreen(Screen newScreen)
	{
		game.setScreen(newScreen);
	}
	
	public void drawString(String string, float x, float y, float scale)
	{
		string = string.toUpperCase();
		for(int i = 0; i < string.length(); i++)
		{
			char ch = string.charAt(i);
			
			
			int xs = chars.indexOf(ch);
			if(xs >= 0){
			batch.draw(AssetLoader.Font[xs][0], x + i * ( 8 * scale), y, 4, 4, 8, 8, scale, scale, 0);
			}
			}			
			
			
		}
	
	//abstract methods
	protected abstract void initScreen();
	public abstract void render();
	protected abstract void updateScreen();
	protected abstract void remove();
	
}
