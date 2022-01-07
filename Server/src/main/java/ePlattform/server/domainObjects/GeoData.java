package ePlattform.server.domainObjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "geoData")
public class GeoData {
	
	@Id @GeneratedValue
	private int geoDataId;
	private double latitude;
	private double longitude;
	
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
