package com.hirenpay.dao.impl;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hirenpay.dao.HomeDAO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.model.Services;
import com.hirenpay.model.Subscriber;

@Transactional
@Repository("homeDAO")
public class HomeDAOImpl implements HomeDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger log = Logger.getLogger(HomeDAOImpl.class);

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public List<Services> getServiceList() throws PersistenceException
	{
		List<Services> serviceList = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Services.class);
			cr.add(Restrictions.eq("active", true));
			cr.addOrder(Order.asc("serviceName"));

			serviceList = (List<Services>) cr.list();

		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		return serviceList;
	}

	public boolean subscribe(Subscriber subscriber) throws PersistenceException
	{
		log.debug("In Method subscribe()");

		try
		{
			getCurrentSession().save(subscriber);
			return true;
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

	public Subscriber getSubscriber(String phoneNumber)
			throws PersistenceException
	{
		log.debug("In Method getSubscriber()");

		List<Subscriber> result = Collections.EMPTY_LIST;
		Subscriber subscriber = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Subscriber.class);
			cr.add(Restrictions.eq("phoneNumber", phoneNumber));

			result = (List<Subscriber>) cr.list();

			if (null != result && result.size() > 0)
			{
				subscriber = result.get(0);
				return subscriber;
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
		return subscriber;
	}
}
