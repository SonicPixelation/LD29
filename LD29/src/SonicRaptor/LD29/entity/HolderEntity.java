package SonicRaptor.LD29.entity;

public abstract class HolderEntity extends Entity {

	public ElectricEntity EE;
	public O2Entity O2E;
	
	public HolderEntity(float x, float y, int w, int h, ElectricEntity EE, O2Entity O2E) {
		super(x, y);
		this.width = w;
		this.height = h;
		
		this.EE = EE;
		this.O2E = O2E;
	}

	
	private int O2SubTime = 0;
	private int PowerSubTime = 0;
	
	@Override
	public void updateEntity()
	{
		if(EE.Power <= 0 )
		{
			if(O2SubTime % 100 == 0)O2E.subO2(1);
			
			O2SubTime++;
		}
		else
		{
			O2SubTime = 0;
		}
		
		if(EE.Power > 0)
		{
			if(PowerSubTime % 200 == 0){EE.subPower(1);}
			PowerSubTime++;
		}
	}
}
