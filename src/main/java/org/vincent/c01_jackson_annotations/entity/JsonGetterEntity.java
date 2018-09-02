package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonGetter;

public class JsonGetterEntity
{
	public static final String DISPLAY_NAME = "ANOTHER_NODE_PROPERTY";

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

	@JsonGetter(DISPLAY_NAME)
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
