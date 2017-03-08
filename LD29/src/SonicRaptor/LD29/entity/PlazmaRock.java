package SonicRaptor.LD29.entity;

import SonicRaptor.LD29.item.Item;
import SonicRaptor.LD29.util.AssetLoader;

public class PlazmaRock extends Entity implements InteractiveEntity {

	public PlazmaRock(float x, float y) {
		super(x, y);
		this.EntityIcon = AssetLoader.objects[3][0];
	}

	private int PressCount = 0;
	private int HarvestCount = 0;
	@Override
	public void primaryAction(Entity e) 
	{	
		if(e instanceof Player)
		{
			Player player = (Player)e;
			if(player.holdingItem != null){AssetLoader.fail.play(); return;}
			
			if(PressCount >= 20){
			player.holdingItem = Item.plazma;
			AssetLoader.getItem.play();
			PressCount = 0;
			HarvestCount++;
			}else
			{
				AssetLoader.fail.play();
			}
			
			PressCount++;
			
			
		}
	}

	@Override
	public void secondaryAction(Entity e) 
	{	
	}

	private int RegenTick = 0;
	@Override
	public void updateEntity() 
	{	
		if(RegenTick == 10000)
		{
			HarvestCount--;
			RegenTick = 0;
		}
		
		if(HarvestCount >= 20)
		{
			this.remove();
		}
	}

	@Override
	public boolean isSolid() 
	{
		return true;
	}

}
