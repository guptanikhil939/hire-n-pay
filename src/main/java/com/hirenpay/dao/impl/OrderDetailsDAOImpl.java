package com.hirenpay.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hirenpay.dao.OrderDetailsDAO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.model.OrderDetails;
import com.hirenpay.model.Services;
import com.hirenpay.util.Constants;

@Repository("orderDetailsDAO")
public class OrderDetailsDAOImpl implements OrderDetailsDAO
{
	private static final Logger log = Logger.getLogger(OrderDetailsDAOImpl.class);
	
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

	public Integer saveOrder(OrderDetails orderDetails)
			throws PersistenceException
	{
		log.debug("In Method saveOrder()");
		
		try
		{
			getCurrentSession().save(orderDetails);
			return orderDetails.getOrderId();
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

	public String getServiceName(Integer serviceId) throws PersistenceException
	{
		log.debug("In Method getServiceName()");

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Services.class);
			cr.add(Restrictions.eq("serviceId", serviceId));

			List<Services> result = (List<Services>)cr.list();
			if (null!=result && result.size() > 0)
			{
				for(Services service : result)
				{
					return service.getServiceName();
				}
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
		return Constants.EMPTY_STRING;
	}
}
