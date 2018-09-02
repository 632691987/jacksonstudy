package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter(JsonFilterEntity.filterName)
public class JsonFilterEntity
{
	public static final String filterName = "thisIsFilterName";

	private int id;

	private String name;

	public JsonFilterEntity(int id, String name)
	{
		this.id = id;
		this.name = name;
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
