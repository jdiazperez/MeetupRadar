package com.jorgediaz.meetupradar.rest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ResultEventos{

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("results")
	private List<Event> results;

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	public void setResults(List<Event> results){
		this.results = results;
	}

	public List<Event> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"ResultEventos{" + 
			"meta = '" + meta + '\'' + 
			",results = '" + results + '\'' + 
			"}";
		}
}