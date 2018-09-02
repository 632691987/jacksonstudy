package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Date;

public class JsonDeserializeEntity
{
	private String name;

	@JsonDeserialize(using = CustomDateDeserializer.class)
	private Date date;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
}
