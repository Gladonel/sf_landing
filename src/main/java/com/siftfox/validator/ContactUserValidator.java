package com.siftfox.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.siftfox.form.ContactForm;
import com.siftfox.service.ContactUserService;

/**
 *
 * @author Hai Minh Vu
 *
 */
@Component
public class ContactUserValidator implements Validator {

	@Autowired
	private ContactUserService userService;

	@Override
	public boolean supports(Class<?> supoprtedClassName) {
		return ContactForm.class.equals(supoprtedClassName);
	}

	@Override
	public void validate(Object model, Errors errors) {
		ContactForm user = (ContactForm) model;
		ValidationUtils.rejectIfEmpty(errors, "email", "email.not.empty");
		ValidationUtils.rejectIfEmpty(errors, "name", "name.cannot.empty");

//		if (!errors.hasErrors()) {
//			if (userService.getUserByEmail(user.getEmail()) != null) {
//				errors.rejectValue("email", "user.already.exists");
//			}
//		}

	}

}
