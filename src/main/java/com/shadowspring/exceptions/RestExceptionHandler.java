package com.shadowspring.exceptions;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class RestExceptionHandler {

	private final MessageSource messageSource;
	
	public RestExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FieldMessage> handle(MethodArgumentNotValidException exception) {
		Locale locale = LocaleContextHolder.getLocale();
		return exception.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(e -> 
				new FieldMessage(e.getField(), messageSource.getMessage(e, locale))
			).collect(Collectors.toList());
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public List<String> handle(NotFoundException exception) {
		String message = exception.getMessage();
		return Collections.singletonList(message);
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public List<String> notFound(BadRequestException exception) {
		String message = exception.getMessage();
		return Collections.singletonList(message);
	}
	
	
	
}
