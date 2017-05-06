package com.hirenpay.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hirenpay.exception.ServiceException;
import com.hirenpay.service.DownloadService;

@Service("downloadService")
public class DownloadServiceImpl implements DownloadService
{
	/**
	 * Size of a byte buffer to read/write file
	 */
	private static final int BUFFER_SIZE = 4096;

	private static final Logger log = Logger
			.getLogger(DownloadServiceImpl.class);

	public void downloadFile(HttpServletRequest request,
			HttpServletResponse response, String filePath)
			throws ServiceException
	{
		log.info("Inside downloadFile() method");

		try
		{
			ServletContext context = request.getServletContext();

			File downloadFile = new File(filePath);
			FileInputStream inputStream = new FileInputStream(downloadFile);

			// get MIME type of the file
			String mimeType = context.getMimeType(filePath);
			if (mimeType == null)
			{
				// set to binary type if MIME mapping not found
				mimeType = "application/octet-stream";
			}

			// set content attributes for the response
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					downloadFile.getName());
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1)
			{
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

	}
}
