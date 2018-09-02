package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonView;

public class JsonViewEntity
{
	@JsonView(InternalViews.Public1.class)
	private int id;

	@JsonView(InternalViews.Public1.class)
	private String firstName;

	@JsonView(InternalViews.Internal1.class)
	private String lastName;

	@JsonView(InternalViews.Internal2.class)
	private String property2;

	public JsonViewEntity(int id, String firstName, String lastName, String property2)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.property2 = property2;
	}

	public String getProperty2()
	{
		return property2;
	}

	public void setProperty2(String property2)
	{
		this.property2 = property2;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public static class InternalViews
	{
		public static class Public1{}

		public static class Internal1 extends Public1{}

		public static class Internal2{}
	}
}
