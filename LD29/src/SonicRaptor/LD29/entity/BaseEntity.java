package SonicRaptor.LD29.entity;

import SonicRaptor.LD29.util.AssetLoader;

public class BaseEntity extends HolderEntity {

	public BaseEntity(float x, float y, ElectricEntity EE, O2Entity O2) {
		super(x, y, 160, 160, EE, O2);
		
		this.EntityIcon = AssetLoader.Base;
	}

	
	@Override
	public boolean isSolid() 
	{
		return false;
	}

}
