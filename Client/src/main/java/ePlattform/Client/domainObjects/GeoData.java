package ePlattform.Client.domainObjects;

public class GeoData {
	

	private int geoDataId;
	private double latitude;
	private double longitude;
	
	
	
	public GeoData( double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public int getGeoDataId() {
		return geoDataId;
	}
	public void setGeoDataId(int geoDataId) {
		this.geoDataId = geoDataId;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
