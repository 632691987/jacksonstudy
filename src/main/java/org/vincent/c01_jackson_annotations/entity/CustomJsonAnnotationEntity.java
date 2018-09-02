package org.vincent.c01_jackson_annotations.entity;

import org.vincent.c01_jackson_annotations.entity.special.CustomAnnotation;

@CustomAnnotation
public class CustomJsonAnnotationEntity
{
	private int id;

	private String name;

	public CustomJsonAnnotationEntity(int id, String name)
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
