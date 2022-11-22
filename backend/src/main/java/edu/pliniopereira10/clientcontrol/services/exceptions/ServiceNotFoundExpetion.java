package edu.pliniopereira10.clientcontrol.services.exceptions;

public class ServiceNotFoundExpetion extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ServiceNotFoundExpetion(String msg) {
		super(msg);
	}
	
}
