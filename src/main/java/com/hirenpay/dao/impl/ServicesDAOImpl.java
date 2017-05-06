package com.hirenpay.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hirenpay.dao.ServicesDAO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.model.City;
import com.hirenpay.model.ServiceCategory;
import com.hirenpay.model.Services;

@Transactional
@Repository("servicesDAO")
public class ServicesDAOImpl implements ServicesDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public boolean checkService(String serviceId) throws PersistenceException
	{
		try
		{
			Criteria cr = getCurrentSession().createCriteria(Services.class);
			cr.add(Restrictions.eq("serviceId", Integer.parseInt(serviceId)));
			cr.add(Restrictions.eq("active", true));

			List result = cr.list();
			if (result.size() > 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
	}

	@Override
	public List<Services> allActiveServices() throws PersistenceException
	{
		List<Services> result = new ArrayList<Services>();

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Services.class);
			cr.add(Restrictions.eq("active", true));

			result = (List<Services>)cr.list();
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return result;
	}

	@Override
	public List<ServiceCategory> allActiveServiceCategories()
			throws PersistenceException
	{
		List<ServiceCategory> result = new ArrayList<ServiceCategory>();

		try
		{
			Criteria cr = getCurrentSession().createCriteria(ServiceCategory.class);
			cr.add(Restrictions.eq("active", true));

			result = (List<ServiceCategory>)cr.list();
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return result;
	}

	@Override
	public List<City> allActiveCity() throws PersistenceException
	{
		List<City> result = new ArrayList<City>();

		try
		{
			Criteria cr = getCurrentSession().createCriteria(City.class);
			cr.add(Restrictions.eq("active", true));

			result = (List<City>)cr.list();
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return result;
	}
}
