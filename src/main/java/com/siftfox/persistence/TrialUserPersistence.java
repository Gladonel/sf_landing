package com.siftfox.persistence;

import java.util.List;

import com.siftfox.model.TrialUser;

public interface TrialUserPersistence extends GenericDAO<TrialUser, Long>{

	List<TrialUser> getAllUsers();

	TrialUser getUserByEmail(String email);
}
