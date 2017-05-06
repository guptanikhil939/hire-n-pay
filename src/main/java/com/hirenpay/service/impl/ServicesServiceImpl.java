package com.hirenpay.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hirenpay.dao.ServicesDAO;
import com.hirenpay.dto.CityDTO;
import com.hirenpay.dto.ServiceCategoryDTO;
import com.hirenpay.dto.ServiceDTO;
import com.hirenpay.dto.mobile.ServiceListDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;
import com.hirenpay.model.City;
import com.hirenpay.model.ServiceCategory;
import com.hirenpay.model.Services;
import com.hirenpay.service.ServicesService;
import com.hirenpay.util.Constants;

@Service("servicesService")
public class ServicesServiceImpl implements ServicesService
{
	@Autowired
	ServicesDAO servicesDAO;

	public String checkService(HttpServletRequest request) throws ServiceException, PersistenceException
	{
		String result = "Service Inactive.";
		
		try
		{
			String serviceId = request.getParameter("serviceId");
	
			boolean serviceActive = servicesDAO.checkService(serviceId);
	
			if (serviceActive)
			{
				result = "success";
			}
			else
			{
				result = "Service Inactive.";
			}
		}
		catch(PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(),exception);
		}
		catch(Exception exception)
		{
			throw new ServiceException(exception.getMessage(),exception);
		}
		return result;
	}

	public String getAllActiveServices(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		String result = Constants.FAILURE;
		
		try
		{
			ServiceListDTO  serviceListDTO = new ServiceListDTO();
			List<ServiceDTO> allActiveServicesDTO = new ArrayList<ServiceDTO>();
			List<ServiceCategoryDTO> serviceCategoryListDTO = new ArrayList<ServiceCategoryDTO>();
			List<CityDTO> cityListDTO = new ArrayList<CityDTO>();
			
			List<Services> allActiveServices = new ArrayList<>();
			allActiveServices = servicesDAO.allActiveServices();
			
			for(Services services : allActiveServices)
			{
				ServiceDTO  serviceDTO = new ServiceDTO();
				//serviceDTO.setServiceCategoryId(services.getServiceCategoryId());
				serviceDTO.setServiceId(services.getServiceId());
				//serviceDTO.setServiceName(services.getServiceName());
				//serviceDTO.setServiceDescription(services.getServiceDescription());
				
				allActiveServicesDTO.add(serviceDTO);
			}
			
			serviceListDTO.setServiceDTO(allActiveServicesDTO);
			
			List<ServiceCategory> allActiveServiceCategories = new ArrayList<>();
			allActiveServiceCategories = servicesDAO.allActiveServiceCategories();
			
			for(ServiceCategory serviceCategory: allActiveServiceCategories)
			{
				ServiceCategoryDTO  serviceCategoryDTO = new ServiceCategoryDTO();
				serviceCategoryDTO.setServiceCategoryId(serviceCategory.getServiceCategoryId());
				//serviceCategoryDTO.setCategoryName(serviceCategory.getCategoryName());
				//serviceCategoryDTO.setCategoryDescription(serviceCategory.getCategoryDescription());
				
				serviceCategoryListDTO.add(serviceCategoryDTO);
			}
			
			serviceListDTO.setServiceCategoryDTO(serviceCategoryListDTO);
			
			List<City> allActiveCity = new ArrayList<>();
			allActiveCity = servicesDAO.allActiveCity();
			
			for(City city : allActiveCity)
			{
				CityDTO  cityDTO = new CityDTO();
				cityDTO.setCityId(city.getCityId());
				cityDTO.setCityName(city.getCityName());
				
				cityListDTO.add(cityDTO);
			}
			serviceListDTO.setCityDTO(cityListDTO);
			
			if (null!=allActiveServices)	
			{
				result = serviceListDTO.getJsonString();
			}	
		}
		catch(PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(),exception);
		}
		catch(Exception exception)
		{
			throw new ServiceException(exception.getMessage(),exception);
		}
		
		return result;
	}
}
