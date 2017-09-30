package com.siftfox.controller;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siftfox.form.ContactForm;
import com.siftfox.form.UserForm;
import com.siftfox.model.ContactUser;
import com.siftfox.service.ContactUserService;
import com.siftfox.validator.ContactUserValidator;

/**
 *
 * @author Hai Minh Vu
 *
 */
@Controller
@RequestMapping
public class MainController {

	@Autowired
	private ContactUserService contactUserService;
	
	@Autowired
	private ContactUserValidator contactUserValidator;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String showHomePage(Model model) {
		UserForm user = new UserForm();
		model.addAttribute("user", user);
		return "index";
	}
	
	@RequestMapping(value = "/contactUs", method = RequestMethod.POST)
	public String doSendContact(@ModelAttribute("contactForm") ContactForm contactForm,
			BindingResult errors, HttpServletRequest request, final RedirectAttributes redirectAttributes) {

		contactUserValidator.validate(contactForm, errors);

        if (!errors.hasErrors()) {
        	String userEmail = contactForm.getEmail();
        	String name = contactForm.getName();
        	String subject = contactForm.getSubject();
        	String message = contactForm.getMessage();
        	
        	try {
        		ContactUser contactUser = new ContactUser();
            	contactUser.setEmail(userEmail);
            	contactUser.setName(name);
            	contactUser.setSubject(subject);
            	contactUser.setMessage(message);
            	
            	contactUserService.createUser(contactUser);
			} catch (Exception e) {
				// TODO: handle exception
			}
        	
        	try {
        		String msgBody = name + " <" + userEmail + "> has left a message on SiftFox:<br/><br/>";
        		msgBody += "<b>Subject:</b><br/>" + subject + "<br/><br/>";
        		msgBody += "<b>Message:</b><br/>" + message + "<br/><br/>";
        		msgBody += "Regards,<br/>SiftFox Team";
        		
        		MimeMessage msg = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(msg, true);
				helper.setTo(InternetAddress.parse("ceo@siftfox.com", false));
				helper.setCc(InternetAddress.parse("haivm3686@gmail.com, adam.edward.mayer@gmail.com", false));
				helper.setFrom(new InternetAddress("ceo@siftfox.com", "SiftFox Support"));
				helper.setSubject("SiftFox - New message from " + userEmail);
				helper.setText(msgBody, true);
				mailSender.send(msg);
			} catch (Exception e) {
				// TODO: handle exception
			}
        	
        	redirectAttributes.addFlashAttribute("returnMsg", "success");
        }
        
        return "redirect:home";
	}
}
