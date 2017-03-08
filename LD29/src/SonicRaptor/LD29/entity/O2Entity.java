package SonicRaptor.LD29.entity;

public abstract class O2Entity extends Entity {

	public int StartingO2 = 100;
	public int MaxO2 = 10000;
	public int O2Amount = StartingO2;
	
	public O2Entity(float x, float y)
	{
		super(x,y);
	}
	
	public void addO2(int amount)
	{
		O2Amount += amount;
		
		if(O2Amount > MaxO2){O2Amount = MaxO2;}
	}
	
	public void subO2(int amount)
	{
		O2Amount -= amount;
		
		if(O2Amount < 0){O2Amount = 0;}
	}
	
	
	@Override
	public void render()
	{
		super.render();
		renderUI();
	}
	public abstract void renderUI();
	
}
