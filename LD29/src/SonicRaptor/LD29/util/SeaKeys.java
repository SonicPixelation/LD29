package SonicRaptor.LD29.util;

public class SeaKeys {

	
	private static boolean[] keys = new boolean[256];
	private static boolean[] pkeys = new boolean[256];
	
	
	
	public static void update()
	{
		for(int i = 0; i < keys.length; i++)
		{
			pkeys[i] = keys[i];
		}
	}
	
	public static void setKey(int k, boolean b)
	{
		keys[k] = b;
	}
	
	
	public static boolean isDown(int k)
	{
		return keys[k];
	}
	
	public static boolean isPressed(int k)
	{
		return(keys[k] && !pkeys[k]);
	}
}
