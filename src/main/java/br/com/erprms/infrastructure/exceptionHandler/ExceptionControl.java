package br.com.erprms.infrastructure.exceptionHandler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionControl {

	@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> notFound_404() {
        return ResponseEntity
	        		.notFound()
	        		.build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionControlDto>> badRequest_400(MethodArgumentNotValidException ex) {
        var exception = ex.getFieldErrors();
        return ResponseEntity
	        		.badRequest()
	        		.body(exception.stream().map(ExceptionControlDto::new)
	        		.toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> badRequest_400(HttpMessageNotReadableException ex) {
        return ResponseEntity
        			.badRequest()
        			.body(ex.getMessage());
    }

//    @ExceptionHandler(ValidacaoException.class)
//    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException ex) {
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> unauthorizedAutentication_401() {
    	return ResponseEntity
	    			.status(HttpStatus.UNAUTHORIZED)
	    			.body("Authentication failed");
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> unauthorizedCredential_401() {
        return ResponseEntity
	        		.status(HttpStatus.UNAUTHORIZED)
	        		.body("Invalid credentials");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessDanied_403() {
        return ResponseEntity
        			.status(HttpStatus.FORBIDDEN)
        			.body("Access denied");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception_500(Exception ex) {
        return ResponseEntity
        			.status(HttpStatus.INTERNAL_SERVER_ERROR)
        			.body("Error: " + ex.getLocalizedMessage());
    }
    
    private record ExceptionControlDto(
    		String field, 
    		String message) {
    	public ExceptionControlDto(FieldError fieldError) {
            this(
            		fieldError.getField(), 
            		fieldError.getDefaultMessage());
        }
    }
}
