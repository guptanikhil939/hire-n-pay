package com.hirenpay.dao.impl;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hirenpay.dao.RegistrationDAO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.model.Otp;
import com.hirenpay.model.User;
import com.hirenpay.service.impl.RegistrationServiceImpl;

@Transactional
@Repository("registrationDAO")
public class RegistrationDAOImpl implements RegistrationDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger log = Logger
			.getLogger(RegistrationDAOImpl.class);

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public boolean saveUser(User user) throws PersistenceException
	{
		log.debug("In Method saveUser()");

		try
		{
			getCurrentSession().saveOrUpdate(user);
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

	public boolean registeredUserForMobile(String phoneNumber)
			throws PersistenceException
	{
		log.debug("In Method registeredUserFromMobile()");

		try
		{
			Criteria cr = getCurrentSession().createCriteria(User.class);
			cr.add(Restrictions.eq("phoneNumber", phoneNumber));
			cr.add(Restrictions.eq("mobile", true));

			List result = cr.list();
			if (result.size() > 0)
			{
				return true;
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
		return false;
	}

	public boolean registeredUserForWeb(String phoneNumber)
			throws PersistenceException
	{
		log.debug("In Method registeredUserFromWeb()");

		try
		{
			Criteria cr = getCurrentSession().createCriteria(User.class);
			cr.add(Restrictions.eq("phoneNumber", phoneNumber));
			cr.add(Restrictions.eq("web", true));

			List result = cr.list();
			if (result.size() > 0)
			{
				return true;
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
		return false;
	}

	@Override
	public User getUser(String phoneNumber) throws PersistenceException
	{
		log.debug("In Method getUser()");

		List<User> result = Collections.EMPTY_LIST;
		User user = null;
		
		try
		{
			Criteria cr = getCurrentSession().createCriteria(User.class);
			cr.add(Restrictions.eq("phoneNumber", phoneNumber));

			result = (List<User>) cr.list();
			
			if (null != result && result.size() > 0)
			{
				user = result.get(0);
				return user;
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
		return user;
	}
}
