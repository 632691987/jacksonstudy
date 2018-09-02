package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JsonValueEntity
{
	TYPE1(1, "Type A"), TYPE2(2, "Type B");

	private Integer id;
	private String name;

	JsonValueEntity(Integer id, String name)
	{
		this.id = id;
		this.name = name;
	}

	@JsonValue
	@Override
	public String toString()
	{
		return "JsonValueEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
