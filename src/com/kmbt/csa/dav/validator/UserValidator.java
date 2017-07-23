package com.kmbt.csa.dav.validator;
/*
Copyright (c) 2014-2015 Konica Minolta
Cloud Services Applications
*/
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kmbt.csa.dav.controller.AuthenticationController;
import com.kmbt.csa.dav.pojo.User;
/**
 * @author Kallol Das
 * @Description This user defined {@link Validator} class validates all the user input in the login page
 *             
 * @version 1.0
 * 
 */
public class UserValidator implements Validator {
	
	private static final Logger logger = Logger.getLogger(UserValidator.class);
	
	@Override
	public boolean supports(Class clazz) {
		// just validate the Customer instances
		return User.class.isAssignableFrom(clazz);

	}
	/**
	 * 
	 * @Description This method holds the validation logic         
	 * @return void
	 */
	@Override
	public void validate(Object target, Errors errors) {
		logger.debug("Validation in progress..");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
				"required.userName", "Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"required.password", "Field name is required.");
		logger.debug("Validation done..");
	}
}