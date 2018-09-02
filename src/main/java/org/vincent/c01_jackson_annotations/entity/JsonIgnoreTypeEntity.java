package org.vincent.c01_jackson_annotations.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

public class JsonIgnoreTypeEntity
{
	private int id;

	private String name;

	private BillingAddress billingAddress;

	private ShippingAddress shippingAddress;

	@JsonIgnoreType
	public static class BillingAddress
	{

		public BillingAddress(String street1, String street2)
		{
			this.street1 = street1;
			this.street2 = street2;
		}

		private String street1;

		private String street2;

		public String getStreet1()
		{
			return street1;
		}

		public void setStreet1(String street1)
		{
			this.street1 = street1;
		}

		public String getStreet2()
		{
			return street2;
		}

		public void setStreet2(String street2)
		{
			this.street2 = street2;
		}
	}

	public static class ShippingAddress {

		private String street3;

		private String street4;

		public ShippingAddress(String street3, String street4)
		{
			this.street3 = street3;
			this.street4 = street4;
		}

		public String getStreet3()
		{
			return street3;
		}

		public void setStreet3(String street3)
		{
			this.street3 = street3;
		}

		public String getStreet4()
		{
			return street4;
		}

		public void setStreet4(String street4)
		{
			this.street4 = street4;
		}
	}

	public JsonIgnoreTypeEntity(int id, String name, BillingAddress billingAddress, ShippingAddress shippingAddress)
	{
		this.id = id;
		this.name = name;
		this.billingAddress = billingAddress;
		this.shippingAddress = shippingAddress;
	}

	public BillingAddress getBillingAddress()
	{
		return billingAddress;
	}

	public void setBillingAddress(
			BillingAddress billingAddress)
	{
		this.billingAddress = billingAddress;
	}

	public ShippingAddress getShippingAddress()
	{
		return shippingAddress;
	}

	public void setShippingAddress(
			ShippingAddress shippingAddress)
	{
		this.shippingAddress = shippingAddress;
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
