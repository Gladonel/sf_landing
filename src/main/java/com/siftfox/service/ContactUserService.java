package com.siftfox.service;

import java.util.List;

import com.siftfox.model.ContactUser;

public interface ContactUserService {

	List<ContactUser> getUsers();
	ContactUser getUserById(Long id);
	ContactUser getUserByEmail(String emailAddress);
	ContactUser createUser(ContactUser newUser);

}
