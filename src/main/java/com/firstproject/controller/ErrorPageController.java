package com.firstproject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;


@Controller
public class ErrorPageController implements ErrorController {
	
	private static final String ERR_PATH = "/error";		// catch 'error' pages
	
	private ErrorAttributes errorAttributes;
	@Autowired
	public void setErrorAttributes(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}
	
	@RequestMapping(ERR_PATH)
	public String error(Model model, WebRequest request) {		// we can ask for any object in a method of controller, even the HttpServletRequest object itself if we need it...
		Map<String, Object> error = this.errorAttributes.getErrorAttributes(request, ErrorAttributeOptions.defaults());		// getErrAttr() = filter - put all error attributes into a Map
		model.addAttribute("pageTitle", "Sorry, there was an error...");
		model.addAttribute("timestamp", error.get("timestamp"));			// then put it all into the Model in order to send them to the front-end
		model.addAttribute("error", error.get("error"));
		model.addAttribute("message", error.get("message"));
		model.addAttribute("path", error.get("path"));
		model.addAttribute("status", error.get("status"));
		return "detailedError";							// then call detailedError.html to print it out  (but we could also call diff. methods based upon these infos...)
	}

	public String getErrorPath() {
		return ERR_PATH;
	}


}
