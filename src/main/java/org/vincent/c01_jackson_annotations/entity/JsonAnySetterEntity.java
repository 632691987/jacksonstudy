package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;

public class JsonAnySetterEntity
{
	private String lastName;

	private Map<String, String> properties = new HashMap<>();

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@JsonAnySetter
	public void putEntity(String key, String value) {
		properties.put(key, value);
	}

	public Map<String, String> getProperties()
	{
		return properties;
	}

	public void setProperties(Map<String, String> properties)
	{
		this.properties = properties;
	}
}
