package com.jorgediaz.meetupradar.rest;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Venue{

	@SerializedName("country")
	private String country;

	@SerializedName("localized_country_name")
	private String localizedCountryName;

	@SerializedName("city")
	private String city;

	@SerializedName("address_1")
	private String address1;

	@SerializedName("name")
	private String name;

	@SerializedName("lon")
	private double lon;

	@SerializedName("id")
	private int id;

	@SerializedName("lat")
	private double lat;

	@SerializedName("repinned")
	private boolean repinned;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setLocalizedCountryName(String localizedCountryName){
		this.localizedCountryName = localizedCountryName;
	}

	public String getLocalizedCountryName(){
		return localizedCountryName;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setAddress1(String address1){
		this.address1 = address1;
	}

	public String getAddress1(){
		return address1;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLon(double lon){
		this.lon = lon;
	}

	public double getLon(){
		return lon;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	public void setRepinned(boolean repinned){
		this.repinned = repinned;
	}

	public boolean isRepinned(){
		return repinned;
	}

	@Override
 	public String toString(){
		return 
			"Venue{" + 
			"country = '" + country + '\'' + 
			",localized_country_name = '" + localizedCountryName + '\'' + 
			",city = '" + city + '\'' + 
			",address_1 = '" + address1 + '\'' + 
			",name = '" + name + '\'' + 
			",lon = '" + lon + '\'' + 
			",id = '" + id + '\'' + 
			",lat = '" + lat + '\'' + 
			",repinned = '" + repinned + '\'' + 
			"}";
		}
}