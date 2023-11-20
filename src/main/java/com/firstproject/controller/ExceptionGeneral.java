package com.firstproject.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//@ControllerAdvice					// this is not a controller
public class ExceptionGeneral {
	
//	@ExceptionHandler
	public String exceptionHandler(Exception ex, Model model) {
		model.addAttribute("exception", ex);		// put the entire exc object into model then call the relevant html
		return "generalExceptionHandler";
	}

}
