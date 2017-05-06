package com.hirenpay.dto;

public class ServiceCategoryDTO
{
	private Integer serviceCategoryId;
	private String categoryName;
	private String categoryDescription;

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

}