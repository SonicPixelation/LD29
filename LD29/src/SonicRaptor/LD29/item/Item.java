package SonicRaptor.LD29.item;

import SonicRaptor.LD29.screen.Screen;
import SonicRaptor.LD29.util.AssetLoader;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Item {

	public int id;
	
	public TextureRegion itemIcon;
	
	public String name;
	
	public static  Item[] items = new Item[256];
	public static Item plazma = new Item(0).setItemIcon(AssetLoader.items[0][0]).setName("Plazma");
	public static Item Air = new Item(0).setItemIcon(AssetLoader.items[1][0]).setName("Air");
	
	
	public Item(int id)
	{
		this.id = id;
		items[id] = this;
	}
	
	public Item setItemIcon(TextureRegion region)
	{
		this.itemIcon = region;
		return this;
	}
	
	
	public Item setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public void render(Screen screen,float x,float y)
	{
		screen.batch.draw(itemIcon, x, y);
	}
}
