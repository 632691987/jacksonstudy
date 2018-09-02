package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"lastName", "firstName"})
public class JsonPropertyOrderEntity
{
	public String firstName;

	public String lastName;

	public JsonPropertyOrderEntity()
	{
	}

	public JsonPropertyOrderEntity(String firstName, String lastName)
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
