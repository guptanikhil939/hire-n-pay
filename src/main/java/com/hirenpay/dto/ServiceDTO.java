package com.hirenpay.dto;

public class ServiceDTO
{
	private Integer serviceId;
	private Integer serviceCategoryId;
	private String serviceName;
	private String serviceDescription;

	public Integer getServiceId()
	{
		return serviceId;
	}

	public void setServiceId(Integer serviceId)
	{
		this.serviceId = serviceId;
	}

	public Integer getServiceCategoryId()
	{
		return serviceCategoryId;
	}

	public void setServiceCategoryId(Integer serviceCategoryId)
	{
		this.serviceCategoryId = serviceCategoryId;
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	public String getServiceDescription()
	{
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription)
	{
		this.serviceDescription = serviceDescription;
	}

}
