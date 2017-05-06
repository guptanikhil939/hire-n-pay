package com.hirenpay.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hirenpay.exception.ServiceException;

public interface DownloadService
{
	public void downloadFile(HttpServletRequest request,  HttpServletResponse response, String filePath) throws ServiceException;
}
