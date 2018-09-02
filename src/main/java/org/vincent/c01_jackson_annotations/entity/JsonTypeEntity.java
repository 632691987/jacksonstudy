package org.vincent.c01_jackson_annotations.entity;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;

public class JsonTypeEntity
{
	private Animal animal;

	public JsonTypeEntity()
	{
	}

	@JsonTypeInfo(use= Id.NAME, include = As.PROPERTY, property = "type222")
	@JsonSubTypes({
			@JsonSubTypes.Type(value=Dog.class),
			@JsonSubTypes.Type(value=Cat.class),
			//@JsonSubTypes.Type(value=Dog.class, name="dog1"),
			//@JsonSubTypes.Type(value=Cat.class, name="cat1"),
	})
	public abstract static class Animal
	{
		private String name;

		public Animal(String name)
		{
			this.name = name;
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

	public Animal getAnimal()
	{
		return animal;
	}

	public void setAnimal(Animal animal)
	{
		this.animal = animal;
	}

	public JsonTypeEntity(Animal animal)
	{
		this.animal = animal;
	}

	@JsonTypeName("dog2")
	public static class Dog extends Animal
	{
		private double barkValue;

		public Dog(double barkValue)
		{
			super("");
			this.barkValue = barkValue;
		}

		public Dog(String name)
		{
			super(name);
		}

		public Dog()
		{
			super("");
		}

		public double getBarkValue()
		{
			return barkValue;
		}

		public void setBarkValue(double barkValue)
		{
			this.barkValue = barkValue;
		}
	}

	@JsonTypeName("cat2")
	public static class Cat extends Animal
	{
		private boolean likeCream;

		private int lives;

		public Cat(boolean likeCream, int lives)
		{
			super("");
			this.likeCream = likeCream;
			this.lives = lives;
		}

		public Cat(String name)
		{
			super(name);
		}

		public Cat()
		{
			super("");
		}

		public boolean isLikeCream()
		{
			return likeCream;
		}

		public void setLikeCream(boolean likeCream)
		{
			this.likeCream = likeCream;
		}

		public int getLives()
		{
			return lives;
		}

		public void setLives(int lives)
		{
			this.lives = lives;
		}
	}
}
