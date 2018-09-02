package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class JsonAutoDetectEntity
{
	private int id;

	private String name;

	public JsonAutoDetectEntity(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
}
