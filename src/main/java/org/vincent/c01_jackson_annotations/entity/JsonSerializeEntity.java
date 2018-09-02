package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;

public class JsonSerializeEntity
{
	private String name;

	@JsonSerialize(using = CustomDateSerializer.class)
	private Date eventDate;

	public JsonSerializeEntity(String name, Date eventDate)
	{
		this.name = name;
		this.eventDate = eventDate;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getEventDate()
	{
		return eventDate;
	}

	public void setEventDate(Date eventDate)
	{
		this.eventDate = eventDate;
	}
}
