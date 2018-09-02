package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonSetter;

public class JsonSetterEntity
{

	private int id;

	private String name;

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

	@JsonSetter("theName")
	public void setName(String name)
	{
		this.name = name;
	}
}
