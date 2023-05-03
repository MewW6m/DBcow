package com.dbcow.controller;


import java.util.Map;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dbcow.config.ErrorHandler;
import com.dbcow.config.LoggingAdvice;
import com.dbcow.model.Response;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CustomErrorController extends AbstractErrorController {
	
    public CustomErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@RequestMapping(value = "/error", consumes="text/html")
    public ModelAndView errorhtml(HttpServletRequest request) {
		log.error("404 Not Found Error");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("common/error");
        modelAndView.addObject("statusCode", getStatus(request).value());
        modelAndView.addObject("errorMessage", getStatus(request).name());
        return modelAndView;
	}

	

	@RequestMapping(value = "/error", consumes="application/json")
	@ResponseBody
    public Response errorjson(HttpServletRequest request, HttpServletResponse response) {
	      ErrorAttributeOptions eao = ErrorAttributeOptions.of(
	        ErrorAttributeOptions.Include.BINDING_ERRORS,
	        ErrorAttributeOptions.Include.EXCEPTION,
	        ErrorAttributeOptions.Include.MESSAGE,
	        ErrorAttributeOptions.Include.STACK_TRACE);
	      Map<String, Object> errAttr = getErrorAttributes(request, eao);
	      log.error("BINDING_ERRORS: " + String.valueOf(errAttr.get("BINDING_ERRORS")));
	      log.error("EXCEPTION: " + String.valueOf(errAttr.get("EXCEPTION")));
	      log.error("MESSAGE: " + String.valueOf(errAttr.get("MESSAGE")));
	      log.error("STACK_TRACE: " + String.valueOf(errAttr.get("STACK_TRACE")));
        return new Response(response.getStatus(), String.valueOf(errAttr.get("message")));
    }
    
    
}