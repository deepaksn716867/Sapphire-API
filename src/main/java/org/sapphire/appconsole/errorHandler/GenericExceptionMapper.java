package org.sapphire.appconsole.errorHandler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception>{
	private final static Logger LOG = Logger.getLogger(GenericExceptionMapper.class) ;

	public GenericExceptionMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response toResponse(Exception arg0) {
		// TODO Auto-generated method stub
		Response response = null;
		try {
			response =  Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(new ObjectMapper().writeValueAsString(new ErrorHandler("Unexpected Error",500)))
					.type(MediaType.APPLICATION_JSON)
					.build();
		} catch (Exception e) {
			LOG.error("Unexpected error while trying writig json error object",e);
			e.printStackTrace();
		}
		return response;
	}

}
