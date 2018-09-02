package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonPropertyEntity
{
	private int id;

	private String name;

	public JsonPropertyEntity(@JsonProperty("id") int id, @JsonProperty("theName") String name)
	{
		this.id = id;
		this.name = name;
	}

	public JsonPropertyEntity()
	{
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
