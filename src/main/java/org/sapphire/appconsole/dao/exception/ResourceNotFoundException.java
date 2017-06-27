package org.sapphire.appconsole.dao.exception;

public class ResourceNotFoundException extends ResourceException {

	private static final long serialVersionUID = -595805914287057554L;
	
	public ResourceNotFoundException(){
		
	}
	
	public ResourceNotFoundException(String resourceClassName, String message) {
		super(resourceClassName, message);
		
		
	}
}
