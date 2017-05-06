package com.hirenpay.dao.impl;

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

import com.hirenpay.dao.CommonDAO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.model.Otp;

@Transactional
@Repository("commonDAO")
public class CommonDAOImpl implements CommonDAO
{
	private static final Logger log = Logger.getLogger(CommonDAOImpl.class);

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

	@Override
	public boolean saveOTP(Otp otp) throws PersistenceException
	{
		log.debug("In Method saveOTP()");

		try
		{
			getCurrentSession().saveOrUpdate(otp);
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

	@Override
	public boolean confirmOTP(String phoneNumber, String otp)
			throws PersistenceException
	{
		log.debug("In Method confirmOTP()");

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Otp.class);
			cr.add(Restrictions.eq("phoneNumber", phoneNumber));
			cr.add(Restrictions.eq("otp", otp));

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
	public boolean deleteOTP(String phoneNumber) throws PersistenceException
	{
		log.debug("In Method deleteOTP()");

		try
		{
			Query query = getCurrentSession().createQuery(
					"delete Otp where phoneNumber = :phoneNumber");
			query.setParameter("phoneNumber", phoneNumber);

			int result = query.executeUpdate();

			if (result > 0)
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
}