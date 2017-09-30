package com.siftfox.service;

import java.util.List;

import com.siftfox.model.TrialUser;

public interface TrialUserService {

	List<TrialUser> getUsers();
	TrialUser getUserById(Long id);
	TrialUser getUserByEmail(String emailAddress);
	TrialUser createUser(TrialUser newUser);

}
