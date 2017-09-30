package com.siftfox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siftfox.model.ContactUser;
import com.siftfox.persistence.ContactUserPersistence;
import com.siftfox.service.ContactUserService;

@Service
@Transactional
public class ContactUserServiceImpl implements ContactUserService {


	@Autowired
	private ContactUserPersistence userPersistence;

	@Override
	public List<ContactUser> getUsers() {
		return userPersistence.getAllUsers();
	}

	@Override
	public ContactUser getUserById(Long id) {
		return userPersistence.findById(id);
	}

	@Override
	public ContactUser getUserByEmail(String emailAddress) {
		return userPersistence.getUserByEmail(emailAddress);
	}

	@Override
	public ContactUser createUser(ContactUser user) {
		return userPersistence.makePersistant(user);
	}


}
