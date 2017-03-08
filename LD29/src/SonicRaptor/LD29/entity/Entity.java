package SonicRaptor.LD29.entity;

import java.util.List;

import SonicRaptor.LD29.world.World;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {

	public float x, y;
	
	public float scale = 1;
	
	public int width,height;
	
	public float xVel, yVel;
	
	public boolean removed = false;
	
	public TextureRegion EntityIcon = null;
	
	public World world;
	
	
	public Entity(float x, float y)
	{
		this.x = x;
		this.y = y;
		
		width = 32;
		height = 32;
	}
	
	
	public void tryMove(float xt, float yt)
	{
		if(xt != 0 && yt != 0)
		{
			xt = xt / 1.5f;
			yt = yt / 1.5f;
			tryMove(xt,0);
			tryMove(0,yt);
			return;
		}
		
		boolean mayPass = true;
		
		List<Entity> entities = world.getEntities(this.getCollisionBox(xt, yt));
		
		for(Entity e: entities)
		{
			if(e != this)
			{
				HandleCollision(e, xt,yt);
				if(this.isSolid() && e.isSolid())
				{
					mayPass = false;
				}
			}
		}
		
		if(leaving(xt,yt))
		{
			mayPass = false;
		}
		
		
		
		if(mayPass)
		{
			x += (xt);
			y += (yt);
		}
	}

	
	public void HandleCollision(Entity e, float xt, float yt)
	{
		this.collide(e, xt, yt);
		e.collide(this, -xt, -yt);
	}
	
	public void init(World world)
	{
		removed = false;
		this.world = world;
	}
	
	public void render()
	{
		world.screen.batch.draw(EntityIcon, x, y, 0, 0, width, height, scale, scale, 0);
	}
	
	public void update()
	{
		setXVel(0);
		setYVel(0);
		scale = world.scale;
		updateEntity();
		tryMove(xVel,yVel);
		
		
	}
	
	public void remove()
	{
		removed = true;
	}
	
	public boolean leaving(float xt, float yt)
	{
		if(x + xt < 32 * scale || x + xt + width > 16 *  99 * scale || y + yt < 32 * scale ||  y + yt + height > 16 * 99 * scale)
			return true;
		
		return false;
		
	}
	
	public Rectangle getCollisionBox(float xt, float yt)
	{
		return new Rectangle((x + xt),(y + yt),(width) * world.scale,(height)  * world.scale);
	}
	
	public void collide(Entity e, float xt, float yt){}
	
	//abstract methods
	
	public abstract void updateEntity();
	public abstract boolean isSolid();
	
	
	//setters
	public void setXVel(float x)
	{
		this.xVel = x;
	}
	
	public void setYVel(float y)
	{
		this.yVel = y;
	}
}
