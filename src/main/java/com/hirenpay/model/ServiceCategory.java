package com.hirenpay.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service_category")
public class ServiceCategory
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "service_category_id")
	private Integer serviceCategoryId;
	
	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "category_description")
	private String categoryDescription;
	
	@Column(name = "active")
	private boolean active;

	public Integer getServiceCategoryId()
	{
		return serviceCategoryId;
	}

	public void setServiceCategoryId(Integer serviceCategoryId)
	{
		this.serviceCategoryId = serviceCategoryId;
	}

	public String getCategoryName()
	{
		return categoryName;
	}

	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	public String getCategoryDescription()
	{
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription)
	{
		this.categoryDescription = categoryDescription;
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