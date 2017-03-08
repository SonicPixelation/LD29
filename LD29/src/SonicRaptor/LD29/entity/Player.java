package SonicRaptor.LD29.entity;

import java.util.List;

import SonicRaptor.LD29.item.Item;
import SonicRaptor.LD29.util.AssetLoader;
import SonicRaptor.LD29.util.SeaKeys;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Mob {

	public int imageX, imageY;
	public TiledMapTileLayer collisionLayer;
	public boolean animate;
	private int[] animsteps = {0,1,0,2};
	
	public Item holdingItem;
	
	public Player(float x, float y)
	{
		super(x,y);
		this.EntityIcon = AssetLoader.Char[0][0];
		width = 32;
		height = 32;
		
		collisionLayer = (TiledMapTileLayer) AssetLoader.oceanFloor.getLayers().get(0);
	}

	private int animcounter = 0;
	private int animDelay = 0;
	@Override
	public void updateEntity() 
	{
		
		this.EntityIcon = AssetLoader.Char[imageX][imageY];
		
		if(SeaKeys.isDown(Keys.W)){this.setYVel(-2f);}
		if(SeaKeys.isDown(Keys.A)){this.setXVel(-2f);}
		if(SeaKeys.isDown(Keys.S)){this.setYVel(+2f);}
		if(SeaKeys.isDown(Keys.D)){this.setXVel(+2f);}
		
		if(animate)
		{
			if(animDelay % 10 == 0)
			{
				imageX = animsteps[animcounter];
				animcounter++;
				if(animcounter >= 4)
				{
					animcounter = 0;
				}
			}
			animDelay++;
		}
		else
		{
			imageX = 0;
			animcounter = 0;
		}
		
		if(dir == 0){imageY = 2;}
		if(dir == 1){imageY = 1;}
		if(dir == 2){imageY = 0;}
		if(dir == 3){imageY = 3;}
		
		if(xVel > 0 || xVel < 0 || yVel > 0 || yVel < 0){animate = true;}
		else{animate = false;}
		
		
		///causes an action to happen
		if(SeaKeys.isPressed(Keys.E))
		{
			Rectangle rectangle = null;
			if(dir == 0){rectangle = new Rectangle(x,y - height, 32, 32);}
			if(dir == 1){rectangle = new Rectangle(x + width * 2,y, 32, 32);}
			if(dir == 2){rectangle = new Rectangle(x,y + height * 2, 32, 32);}
			if(dir == 3){rectangle = new Rectangle(x - width,y, 32, 32);}
			
			List<Entity> entities = world.getEntities(rectangle);
			for(Entity e: entities)
			{
				if(e != this && e instanceof InteractiveEntity)
				{
					InteractiveEntity entity = (InteractiveEntity)e;
					entity.primaryAction(this);
				}
			}
		}
		
		if(SeaKeys.isPressed(Keys.Q))
		{
			Rectangle rectangle = null;
			if(dir == 0){rectangle = new Rectangle(x,y - height, 32, 32);}
			if(dir == 1){rectangle = new Rectangle(x + width * 2,y, 32, 32);}
			if(dir == 2){rectangle = new Rectangle(x,y + height * 2, 32, 32);}
			if(dir == 3){rectangle = new Rectangle(x - width,y, 32, 32);}
			
			List<Entity> entities = world.getEntities(rectangle);
			
			for(Entity e: entities)
			{
				if(e != this && e instanceof InteractiveEntity)
				{
					InteractiveEntity entity = (InteractiveEntity)e;
					entity.secondaryAction(this);
				}
			}
		}
		
	}
	@Override
	public boolean isSolid() {
		return true;
	}

	
	@Override
	public void die()
	{
		world.PlayerDead = true;
		super.die();
	}

	

	
}
