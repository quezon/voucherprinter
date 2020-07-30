package com.ex.advice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.opensagres.xdocreport.core.XDocReportException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler({XDocReportException.class})
	public boolean handleXDocReportException(HttpServletRequest request, Exception ex){
		logger.info("XDoc Report Exception Occured:: URL= " + request.getRequestURL());
		return false;
	}
	
	@ExceptionHandler({IOException.class})
	public boolean handleIOException(HttpServletRequest request, Exception ex){
		logger.info("I/O Exception Occured:: URL= " + request.getRequestURL());
		return false;
	}
}
