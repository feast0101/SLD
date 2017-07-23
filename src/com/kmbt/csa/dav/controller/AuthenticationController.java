package com.kmbt.csa.dav.controller;

/*
 Copyright (c) 2014-2015 Konica Minolta
 Cloud Services Applications
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.kmbt.csa.dav.bo.UserBo;
import com.kmbt.csa.dav.pojo.User;
import com.kmbt.csa.dav.validator.UserValidator;

/**
 * @author Kallol Das
 * @Description This controller servlet handles authentication requests coming
 *              from login page.
 * @version 1.0
 * 
 */
@Controller
public class AuthenticationController {

	@Autowired
	UserBo authservice;

	UserValidator userValidator;

	private static final Logger logger = Logger
			.getLogger(AuthenticationController.class);

	@Autowired
	public AuthenticationController(UserValidator userValidator) {
		this.userValidator = userValidator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	/**
	 * 
	 * @Description This method will handle the authentication process after
	 *              user submission
	 * @return String view name
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("user") User user,
			BindingResult result, SessionStatus status, HttpSession session) {
		logger.info("Login process in progress..");
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			logger.info("validation status:FAILED !!");
			return "Loginpage";
		} else if (authservice.authenticate(user)) {
			logger.info("authentication status:SUCCESSFUL !!");
			return "redirect:showUserInput.do";
		} else {
			ObjectError error = new ObjectError("authentication",
					"Invalid Authentication....Please try again");
			result.addError(error);
			logger.info("authentication status:UNSUCCESSFUL !!");
		}
		logger.info("Logout process complete..");
		return "Loginpage";
	}

	/**
	 * 
	 * @Description This method initializes command object {@link User} in the
	 *              view
	 * @return String view name
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "Loginpage";
	}

	/**
	 * 
	 * @Description This method will handle the logout process mechanism
	 * @return String view name
	 */
	@RequestMapping(value = "/logout.do")
	public String logout(@ModelAttribute("user") User user,
			BindingResult result, SessionStatus status, HttpSession session) {
		logger.info("Logout process in progress..");
		status.setComplete();
		session.invalidate();
		logger.info("Logout process complete..");
		return "redirect:/Logoutpage.jsp";
	}

}
