package com.hirenpay.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hirenpay.dao.AccountDAO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.model.OrderDetails;
import com.hirenpay.model.User;

@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO
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

	public List<OrderDetails> getOrderDetailsList(String phoneNumber)
			throws PersistenceException
	{
		List<OrderDetails> orderDetails = null;

		try
		{
			Criteria cr = getCurrentSession()
					.createCriteria(OrderDetails.class);
			cr.add(Restrictions.eq("pickupPhoneNumber", phoneNumber));
			cr.addOrder(Order.desc("pickUpDate"));

			orderDetails = (List<OrderDetails>) cr.list();
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		return orderDetails;
	}

	public User getProfile(String phoneNumber) throws PersistenceException
	{
		User user = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(User.class);
			cr.add(Restrictions.eq("phoneNumber", phoneNumber));

			List object = cr.list();

			if (null != object && object.size()>0)
			{
				user = (User)object.get(0);
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

	public boolean updateProfile(User user) throws PersistenceException
	{
		try
		{
			getCurrentSession().update(user);
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
}