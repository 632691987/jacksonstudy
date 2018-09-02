package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class JsonRawValueEntity
{

	private String name;

	@JsonRawValue
	private String jsonString;

	public JsonRawValueEntity(String name, String jsonString)
	{
		this.name = name;
		this.jsonString = jsonString;
	}

	public JsonRawValueEntity()
	{
	}


	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getJsonString()
	{
		return jsonString;
	}

	public void setJsonString(String jsonString)
	{
		this.jsonString = jsonString;
	}
}
