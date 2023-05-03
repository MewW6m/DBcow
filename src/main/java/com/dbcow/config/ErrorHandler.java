package com.dbcow.config;

import com.dbcow.model.Response;
import com.dbcow.util.ControllerUtil;
import com.dbcow.util.Util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
}