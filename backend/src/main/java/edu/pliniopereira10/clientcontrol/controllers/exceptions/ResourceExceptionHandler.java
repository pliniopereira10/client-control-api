package edu.pliniopereira10.clientcontrol.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.pliniopereira10.clientcontrol.services.exceptions.ServiceDataBaseException;
import edu.pliniopereira10.clientcontrol.services.exceptions.ServiceNotFoundExpetion;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ServiceNotFoundExpetion.class)
	public ResponseEntity<StandardError> entityNotFound(ServiceNotFoundExpetion e,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Resource not found");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(ServiceDataBaseException.class)
	public ResponseEntity<StandardError> entityNotFound(ServiceDataBaseException e,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Resource not found");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(status).body(error);
	}

}
