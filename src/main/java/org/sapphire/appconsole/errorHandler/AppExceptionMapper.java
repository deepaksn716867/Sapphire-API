package org.sapphire.appconsole.errorHandler;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class AppExceptionMapper extends WebApplicationException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public AppExceptionMapper() {
		super("Not Found");
	}

	public AppExceptionMapper(Response.Status errorcode,String errorJSON) {
		
		super(Response.status(errorcode).entity(errorJSON).type("application/json").build());
		System.out.println("Going to call super class constructor");
	}
}
