package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

public class JsonReferenceEntity
{
	public static class AClass
	{
		private int id;
		private String name;

		@JsonManagedReference
		private BClass bClass;

		public AClass(int id, String name,
				BClass bClass)
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

		public BClass getbClass()
		{
			return bClass;
		}

		public void setbClass(BClass bClass)
		{
			this.bClass = bClass;
		}
	}

	public static class BClass
	{
		private int id;
		private String name;

		@JsonBackReference
		private List<AClass> aClassList;

		public BClass(int id, String name,
				List<AClass> aClassList)
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

		public List<AClass> getaClassList()
		{
			return aClassList;
		}

		public void setaClassList(
				List<AClass> aClassList)
		{
			this.aClassList = aClassList;
		}
	}
}
