package com.siftfox.persistence;

import java.util.List;

import com.siftfox.model.ContactUser;

public interface ContactUserPersistence extends GenericDAO<ContactUser, Long>{

	List<ContactUser> getAllUsers();

	ContactUser getUserByEmail(String email);
}
