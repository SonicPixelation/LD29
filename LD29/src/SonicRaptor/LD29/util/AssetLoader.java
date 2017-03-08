package SonicRaptor.LD29.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TmxMapLoader.Parameters;


public class AssetLoader {

	public static TiledMap oceanFloor;
	public static TextureRegion[][] Char;
	public static TextureRegion Base;
	public static TextureRegion[][] Font;
	public static TextureRegion[][] objects;
	public static TextureRegion[][] items;
	public static TextureRegion[][] UIBar;
	public static TextureRegion water;
	
	public static Sound fail;
	public static Sound damage;
	public static Sound getItem;
	public static void load()
	{
		oceanFloor = loadMap("Maps/Ocean_Floor.tmx");
		Char = splitTexture("graphics/Char.png",32,32);
		Base = loadTexture("graphics/Base.png",160,160);
		Font = splitTexture("graphics/UI/Font.png",8,8);
		objects = splitTexture("graphics/Objects.png",32,32);
		items = splitTexture("graphics/Items.png",16,16);
		UIBar = splitTexture("graphics/UI/UI_Bars.png",80,16);
		water = loadTexture("graphics/water.png",1280,800);
		
		fail = Gdx.audio.newSound(Gdx.files.internal("Sounds/Fail.wav"));
		damage = Gdx.audio.newSound(Gdx.files.internal("Sounds/damage.wav"));
		getItem = Gdx.audio.newSound(Gdx.files.internal("Sounds/get Item.wav"));
	}

	public static void unload()
	{
		oceanFloor.dispose();
	}
	
	public static TiledMap loadMap(String filename)
	{
		Parameters prams = new Parameters();
		prams.yUp = false;
		TiledMap map = new TmxMapLoader().load(filename,prams);
		
		return map;
	}
	
	public static TextureRegion[][] splitTexture(String filename, int w, int h)
	{
		return splitTexture(filename, w, h, false);
	}
	
	private static TextureRegion[][] splitTexture (String name, int width, int height, boolean flipX) {
		Texture texture = new Texture(Gdx.files.internal(name));
		int xSlices = texture.getWidth() / width;
		int ySlices = texture.getHeight() / height;
		TextureRegion[][] res = new TextureRegion[xSlices][ySlices];
		for (int x = 0; x < xSlices; x++) {
			for (int y = 0; y < ySlices; y++) {
				res[x][y] = new TextureRegion(texture, x * width, y * height, width, height);
				res[x][y].flip(flipX, true);
			}
		}
		return res;
	}
	
	
	//loads single texture ________________________________________
	public static TextureRegion loadTexture(String filename, int w, int h)
	{
		return loadTexture(filename,w,h,false);
	}
	
	public static TextureRegion loadTexture(String filename, int w, int h, boolean flip)
	{
		Texture texture = new Texture(Gdx.files.internal(filename));
		texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		TextureRegion region = new TextureRegion(texture, w, h);
		region.flip(flip, true);
		
		return region;
	}
	
	//____________________________________________________________
	
	}
