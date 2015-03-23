package com.stormevents.endpoints;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.stormevents.dao.StormEventsPersistenceManager;


/**
 * This filter intercepts the endpoint responses in order to close the used entity manager.
 * A unique entity manager makes sure that all transactions are executed with the same
 * context allowing lazy relationships.
 * 
 * @author ricardo
 */
public class DatabaseTransactionFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
		StormEventsPersistenceManager.getInstance().closeEntityManager();
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}
