package com.siftfox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siftfox.model.TrialUser;
import com.siftfox.persistence.TrialUserPersistence;
import com.siftfox.service.TrialUserService;

@Service
@Transactional
public class TrialUserServiceImpl implements TrialUserService {


	@Autowired
	private TrialUserPersistence userPersistence;

	@Override
	public List<TrialUser> getUsers() {
		return userPersistence.getAllUsers();
	}

	@Override
	public TrialUser getUserById(Long id) {
		return userPersistence.findById(id);
	}

	@Override
	public TrialUser getUserByEmail(String emailAddress) {
		return userPersistence.getUserByEmail(emailAddress);
	}

	@Override
	public TrialUser createUser(TrialUser user) {
		return userPersistence.makePersistant(user);
	}


}
