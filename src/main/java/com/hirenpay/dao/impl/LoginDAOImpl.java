package com.hirenpay.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hirenpay.dao.LoginDAO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.model.User;

@Transactional
@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO
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

	public User loginUser(String phoneNumber, String password) throws PersistenceException
	{
		User user = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(User.class);
			cr.add(Restrictions.eq("phoneNumber", phoneNumber));
			cr.add(Restrictions.eq("password", password));

			List result = cr.list();
			if (result.size() > 0)
			{
				for (Iterator iterator = result.iterator(); iterator.hasNext();)
				{
					user = (User) iterator.next();
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
		return user;
	}
}