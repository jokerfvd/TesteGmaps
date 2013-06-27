package api.map;

import com.google.android.gms.maps.GoogleMap;

public class Map implements IMap{
	private GoogleMap map;
	
	public Map(){
		
	}
	
	public void setTypeNomal(){
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}
	
	public void setLocation(){
		map.setMyLocationEnabled(true);
	}
	
}
