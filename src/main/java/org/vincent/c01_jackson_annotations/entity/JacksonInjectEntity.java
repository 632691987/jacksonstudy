package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JacksonInject;

public class JacksonInjectEntity
{
	public static final String JACKSON_INJECT_KEY = "Jackson_Inject_Key";

	@JacksonInject(JACKSON_INJECT_KEY)
	private int length2223333;

	private String name;

	public int getLength2223333()
	{
		return length2223333;
	}

	public void setLength2223333(int length2223333)
	{
		this.length2223333 = length2223333;
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
