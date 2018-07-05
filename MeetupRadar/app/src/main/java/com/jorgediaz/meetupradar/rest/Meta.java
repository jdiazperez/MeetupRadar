package com.jorgediaz.meetupradar.rest;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Meta{

	@SerializedName("next")
	private String next;

	@SerializedName("method")
	private String method;

	@SerializedName("total_count")
	private int totalCount;

	@SerializedName("link")
	private String link;

	@SerializedName("count")
	private int count;

	@SerializedName("description")
	private String description;

	@SerializedName("lon")
	private double lon;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("signed_url")
	private String signedUrl;

	@SerializedName("id")
	private String id;

	@SerializedName("updated")
	private long updated;

	@SerializedName("lat")
	private double lat;

	public void setNext(String next){
		this.next = next;
	}

	public String getNext(){
		return next;
	}

	public void setMethod(String method){
		this.method = method;
	}

	public String getMethod(){
		return method;
	}

	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
	}

	public int getTotalCount(){
		return totalCount;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setLon(double lon){
		this.lon = lon;
	}

	public double getLon(){
		return lon;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setSignedUrl(String signedUrl){
		this.signedUrl = signedUrl;
	}

	public String getSignedUrl(){
		return signedUrl;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUpdated(long updated){
		this.updated = updated;
	}

	public long getUpdated(){
		return updated;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"Meta{" + 
			"next = '" + next + '\'' + 
			",method = '" + method + '\'' + 
			",total_count = '" + totalCount + '\'' + 
			",link = '" + link + '\'' + 
			",count = '" + count + '\'' + 
			",description = '" + description + '\'' + 
			",lon = '" + lon + '\'' + 
			",title = '" + title + '\'' + 
			",url = '" + url + '\'' + 
			",signed_url = '" + signedUrl + '\'' + 
			",id = '" + id + '\'' + 
			",updated = '" + updated + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}