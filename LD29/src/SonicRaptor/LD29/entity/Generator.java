package SonicRaptor.LD29.entity;

import SonicRaptor.LD29.item.Item;
import SonicRaptor.LD29.util.AssetLoader;

public class Generator extends ElectricEntity implements InteractiveEntity {

	public Generator(float x, float y) 
	{
		super(x, y,32,32);
		this.EntityIcon = AssetLoader.objects[0][0];
	}

	@Override
	public void primaryAction(Entity e) 
	{	
		Player player = (Player)e;
		if(player.holdingItem != null)
		if(player.holdingItem.name == Item.plazma.name)
		{
			AssetLoader.getItem.play();
			this.addPower(10);
			player.holdingItem = null;
		}else{
			AssetLoader.fail.play();
		}
	}
	
	@Override
	public void secondaryAction(Entity e) 
	{	
	}

	@Override
	public void updateEntity() 
	{		
	}

	@Override
	public boolean isSolid() 
	{
		return true;
	}

	@Override
	public void renderUI() 
	{
		world.screen.drawString(""+this.Power, x+16, y+8, 1);
	}

}
