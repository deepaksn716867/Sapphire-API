package org.sapphire.appconsole.resource;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * This class is a filter for supporting CORS.
 * All the responses will have the corresponding headers.
 * @author deepak
 */
@Provider
public class CorsResponseFilters implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		MultivaluedMap<String, Object> headers = responseContext.getHeaders();

		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT,OPTIONS, HEAD");
		headers.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
	}
}
