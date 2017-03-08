package SonicRaptor.LD29.entity;

public abstract class ElectricEntity extends Entity {

	public int StartingPower = 100;
	public int MaxPower = 10000;
	public int Power = StartingPower;
	public ElectricEntity(float x, float y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
		
	}
	
	public void addPower(int i)
	{
		Power += i;
		if(Power > MaxPower)
		{
			Power = MaxPower;
		}
	}
	
	public void subPower(int i)
	{
		Power -= i;
		
		if(Power < 0)
		{
			Power = 0;
		}
	}
	
	@Override
	public void render()
	{
		super.render();
		renderUI();
	}
	public abstract void renderUI();

}
