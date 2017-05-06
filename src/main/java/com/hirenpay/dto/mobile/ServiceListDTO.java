package com.hirenpay.dto.mobile;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.hirenpay.dto.CityDTO;
import com.hirenpay.dto.ServiceCategoryDTO;
import com.hirenpay.dto.ServiceDTO;

public class ServiceListDTO
{
	@SerializedName("services")
	private List<ServiceDTO> serviceDTO;
	@SerializedName("categories")
	private List<ServiceCategoryDTO> serviceCategoryDTO;
	@SerializedName("cities")
	private List<CityDTO> cityDTO;
	
	public List<ServiceDTO> getServiceDTO()
	{
		return serviceDTO;
	}

	public void setServiceDTO(List<ServiceDTO> serviceDTO)
	{
		this.serviceDTO = serviceDTO;
	}
	
	public List<ServiceCategoryDTO> getServiceCategoryDTO()
	{
		return serviceCategoryDTO;
	}

	public void setServiceCategoryDTO(List<ServiceCategoryDTO> serviceCategoryDTO)
	{
		this.serviceCategoryDTO = serviceCategoryDTO;
	}

	public List<CityDTO> getCityDTO()
	{
		return cityDTO;
	}

	public void setCityDTO(List<CityDTO> cityDTO)
	{
		this.cityDTO = cityDTO;
	}

	public String getJsonString()
	{
		return new Gson().toJson(this);
	}
}