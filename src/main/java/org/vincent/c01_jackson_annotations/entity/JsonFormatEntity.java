package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.Date;

public class JsonFormatEntity
{
	private String name;

	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date eventDate;

	public JsonFormatEntity(String name, Date eventDate)
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
}
