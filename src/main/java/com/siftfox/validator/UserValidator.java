package com.siftfox.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.siftfox.form.UserForm;
import com.siftfox.service.TrialUserService;

/**
 *
 * @author Hai Minh Vu
 *
 */
@Component
public class UserValidator implements Validator {

	@Autowired
	private TrialUserService userService;

	@Override
	public boolean supports(Class<?> supoprtedClassName) {
		return UserForm.class.equals(supoprtedClassName);
	}

	@Override
	public void validate(Object model, Errors errors) {
		UserForm user = (UserForm) model;
		ValidationUtils.rejectIfEmpty(errors, "email", "email.not.empty");

//		if (!errors.hasErrors()) {
//			if (userService.getUserByEmail(user.getEmail()) != null) {
//				errors.rejectValue("email", "user.already.exists");
//			}
//		}

	}

}
