package SonicRaptor.LD29.entity;

public abstract class Mob extends Entity {

	public int dir;
	
	public int MaxHealth = 100;
	
	public int Health = MaxHealth;
	
	public int MaxO2 = 100;
	
	public int O2Amount = MaxO2;

	public boolean freshAir = false;
	
	
	public Mob(float x , float y)
	{
		super(x,y);
	}
	
	public void die()
	{
		remove();
	}
	
	private int subCounter;
	private int hurtCounter;
	public void update()
	{
		setXVel(0);
		setYVel(0);
		
		this.scale = world.scale;
		
		freshAir = false;
		
		updateEntity(); 
		
		if(xVel > 0){dir = 1;}
		if(yVel < 0){dir = 0;}
		
		if(xVel < 0){dir = 3;}
		if(yVel > 0){dir = 2;}
		
		tryMove(xVel,yVel);
		
		
		if(O2Amount <= 0 && !freshAir)
		{
			if(hurtCounter % 10 == 0){hurt(1);}
			hurtCounter++;
		}else
		{
			hurtCounter = 0;
		}
		
		if(!freshAir)
		{
			if(subCounter % 100 == 0){subO2(1);}
			subCounter++;
		}else
		{
			subCounter = 0;
		}
		
		
	}
	
	@Override
	public void collide(Entity e, float xt, float yt) 
	{
		
		if(e instanceof HolderEntity){
			
			HolderEntity entity = (HolderEntity)e;
			if(entity.O2E.O2Amount > 0)freshAir = true;
		
		}
	}
	
	public void addO2(int amount)
	{
		O2Amount += amount;
		
		if(O2Amount > MaxO2)
		{
			O2Amount = MaxO2;
		}
	}
	
	public void subO2(int amount)
	{
		O2Amount -= amount;
		if(O2Amount < 0)
		{
			 O2Amount = 0;
		}
	}
	
	public void hurt(int amount)
	{
		Health -= amount;
		if(Health <= 0 )
		{
			die();
		}
	}
	
	public void heal(int amount)
	{
		Health += amount;
		if(Health > MaxHealth)
		{
			Health = MaxHealth;
		}
	}
}
