package edu.pliniopereira10.clientcontrol.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.pliniopereira10.clientcontrol.services.exceptions.ServiceNotFoundExpetion;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ServiceNotFoundExpetion.class)
	public ResponseEntity<StandardError> entityNotFound(ServiceNotFoundExpetion e,
			HttpServletRequest request) {
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Resource not found");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}