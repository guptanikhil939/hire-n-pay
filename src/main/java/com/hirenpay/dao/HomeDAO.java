package com.hirenpay.dao;

import java.util.List;

import com.hirenpay.exception.PersistenceException;
import com.hirenpay.model.Services;
import com.hirenpay.model.Subscriber;

public interface HomeDAO
{
	public List<Services> getServiceList() throws PersistenceException;

	public boolean subscribe(Subscriber subscriber) throws PersistenceException;

	public Subscriber getSubscriber(String phoneNumber) throws PersistenceException;
}
