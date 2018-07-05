package com.jorgediaz.meetupradar.rest;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Group{

	@SerializedName("join_mode")
	private String joinMode;

	@SerializedName("created")
	private long created;

	@SerializedName("name")
	private String name;

	@SerializedName("group_lon")
	private double groupLon;

	@SerializedName("id")
	private int id;

	@SerializedName("urlname")
	private String urlname;

	@SerializedName("group_lat")
	private double groupLat;

	@SerializedName("who")
	private String who;

	public void setJoinMode(String joinMode){
		this.joinMode = joinMode;
	}

	public String getJoinMode(){
		return joinMode;
	}

	public void setCreated(long created){
		this.created = created;
	}

	public long getCreated(){
		return created;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setGroupLon(double groupLon){
		this.groupLon = groupLon;
	}

	public double getGroupLon(){
		return groupLon;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUrlname(String urlname){
		this.urlname = urlname;
	}

	public String getUrlname(){
		return urlname;
	}

	public void setGroupLat(double groupLat){
		this.groupLat = groupLat;
	}

	public double getGroupLat(){
		return groupLat;
	}

	public void setWho(String who){
		this.who = who;
	}

	public String getWho(){
		return who;
	}

	@Override
 	public String toString(){
		return 
			"Group{" + 
			"join_mode = '" + joinMode + '\'' + 
			",created = '" + created + '\'' + 
			",name = '" + name + '\'' + 
			",group_lon = '" + groupLon + '\'' + 
			",id = '" + id + '\'' + 
			",urlname = '" + urlname + '\'' + 
			",group_lat = '" + groupLat + '\'' + 
			",who = '" + who + '\'' + 
			"}";
		}
}