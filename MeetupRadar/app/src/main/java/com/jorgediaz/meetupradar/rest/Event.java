package com.jorgediaz.meetupradar.rest;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Event {

	@SerializedName("utc_offset")
	private int utcOffset;

	@SerializedName("venue")
	private Venue venue;

	@SerializedName("headcount")
	private int headcount;

	@SerializedName("distance")
	private double distance;

	@SerializedName("visibility")
	private String visibility;

	@SerializedName("waitlist_count")
	private int waitlistCount;

	@SerializedName("created")
	private long created;

	@SerializedName("maybe_rsvp_count")
	private int maybeRsvpCount;

	@SerializedName("description")
	private String description;

	@SerializedName("how_to_find_us")
	private String howToFindUs;

	@SerializedName("event_url")
	private String eventUrl;

	@SerializedName("yes_rsvp_count")
	private int yesRsvpCount;

	@SerializedName("duration")
	private int duration;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("photo_url")
	private String photoUrl;

	@SerializedName("time")
	private long time;

	@SerializedName("updated")
	private long updated;

	@SerializedName("group")
	private Group group;

	@SerializedName("status")
	private String status;

	public void setUtcOffset(int utcOffset){
		this.utcOffset = utcOffset;
	}

	public int getUtcOffset(){
		return utcOffset;
	}

	public void setVenue(Venue venue){
		this.venue = venue;
	}

	public Venue getVenue(){
		return venue;
	}

	public void setHeadcount(int headcount){
		this.headcount = headcount;
	}

	public int getHeadcount(){
		return headcount;
	}

	public void setDistance(double distance){
		this.distance = distance;
	}

	public double getDistance(){
		return distance;
	}

	public void setVisibility(String visibility){
		this.visibility = visibility;
	}

	public String getVisibility(){
		return visibility;
	}

	public void setWaitlistCount(int waitlistCount){
		this.waitlistCount = waitlistCount;
	}

	public int getWaitlistCount(){
		return waitlistCount;
	}

	public void setCreated(long created){
		this.created = created;
	}

	public long getCreated(){
		return created;
	}

	public void setMaybeRsvpCount(int maybeRsvpCount){
		this.maybeRsvpCount = maybeRsvpCount;
	}

	public int getMaybeRsvpCount(){
		return maybeRsvpCount;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setHowToFindUs(String howToFindUs){
		this.howToFindUs = howToFindUs;
	}

	public String getHowToFindUs(){
		return howToFindUs;
	}

	public void setEventUrl(String eventUrl){
		this.eventUrl = eventUrl;
	}

	public String getEventUrl(){
		return eventUrl;
	}

	public void setYesRsvpCount(int yesRsvpCount){
		this.yesRsvpCount = yesRsvpCount;
	}

	public int getYesRsvpCount(){
		return yesRsvpCount;
	}

	public void setDuration(int duration){
		this.duration = duration;
	}

	public int getDuration(){
		return duration;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPhotoUrl(String photoUrl){
		this.photoUrl = photoUrl;
	}

	public String getPhotoUrl(){
		return photoUrl;
	}

	public void setTime(long time){
		this.time = time;
	}

	public long getTime(){
		return time;
	}

	public void setUpdated(long updated){
		this.updated = updated;
	}

	public long getUpdated(){
		return updated;
	}

	public void setGroup(Group group){
		this.group = group;
	}

	public Group getGroup(){
		return group;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Event{" +
			"utc_offset = '" + utcOffset + '\'' + 
			",venue = '" + venue + '\'' + 
			",headcount = '" + headcount + '\'' + 
			",distance = '" + distance + '\'' + 
			",visibility = '" + visibility + '\'' + 
			",waitlist_count = '" + waitlistCount + '\'' + 
			",created = '" + created + '\'' + 
			",maybe_rsvp_count = '" + maybeRsvpCount + '\'' + 
			",description = '" + description + '\'' + 
			",how_to_find_us = '" + howToFindUs + '\'' + 
			",event_url = '" + eventUrl + '\'' + 
			",yes_rsvp_count = '" + yesRsvpCount + '\'' + 
			",duration = '" + duration + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",photo_url = '" + photoUrl + '\'' + 
			",time = '" + time + '\'' + 
			",updated = '" + updated + '\'' + 
			",group = '" + group + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}