package com.hirenpay.dao;

import java.util.List;

import com.hirenpay.exception.PersistenceException;
import com.hirenpay.model.City;
import com.hirenpay.model.ServiceCategory;
import com.hirenpay.model.Services;

public interface ServicesDAO
{
	public boolean checkService(String serviceId) throws PersistenceException;

	public List<Services> allActiveServices() throws PersistenceException;

	public List<ServiceCategory> allActiveServiceCategories() throws PersistenceException;

	public List<City> allActiveCity() throws PersistenceException;	
}