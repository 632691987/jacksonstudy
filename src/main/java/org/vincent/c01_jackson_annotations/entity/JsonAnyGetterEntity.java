package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import java.util.HashMap;
import java.util.Map;

public class JsonAnyGetterEntity
{
	private Map<String, String> properties = new HashMap<>();

	private String lastName;

	@JsonAnyGetter
	public Map<String, String> getProperties()
	{
		return properties;
	}

	public void setProperties(Map<String, String> properties)
	{
		this.properties = properties;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
}
