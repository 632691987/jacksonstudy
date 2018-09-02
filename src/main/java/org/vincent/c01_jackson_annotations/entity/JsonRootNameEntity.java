package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(JsonRootNameEntity.ROOTNAME)
public class JsonRootNameEntity
{
	public static final String ROOTNAME = "ROOT_NAME";

	private int id;

	private String name;

	public JsonRootNameEntity()
	{
	}

	public JsonRootNameEntity(int id, String name)
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
