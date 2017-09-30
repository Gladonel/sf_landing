package com.siftfox.controller;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siftfox.form.UserForm;
import com.siftfox.model.TrialUser;
import com.siftfox.service.TrialUserService;
import com.siftfox.validator.UserValidator;

/**
 *
 * @author Hai Minh Vu
 *
 */
@Controller
@RequestMapping
public class AjaxController {

	@Autowired
    private JavaMailSender mailSender;

	@Autowired
	private TrialUserService userService;

	@Autowired
	private UserValidator userValidator;
	
	@RequestMapping(value = "/requestDemo", method = RequestMethod.GET)
	public @ResponseBody String doRequestDemo(@ModelAttribute("user") UserForm user,
			BindingResult errors, HttpServletRequest request) {

		userValidator.validate(user, errors);

        if (!errors.hasErrors()) {
        	String userEmail = user.getEmail();
            TrialUser trialUser = userService.getUserByEmail(userEmail);

            if (trialUser == null) {
            	trialUser = new TrialUser();
            	trialUser.setEmail(userEmail);
                userService.createUser(trialUser);
            }
        }
        
        return "";
	}
	
	@RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	public @ResponseBody String doSendEmail(HttpServletRequest request) {
		try {
			
			String userEmail = request.getParameter("email");
			
			if (userEmail != null && userEmail.length() > 0) {
				MimeMessage msg = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(msg, true);
				helper.setTo(InternetAddress.parse("ceo@siftfox.com", false));
				helper.setCc(InternetAddress.parse("haivm3686@gmail.com, adam.edward.mayer@gmail.com", false));
				//helper.setTo(InternetAddress.parse("haivm3686@gmail.com", false));
				helper.setFrom(new InternetAddress("ceo@siftfox.com", "SiftFox Support"));
				helper.setSubject("SiftFox - New request for a demo");
				helper.setText(userEmail + " has requested for a demo!", true);
				mailSender.send(msg);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
}
