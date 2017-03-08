package SonicRaptor.LD29.world;

import java.util.ArrayList;
import java.util.List;

import SonicRaptor.LD29.entity.AirLeaf;
import SonicRaptor.LD29.entity.BaseEntity;
import SonicRaptor.LD29.entity.Entity;
import SonicRaptor.LD29.entity.Generator;
import SonicRaptor.LD29.entity.O2Tank;
import SonicRaptor.LD29.entity.Player;
import SonicRaptor.LD29.entity.PlazmaRock;
import SonicRaptor.LD29.screen.Screen;
import SonicRaptor.LD29.util.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;



public class World {

	public Screen screen;
	
	public List<Entity> entities = new ArrayList<Entity>();
	
	public float scale = 2;
	
	public Player player;
	
	public BaseEntity base;
	
	public OrthogonalTiledMapRenderer renderer;
	
	public Generator generator;
	
	public O2Tank tank;

	public boolean PlayerDead = false;
	
	public void StartupWorld()
	{
		player = new Player(32*54,32*56);
		generator = new Generator(32*52,32*52);
		tank = new O2Tank(32*54, 32*54);
		
		base = new BaseEntity(32*50,32*50, generator,tank);
		

		this.addEntity(base);
		this.addEntity(tank);
		this.addEntity(generator);
		
		
		for(int i = 0; i < 10; i++)
		{
			float x;
			float y;
			while(true){
			x = MathUtils.random(64, 98 * 32);
			y = MathUtils.random(64, 98 * 32);
			Rectangle test = new Rectangle(x,y,32,32);
			List<Entity> tmp = getEntities(test);
			if(tmp.size() <= 0){break;}
			}
			this.addEntity(new PlazmaRock(x,y));
		}
		
		for(int j = 0; j < 10; j++)
		{
			float x;
			float y;
			while(true){
			x = MathUtils.random(64, 98 * 32);
			y = MathUtils.random(64, 98 * 32);
			Rectangle test = new Rectangle(x,y,32,32);
			List<Entity> tmp = getEntities(test);
			if(tmp.size() <= 0){break;}
			}
			this.addEntity(new AirLeaf(x,y));
		}
		
		this.addEntity(player);
		
	}
	
	public void init(Screen screen)
	{
		this.screen = screen;
		renderer = new OrthogonalTiledMapRenderer(AssetLoader.oceanFloor,scale);
		
		
		
		StartupWorld();
	}
	private float xScroll = 0;
	private float yScroll = 0;
	public void render()
	{
		
		renderer.render();
		
		float x = screen.camera.position.x - screen.game.Width / 2;
		float y = screen.camera.position.y - screen.game.Height / 2;
		float width = screen.game.Width;
		float height = screen.game.Height;
		
		List<Entity> visableEntities = getEntities(new Rectangle(x,y,width, height));
	    
		screen.batch.begin();
		for(Entity e: visableEntities)
		{
			
			e.render();
			 
		}
		screen.batch.end();
	
		if(xScroll > 1){xScroll = 0;}
		if(yScroll > 1){yScroll = 0;}
		
		if(xScroll < -1){xScroll = 0;}
		if(yScroll < -1){yScroll = 0;}
		
		if(player.xVel > 0){xScroll += screen.deltaTime / 6;}
		if(player.xVel < 0){xScroll -= screen.deltaTime / 6;}
		
		if(player.yVel > 0){yScroll -= screen.deltaTime / 5;}
		if(player.yVel < 0){yScroll += screen.deltaTime / 5;}
		
		
		screen.staticBatch.begin();
		screen.staticBatch.draw(AssetLoader.water,  0, 0, 0, 0, screen.game.Width, screen.game.Height, 1, 1, 0);
		AssetLoader.water.setU(xScroll);
		AssetLoader.water.setU2(xScroll + 1);
		AssetLoader.water.setV(yScroll);
		AssetLoader.water.setV2(yScroll + 1);
		 
		screen.staticBatch.end();
		
		screen.batch.begin();
		this.renderPlayerUI();
		screen.batch.end();
	}
	
	
	public void update()
	{	
		renderer.setView(screen.camera);
		
		for(Entity e: entities)
		{
			if(e.removed)
			{
				entities.remove(e);
				return;
			}
			
			e.update();
		}
			
		
	}
	

	
	public void addEntity(Entity e)
	{
		e.init(this);
		entities.add(e);
	}
	
	
	public void dispose()
	{
		renderer.dispose();
	}
	
	
	public void renderPlayerUI()
	{
		
		screen.batch.draw(AssetLoader.UIBar[0][1], screen.camera.position.x - 16 * 2, screen.camera.position.y - 32);
		screen.batch.draw(AssetLoader.UIBar[0][0], screen.camera.position.x + 16 * 4, screen.camera.position.y - 32);
		
		screen.drawString(""+player.O2Amount, screen.camera.position.x - 16, screen.camera.position.y, 2);
		screen.drawString(""+player.Health, screen.camera.position.x + 16 * 4, screen.camera.position.y, 2f);
		
		

		
		
		if(player.holdingItem != null){
		player.holdingItem.render(screen, screen.camera.position.x, screen.camera.position.y + 16);
		}
	}
		//setters
		public void setScale(float s)
		{
			this.scale = s;
		}
	
		
	//getters
	public  List<Entity> getEntities(Rectangle rect)
	{
		List<Entity> result = new ArrayList<Entity>();
		
		for(Entity e: entities)
		{
			if(rect.overlaps(e.getCollisionBox(0,0)))
			{
				result.add(e);
			}
		}
		return result;
	}
	
	
	public void setupO2Field(float x, float y)
	{
		
	}
	
	public void setupPlazmaField(float x, float y)
	{
		
	}
	
}
