package com.hirenpay.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "services")
public class Services
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "service_id")
	private Integer serviceId;
	
	@Column(name = "service_category_id")
	private Integer serviceCategoryId;
	
	@Column(name = "service_name")
	private String serviceName;

	@Column(name = "service_description")
	private String serviceDescription;
	
	@Column(name = "active")
	private boolean active;

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

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}
}