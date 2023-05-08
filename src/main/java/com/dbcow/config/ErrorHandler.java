package com.dbcow.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.TypeMismatchException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dbcow.model.Response;
import com.dbcow.util.ControllerUtil;
import com.dbcow.util.Util;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private Util util;
	@Autowired
	private ControllerUtil controllerUtil;

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
		// メッセージを何にするか判定(bodyにあればそれ、なければutil.getMessage(String.valueOf(statusCode.value())
		String errorMessage;
		if (ex instanceof CustomErrorException)
			errorMessage = String.valueOf(ex.getMessage());
		else if (ex instanceof MethodArgumentNotValidException)
			errorMessage = String.valueOf(body);
		else
			errorMessage = util.getMessage(String.valueOf(statusCode.value()));

		if (StringUtils.contains(request.getHeader("Content-Type"), "text/html"))
			return super.handleExceptionInternal(ex, controllerUtil.getErrorPageContent(500, "INTERNAL_SERVER_ERROR"),
					headers, statusCode, request);
		else
			return new ResponseEntity<>(new Response(statusCode.value(), errorMessage), headers, statusCode);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {

		List<String> errorMessageList = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMessageList.add(error.getField() + ": " + error.getDefaultMessage());
		});
		return handleExceptionInternal(ex, errorMessageList.get(0), headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		if (ex instanceof HttpRequestMethodNotSupportedException subEx) {
			return handleExceptionInternal(subEx, subEx.getMessage(), subEx.getHeaders(), subEx.getStatusCode(),
					request);
		} else if (ex instanceof HttpMediaTypeNotSupportedException subEx) {
			return handleExceptionInternal(subEx, subEx.getMessage(), subEx.getHeaders(), subEx.getStatusCode(),
					request);
		} else if (ex instanceof HttpMediaTypeNotAcceptableException subEx) {
			return handleExceptionInternal(subEx, subEx.getMessage(), subEx.getHeaders(), subEx.getStatusCode(),
					request);
		} else if (ex instanceof MissingPathVariableException subEx) {
			return handleExceptionInternal(subEx, subEx.getMessage(), subEx.getHeaders(), subEx.getStatusCode(),
					request);
		} else if (ex instanceof MissingServletRequestParameterException subEx) {
			return handleExceptionInternal(subEx, subEx.getMessage(), subEx.getHeaders(), subEx.getStatusCode(),
					request);
		} else if (ex instanceof MissingServletRequestPartException subEx) {
			return handleExceptionInternal(subEx, subEx.getMessage(), subEx.getHeaders(), subEx.getStatusCode(),
					request);
		} else if (ex instanceof ServletRequestBindingException subEx) {
			return handleExceptionInternal(subEx, subEx.getMessage(), subEx.getHeaders(), subEx.getStatusCode(),
					request);
		} else if (ex instanceof MethodArgumentNotValidException subEx) {
			List<String> errorMessageList = new ArrayList<>();
			subEx.getBindingResult().getFieldErrors().forEach(error -> {
				errorMessageList.add(error.getField() + ": " + error.getDefaultMessage());
			});
			return handleExceptionInternal(ex, errorMessageList.get(0), subEx.getHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR, request);
		} else if (ex instanceof NoHandlerFoundException subEx) {
			return handleExceptionInternal(subEx, subEx.getMessage(), subEx.getHeaders(), subEx.getStatusCode(),
					request);
		} else if (ex instanceof AsyncRequestTimeoutException subEx) {
			return handleExceptionInternal(subEx, subEx.getMessage(), subEx.getHeaders(), subEx.getStatusCode(),
					request);
		} else if (ex instanceof ErrorResponseException subEx) {
			return handleExceptionInternal(subEx, subEx.getMessage(), subEx.getHeaders(), subEx.getStatusCode(),
					request);
		} else if (ex instanceof ConversionNotSupportedException theEx) {
			return handleExceptionInternal(theEx, theEx.getMessage(), new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR, request);
		} else if (ex instanceof TypeMismatchException theEx) {
			return handleExceptionInternal(theEx, theEx.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
					request);
		} else if (ex instanceof HttpMessageNotReadableException theEx) {
			return handleExceptionInternal(theEx, theEx.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
					request);
		} else if (ex instanceof HttpMessageNotWritableException theEx) {
			return handleExceptionInternal(theEx, theEx.getMessage(), new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR, request);
		} else if (ex instanceof BindException theEx) {
			return handleExceptionInternal(theEx, theEx.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
					request);
		} else {
			return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
					request);
		}
	}
}