package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class JsonUnwrappedEntity
{
	private int id;

	@JsonUnwrapped
	private JsonUnwrappedName name;

	public JsonUnwrappedEntity(int id, JsonUnwrappedName name)
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

	public JsonUnwrappedName getName()
	{
		return name;
	}

	public void setName(JsonUnwrappedName name)
	{
		this.name = name;
	}

	public static class JsonUnwrappedName
	{
		private String firstName;

		private String lastName;

		public JsonUnwrappedName(String firstName, String lastName)
		{
			this.firstName = firstName;
			this.lastName = lastName;
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
	}
}
