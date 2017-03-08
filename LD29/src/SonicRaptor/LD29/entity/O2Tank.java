package SonicRaptor.LD29.entity;

import SonicRaptor.LD29.item.Item;
import SonicRaptor.LD29.util.AssetLoader;

public class O2Tank extends O2Entity implements InteractiveEntity {

	public O2Tank(float x, float y) {
		super(x, y);
		this.EntityIcon = AssetLoader.objects[1][0];
	}

	@Override
	public void primaryAction(Entity e) 
	{		
		Player player = (Player)e;
		if(player.holdingItem != null)
		if(player.holdingItem.name == Item.Air.name)
		{
			AssetLoader.getItem.play();
			this.addO2(10);
			player.holdingItem = null;
		}else
		{
			AssetLoader.fail.play();
		}
	}
	
	@Override
	public void secondaryAction(Entity e) 
	{	
		if(this.O2Amount > 0)
		{
			AssetLoader.getItem.play();
			Player player = (Player)e;
			int count = Math.abs(player.O2Amount - player.MaxO2);
			this.subO2(count);
			player.addO2(count);
		}
		AssetLoader.fail.play();
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
		world.screen.drawString(""+this.O2Amount, x+16, y+16, 1);
	}


}
