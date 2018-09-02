package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;

public class JsonIdentifyInfoEntity
{
	@JsonIdentityInfo(
			generator = ObjectIdGenerators.PropertyGenerator.class,
			property = "id"
	)
	public static class AClass
	{
		private int id;
		private String name;

		private JsonIdentifyInfoEntity.BClass bClass;

		public AClass(int id, String name,
				JsonIdentifyInfoEntity.BClass bClass)
		{
			this.id = id;
			this.name = name;
			this.bClass = bClass;
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

		public JsonIdentifyInfoEntity.BClass getbClass()
		{
			return bClass;
		}

		public void setbClass(JsonIdentifyInfoEntity.BClass bClass)
		{
			this.bClass = bClass;
		}
	}

	@JsonIdentityInfo(
			generator = ObjectIdGenerators.PropertyGenerator.class,
			property = "id"
	)
	public static class BClass
	{
		private int id;
		private String name;

		private List<JsonIdentifyInfoEntity.AClass> aClassList;

		public BClass(int id, String name,
				List<JsonIdentifyInfoEntity.AClass> aClassList)
		{
			this.id = id;
			this.name = name;
			this.aClassList = aClassList;
		}

		public BClass(int id, String name)
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

		public List<JsonIdentifyInfoEntity.AClass> getaClassList()
		{
			return aClassList;
		}

		public void setaClassList(
				List<JsonIdentifyInfoEntity.AClass> aClassList)
		{
			this.aClassList = aClassList;
		}
	}
}
